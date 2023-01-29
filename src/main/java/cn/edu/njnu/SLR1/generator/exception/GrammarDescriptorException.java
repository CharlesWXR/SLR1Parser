package cn.edu.njnu.SLR1.generator.exception;

public class GrammarDescriptorException extends Exception {
	public static final String UndeclaredIdentifier = "Undeclared Identifier: ";
	public static final String MissingNonTerminals = "Missing non-terminal symbol definitions.";
	public static final String MissingTerminals = "Missing terminal symbol definitions.";
	public static final String MissingGrammarDefinition = "Missing grammar definition.";
	public static final String IllegalGrammarBeginning = "Illegal grammar beginning: ";
	public static final String NotSLR1GrammarSR = "Not a SLR(1) grammar, found shift-reduce conflict.";
	public static final String NotSLR1GrammarRR = "Not a SLR(1) grammar, found reduce-reduce conflict.";

	public GrammarDescriptorException() {
		super();
	}

	public GrammarDescriptorException(String msg) {
		super(msg);
	}
}
