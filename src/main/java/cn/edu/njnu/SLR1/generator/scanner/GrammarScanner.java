package cn.edu.njnu.SLR1.generator.scanner;

import cn.edu.njnu.SLR1.generator.exception.GrammarDescriptorException;
import cn.edu.njnu.SLR1.generator.grammarelement.GrammarContent;
import cn.edu.njnu.SLR1.generator.grammarelement.Symbol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarScanner {
	private GrammarContent content = new GrammarContent();
	private String dependencyPackage = "";
	private Set<String> packages = new HashSet<String>();

	private static final String Dependencies = "\\$DEPENDENCIES[\\s]*\\{(.+?)}";
	private static final String Terminal = "\\$VT[\\s]*\\{(.+?)}";
	private static final String NonTerminal = "\\$VN[\\s]*\\{(.+?)}";
	private static final String GrammarContent = "G\\[(.+?)][\\s]*\\{\\{((.+|[\\s])+)}}";

	private static Pattern DependenciesPattern = Pattern.compile(Dependencies, Pattern.DOTALL);
	private static Pattern TerminalPattern = Pattern.compile(Terminal, Pattern.DOTALL);
	private static Pattern NonTerminalPattern = Pattern.compile(NonTerminal, Pattern.DOTALL);
	private static Pattern GrammarContentPattern = Pattern.compile(GrammarContent, Pattern.DOTALL);

	public void scan(String buffer) throws Exception {
		// Scan the lex file content and analyse the content
		// Get dependencies
		Matcher m = DependenciesPattern.matcher(buffer);
		if (m.find()) {
			this.dependencyPackage = m.group(1).trim() + ".";
		}

		// Get non-terminal definitions
		m = NonTerminalPattern.matcher(buffer);
		if (m.find()) {
			String nonTerminal = m.group(1);
			List<Symbol> nonTerminals = Arrays.stream(nonTerminal.trim().split("[\\s]+"))
					.filter(s -> s != null)
					.map(s -> new Symbol(false, s, null))
					.collect(Collectors.toList());
			for (Symbol s : nonTerminals) {
				this.content.addNonTerminalSymbol(s);
			}
		} else
			throw new GrammarDescriptorException(GrammarDescriptorException.MissingNonTerminals);

		// Get terminal definitions
		m = TerminalPattern.matcher(buffer);
		if (m.find()) {
			String terminal = m.group(1);
			String[] rawTerminals = terminal.trim().split("[\n\r]+");
			for (String s : rawTerminals) {
				int index = s.lastIndexOf(',');
				String symbol = s.substring(1, index).trim();
				String type = s.substring(index + 1, s.length() - 1).trim();
				// Split the Enum into ClassName.EnumName for reflection
				String[] typeClass = type.split("[.]");
				Class clazz = null;
				// Reflectively get the enum class and get instance by Enum.valueOf(String) => Enum Object
				if (typeClass[0].equals("TypeEnum")) {
					// The type enum is contained in generator instead of customized
					// outside the package in previous programs
					clazz = Class.forName("cn.edu.njnu.SLR1.generator.wordelement.TypeEnum");
					this.packages.add("cn.edu.njnu.SLR1.generator.wordelement.TypeEnum");
				}
				else {
					clazz = Class.forName(this.dependencyPackage + typeClass[0]);
					this.packages.add(this.dependencyPackage + typeClass[0]);
				}

				this.content.addTerminalSymbol(
						new Symbol(true,
								symbol,
								clazz.getMethod("valueOf", String.class).invoke(null, typeClass[1]))
				);
			}
		} else
			throw new GrammarDescriptorException(GrammarDescriptorException.MissingTerminals);

		// The grammar definitions
		m = GrammarContentPattern.matcher(buffer);
		if (m.find()) {
			String start = m.group(1);
			if (!this.content.setBeginning(start))
				throw new GrammarDescriptorException(GrammarDescriptorException.IllegalGrammarBeginning + start);

			String rawRules = m.group(2);
			// Split rule in a line by | into rules
			String[] rules = rawRules.trim().split("[\n\r]+");
			for (String r : rules) {
				int index = r.indexOf(":");
				String name = r.substring(0, index).trim();
				String rule = r.substring(index + 1).trim();
				List<String> subRules = Arrays.stream(rule.split("(?![{])\\|(?![}])"))
						.map(s -> s.trim())
						.collect(Collectors.toList());
				this.content.addRules(name, subRules);
			}
		} else
			throw new GrammarDescriptorException(GrammarDescriptorException.MissingGrammarDefinition);

		this.content.process();
	}

	public GrammarContent getContent() {
		return this.content;
	}

	public Set<String> getPackages() {
		return packages;
	}
}
