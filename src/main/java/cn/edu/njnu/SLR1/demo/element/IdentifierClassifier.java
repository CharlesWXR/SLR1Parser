package cn.edu.njnu.SLR1.demo.element;


import cn.edu.njnu.SLR1.generator.wordelement.TypeEnum;

public class IdentifierClassifier {
	public static Object classify(String _content) {
		switch (_content) {
		case "auto":
			return new Word(ReservedEnum.Auto, _content);
		case "break":
			return new Word(ReservedEnum.Break, _content);
		case "case":
			return new Word(ReservedEnum.Case, _content);
		case "char":
			return new Word(ReservedEnum.Char, _content);
		case "const":
			return new Word(ReservedEnum.Const, _content);
		case "continue":
			return new Word(ReservedEnum.Continue, _content);
		case "default":
			return new Word(ReservedEnum.Default, _content);
		case "do":
			return new Word(ReservedEnum.Do, _content);
		case "double":
			return new Word(ReservedEnum.Double, _content);
		case "else":
			return new Word(ReservedEnum.Else, _content);
		case "enum":
			return new Word(ReservedEnum.Enum, _content);
		case "extern":
			return new Word(ReservedEnum.Extern, _content);
		case "float":
			return new Word(ReservedEnum.Float, _content);
		case "for":
			return new Word(ReservedEnum.For, _content);
		case "goto":
			return new Word(ReservedEnum.Goto, _content);
		case "if":
			return new Word(ReservedEnum.If, _content);
		case "int":
			return new Word(ReservedEnum.Int, _content);
		case "long":
			return new Word(ReservedEnum.Long, _content);
		case "register":
			return new Word(ReservedEnum.Register, _content);
		case "return":
			return new Word(ReservedEnum.Return, _content);
		case "short":
			return new Word(ReservedEnum.Short, _content);
		case "signed":
			return new Word(ReservedEnum.Signed, _content);
		case "sizeof":
			return new Word(ReservedEnum.Sizeof, _content);
		case "static":
			return new Word(ReservedEnum.Static, _content);
		case "struct":
			return new Word(ReservedEnum.Struct, _content);
		case "switch":
			return new Word(ReservedEnum.Switch, _content);
		case "typedef":
			return new Word(ReservedEnum.Typedef, _content);
		case "unsigned":
			return new Word(ReservedEnum.Unsigned, _content);
		case "union":
			return new Word(ReservedEnum.Union, _content);
		case "void":
			return new Word(ReservedEnum.Void, _content);
		case "volatile":
			return new Word(ReservedEnum.Volatile, _content);
		case "while":
			return new Word(ReservedEnum.While, _content);
		default: {
			return new Word(TypeEnum.Identifier, _content);
		}
		}
	}
}
