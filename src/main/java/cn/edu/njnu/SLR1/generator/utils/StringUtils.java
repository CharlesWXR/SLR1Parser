package cn.edu.njnu.SLR1.generator.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static Pattern TemplatePattern = Pattern.compile("\\$\\{([\\w]+?)}");
	private static String TransformedReturn = "$1if (_return == null) {\n\t$1_return = $2\n$1}";

	public static String processTemplate(String template, Map<String, String> mapper) {
		StringBuffer sb = new StringBuffer();
		Matcher matcher = TemplatePattern.matcher(template);

		while (matcher.find()) {
			String key = matcher.group(1);
			if (mapper.containsKey(key)) {
				matcher.appendReplacement(sb, mapper.get(key));
			}
		}
		matcher.appendTail(sb);

		return sb.toString();
	}

	// Turn the "return" into _return = xxx;
	public static String processReturn(String target) {
		return target.replaceAll("([ \\t]*)return (.*;)", TransformedReturn);
	}
}
