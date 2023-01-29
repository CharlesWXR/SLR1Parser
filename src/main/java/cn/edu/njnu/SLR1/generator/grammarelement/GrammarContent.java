package cn.edu.njnu.SLR1.generator.grammarelement;


import cn.edu.njnu.SLR1.generator.exception.GrammarDescriptorException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarContent {
	private Map<String, List<GrammarSentence>> grammar = new HashMap<String, List<GrammarSentence>>();
	private Symbol beginning = new Symbol();
	private List<Symbol> terminalSymbols = new ArrayList<Symbol>();
	private List<Symbol> nonTerminalSymbols = new ArrayList<Symbol>();

	private Map<Symbol, Set<Symbol>> firsts = new HashMap<Symbol, Set<Symbol>>();
	private Map<Symbol, Set<Symbol>> follows = new HashMap<Symbol, Set<Symbol>>();

	private static final String RuleElement = "\\$\\{(.+?)}";
	private static final Pattern RuleElementPattern = Pattern.compile(RuleElement);

	@Override
	public String toString() {
		return "GrammarContent{" +
				"grammar=" + grammar.toString() +
				", beginning=" + beginning +
				", terminalSymbols=" + terminalSymbols +
				", nonTerminalSymbols=" + nonTerminalSymbols +
				'}';
	}

	public boolean setBeginning(String beginning) {
		// Check the availability of the symbol
		Symbol symbol = new Symbol();
		symbol.isTerminal = false;
		symbol.identifier = beginning;

		if (!this.nonTerminalSymbols.contains(symbol))
			return false;

		this.beginning = symbol;
		return true;
	}

	public boolean addTerminalSymbol(Symbol symbol) {
		// Distinct the symbol
		if (this.terminalSymbols.contains(symbol))
			return false;

		this.terminalSymbols.add(symbol);
		return true;
	}

	public boolean addNonTerminalSymbol(Symbol symbol) {
		// Distinct the symbol
		if (this.nonTerminalSymbols.contains(symbol))
			return false;

		this.nonTerminalSymbols.add(symbol);
		return true;
	}

	public boolean addRules(String symbol, List<String> rules) throws Exception {
		// Add grammar sentences to a left symbol
		for (Symbol s : this.nonTerminalSymbols) {
			if (s.identifier.equals(symbol)) {
				for (String rule : rules) {
					List<String> elements = new ArrayList<String>();
					// Extract symbols from the rule
					Matcher matcher = RuleElementPattern.matcher(rule);
					while (matcher.find()) {
						elements.add(matcher.group(1).trim());
					}

					if (elements.size() == 0)
						return false;
					addRule(symbol, elements);
				}
				return true;
			}
		}
		return false;
	}

	private void addRule(String symbol, List<String> rule) throws Exception {
		// A symbol preprocess and control for rule insertion
		List<GrammarSentence> target = this.grammar.get(symbol);
		if (null == target) {
			this.grammar.put(symbol, new ArrayList<GrammarSentence>());
			target = this.grammar.get(symbol);
		}

		// GrammarSentence is actually an array of symbols with Select set for further usage
		GrammarSentence gs = new GrammarSentence();
		for (String str : rule) {
			if (!addRule(str, gs))
				throw new GrammarDescriptorException(
						GrammarDescriptorException.UndeclaredIdentifier + str);
		}
		gs.length = gs.contents.size();
		target.add(gs);
	}

	private boolean addRule(String str, GrammarSentence gs) {
		// Do add rule
		// Process the name of the symbols into symbols by consulting the symbol table
		// meanwhile verify the symbols
		Symbol tempSymbol = new Symbol();
		boolean found = false;

		tempSymbol.identifier = str;
		for (Symbol s : this.terminalSymbols) {
			if (s.identifier.equals(str)) {
				found = true;
				tempSymbol.isTerminal = true;
				tempSymbol.type = s.type;
				break;
			}
		}
		if (!found) {
			for (Symbol s : this.nonTerminalSymbols) {
				if (s.identifier.equals(str)) {
					found = true;
					tempSymbol.isTerminal = false;
					break;
				}
			}
		}

		if (!found)
			return false;

		gs.contents.add(tempSymbol);
		return true;
	}

	private Set<Symbol> getFirst(Symbol s) {
		// Get the First-set
		if (s.isTerminal) {
			// The first-set of terminal is itself
			Set<Symbol> result = new HashSet<>();
			result.add(s);
			return result;
		}

		Set<Symbol> first = this.firsts.get(s);
		if (first == null) {
			// Need to put an empty set first in case of recursion infinitely
			// considering A -> B..., B -> CA..., C -> empty | ...
			first = new HashSet<Symbol>();
			this.firsts.put(s, first);
		} else
			// Has the record in the map, simply return the result
			return first;

		List<GrammarSentence> rules = this.grammar.get(s.identifier);
		if (rules == null)
			return null;

		for (GrammarSentence gs : rules) {
			for (int i = 0; i < gs.contents.size(); i++) {
				Symbol temp = gs.contents.get(i);
				if (temp.isTerminal) {
					// Meet the terminal in the rule, add it and break the loop
					first.add(temp);
					break;
				} else if (!s.identifier.equals(temp.identifier)) {
					// Meet a different non-terminal, add it all
					Set<Symbol> recFirst = this.firsts.get(temp.identifier);
					if (recFirst == null)
						// no record in the map, recurse to get it
						recFirst = getFirst(temp);

					if (!recFirst.contains(Symbol.Empty)) {
						// Contains no empty rule, add the first-set and break the loop
						first.addAll(recFirst);
						break;
					}
					if (i != gs.contents.size() - 1) {
						// the last first-set needn't to remove the empty symbol
						recFirst.remove(Symbol.Empty);
						first.addAll(recFirst);
					}
				}
			}
		}
		this.firsts.put(s, first);
		return first;
	}

	private Set<Symbol> getFollow(Symbol s) {
		// Get the follow-set of a symbol
		// query the map first
		Set<Symbol> follow = this.follows.get(s);
		if (follow == null) {
			// Haven't processed before
			follow = new HashSet<Symbol>();
			// The beginning of the grammar should add # to the follow-set
			if (s.equals(this.beginning))
				follow.add(Symbol.End);

			// avoid infinite recursion
			this.follows.put(s, follow);
		} else
			return follow;

		for (Map.Entry<String, List<GrammarSentence>> entry : grammar.entrySet()) {
			List<GrammarSentence> rules = entry.getValue();
			for (GrammarSentence rule : rules) {
				// If a rule contains target symbol, we care the following one instead
				boolean found = false;
				for (int i = 0; i < rule.contents.size(); i++) {
					if (found) {
						// Process the following symbol
						Set<Symbol> first = getFirst(rule.contents.get(i));
						if (first.contains(Symbol.Empty))
							// No empty in follow-set and take the following symbol
							// into consideration by not reset the found flag
							first.remove(Symbol.Empty);
						else
							found = false;
						follow.addAll(first);
					} else if (s.equals(rule.contents.get(i))) {
						found = true;
					}
				}
				if (found) {
					// if is the target symbol is the end of the rule
					// or ...ab first(b)->empty
					if (!entry.getKey().equals(s.identifier)) {
						follow.addAll(getFollow(new Symbol(false, entry.getKey(), null)));
					}
				}
			}
		}

		this.follows.put(s, follow);
		return follow;
	}

	public void process() {
		processBegin();
		processFirst();
		processFollow();
		processFollows();
	}

	private void processBegin() {
		List<GrammarSentence> beginningRight = grammar.get(this.beginning.identifier);
		if (beginningRight.size() != 1) {
			expandGrammar();
			return;
		}

		for (Map.Entry<String, List<GrammarSentence>> entry : this.grammar.entrySet()) {
			List<GrammarSentence> rules = entry.getValue();
			for (GrammarSentence rule : rules)
				if (rule.contents.contains(this.beginning)) {
					expandGrammar();
					return;
				}
		}
	}

	private void expandGrammar() {
		Symbol newBegin = new Symbol(false, "$Begin", null);

		GrammarSentence rule = new GrammarSentence();
		rule.contents = new ArrayList<Symbol>();
		rule.add(this.beginning);
		rule.length = rule.contents.size();

		this.nonTerminalSymbols.add(newBegin);
		this.grammar.put(newBegin.identifier, Arrays.asList(rule));

		this.beginning = newBegin;
	}

	private void processFirst() {
		for (Symbol s : this.nonTerminalSymbols)
			getFirst(s);
	}

	private void processFollow() {
		for (Symbol s : this.nonTerminalSymbols)
			getFollow(s);
	}

	private void processFollows() {
		for (Map.Entry<String, List<GrammarSentence>> entry : this.grammar.entrySet()) {
			List<GrammarSentence> rules = entry.getValue();
			Symbol temp = new Symbol(false, entry.getKey(), null);
			for (GrammarSentence rule : rules) {
				rule.follows = getFollow(temp);
			}
		}
	}

	public Map<String, List<GrammarSentence>> getGrammar() {
		return grammar;
	}

	public Symbol getBeginning() {
		return beginning;
	}

	public List<Symbol> getTerminalSymbols() {
		return terminalSymbols;
	}

	public List<Symbol> getNonTerminalSymbols() {
		return nonTerminalSymbols;
	}
}
