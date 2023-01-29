package cn.edu.njnu.SLR1.generator.grammarelement;

import java.util.*;

public class LRNode {
	public int ID;
	public List<Symbol> leftSymbols = new ArrayList<Symbol>();
	public List<GrammarSentence> sentences = new ArrayList<GrammarSentence>();
	public List<Integer> reduceTarget = new ArrayList<Integer>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LRNode lrNode = (LRNode) o;

		if (this.sentences.size() != lrNode.sentences.size())
			return false;

		if (this.leftSymbols.size() != lrNode.leftSymbols.size())
			return false;

		return this.sentences.containsAll(lrNode.sentences) && this.leftSymbols.containsAll(lrNode.leftSymbols);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		for (GrammarSentence sentence : this.sentences) {
			hash += sentence.hashCode();
		}
		return hash;
	}

	@Override
	public String toString() {
		return "LRNode{" +
				"ID=" + ID +
				", leftSymbols=" + leftSymbols +
				", sentences=" + sentences +
				", reduceTarget=" + reduceTarget +
				'}';
	}
}
