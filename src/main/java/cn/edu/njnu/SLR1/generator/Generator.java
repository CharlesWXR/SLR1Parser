package cn.edu.njnu.SLR1.generator;

import cn.edu.njnu.SLR1.generator.annotation.TemplateScanPath;
import cn.edu.njnu.SLR1.generator.scanner.GrammarProcessor;
import cn.edu.njnu.SLR1.generator.scanner.GrammarScanner;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@TemplateScanPath(".\\src\\main\\java\\cn\\edu\\njnu\\SLR1\\generator\\template")
public class Generator {
	private static String Path;
	static {
		// Load the templates from the path in the annotation TemplateScanPath
		TemplateScanPath scanPathAnno = Generator.class.getAnnotation(TemplateScanPath.class);
		Path = scanPathAnno.value();
	}

	private static final String TableTemplateName = "Table.ftl";
	private static final String TableOutputName = "Table.java";
	private static final String DriverTemplateName = "TableDriver.ftl";
	private static final String DriverOutputName = "TableDriver.java";

	public void generate(String lexBuffer) throws Exception {
		Map<String, Object> templateParams = new HashMap<String, Object>();

		// Init package name
		StackTraceElement[] invokers = Thread.currentThread().getStackTrace();
		Class clazz = Class.forName(invokers[2].getClassName());
		String packageName = clazz.getPackage().getName() + ".ll1table";
		templateParams.put("packageName", packageName);

		GrammarScanner scanner = new GrammarScanner();
		scanner.scan(lexBuffer);

		GrammarProcessor processor = new GrammarProcessor();
		processor.process(scanner.getContent());
		templateParams.putAll(processor.toInitMap());

		Set<String> dependencies = scanner.getPackages();
		templateParams.put("dependencies", new ArrayList<String>(dependencies));

		Configuration conf = new Configuration(Configuration.getVersion());
		conf.setDirectoryForTemplateLoading(new File(Path));
		
		Template tableTemplate = conf.getTemplate(TableTemplateName);
		Writer out = new FileWriter(new File(TableOutputName));
		tableTemplate.process(templateParams, out);
		out.close();
		System.out.println("SRL1 Table saved to Table.java");
		
		Template driverTemplate = conf.getTemplate(DriverTemplateName);
		out = new FileWriter(new File(DriverOutputName));
		driverTemplate.process(templateParams, out);
		out.close();
		System.out.println("Table driver saved to TableDriver.java");
	}
}
