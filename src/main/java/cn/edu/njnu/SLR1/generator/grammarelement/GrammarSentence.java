package cn.edu.njnu.SLR1.generator.grammarelement;

import cn.edu.njnu.SLR1.generator.utils.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GrammarSentence {
	// Simply to attach a rule to select-set for LL1table process
	public List<Symbol> contents = new ArrayList<Symbol>();
	public Set<Symbol> follows = new HashSet<Symbol>();
	public int pos = 0;
	public int length = 0;

	public boolean add(Symbol s) {
		return contents.add(s);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GrammarSentence that = (GrammarSentence) o;

		return pos == that.pos && this.length == that.length && contents.containsAll(that.contents);
	}

	@Override
	public String toString() {
		return "GrammarSentence{" +
				"contents=" + contents +
				", follows=" + follows +
				", pos=" + pos +
				", length=" + length +
				'}';
	}

	public GrammarSentence() {
	}

	public GrammarSentence(GrammarSentence gs) {
		this.contents = gs.contents.stream().collect(Collectors.toList());
		this.length = gs.length;
		this.follows = gs.follows.stream().collect(Collectors.toSet());
		this.pos = gs.pos;
	}

	@Override
	public int hashCode() {
		return (length + follows.size()) << (pos & 0x7);
	}

	public List<Symbol> getContents() {
		return contents;
	}

	public void setContents(List<Symbol> contents) {
		this.contents = contents;
	}

	public Set<Symbol> getFollows() {
		return follows;
	}

	public void setFollows(Set<Symbol> follows) {
		this.follows = follows;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
