package cn.edu.njnu.SLR1.generator.grammarelement;

import cn.edu.njnu.SLR1.generator.wordelement.ActionEnum;

public class Action {
	public int content;
	public ActionEnum type;
	public Symbol leftSymbol;
	public GrammarSentence sentence;

	public Action() {
	}

	@Override
	public String toString() {
		return "Action{" +
				"content=" + content +
				", type=" + type +
				", leftSymbol=" + leftSymbol +
				", sentence=" + sentence +
				'}';
	}

	public Action(int content, ActionEnum type) {
		this.content = content;
		this.type = type;
	}

	public Action(int content, ActionEnum type, Symbol leftSymbol, GrammarSentence sentence) {
		this.content = content;
		this.type = type;
		this.leftSymbol = leftSymbol;
		this.sentence = sentence;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public ActionEnum getType() {
		return type;
	}

	public void setType(ActionEnum type) {
		this.type = type;
	}

	public Symbol getLeftSymbol() {
		return leftSymbol;
	}

	public void setLeftSymbol(Symbol leftSymbol) {
		this.leftSymbol = leftSymbol;
	}

	public GrammarSentence getSentence() {
		return sentence;
	}

	public void setSentence(GrammarSentence sentence) {
		this.sentence = sentence;
	}
}
