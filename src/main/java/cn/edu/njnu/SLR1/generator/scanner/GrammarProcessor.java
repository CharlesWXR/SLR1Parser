package cn.edu.njnu.SLR1.generator.scanner;

import cn.edu.njnu.SLR1.generator.exception.GrammarDescriptorException;
import cn.edu.njnu.SLR1.generator.grammarelement.*;
import cn.edu.njnu.SLR1.generator.wordelement.ActionEnum;
import cn.edu.njnu.SLR1.generator.wordelement.TypeEnum;

import java.util.*;
import java.util.stream.Collectors;

public class GrammarProcessor {
	private Map<Long, Action> actionsTable = new HashMap<Long, Action>();
	private Map<Long, Action> gotoTable = new HashMap<Long, Action>();
	private Map<String, Integer> nonTerminalMapper = new HashMap<String, Integer>();
	private Map<Object, Integer> terminalMapper = new HashMap<Object, Integer>();
	private Map<Object, Symbol> reversedSymbolMapper = new HashMap<Object, Symbol>();
	private Symbol beginning;

	public void process(GrammarContent content) throws Exception {
		// Copy the lex analyse result
		this.beginning = content.getBeginning();

		// Assign a symbol with a number for quick query
		List<Symbol> terminals = content.getTerminalSymbols();
		for (int i = 0; i < terminals.size(); i++) {
			this.terminalMapper.put(terminals.get(i).type, i);
		}
		this.terminalMapper.put(Symbol.End.type, this.terminalMapper.size());

		List<Symbol> nonTerminals = content.getNonTerminalSymbols();
		for (int i = 0; i < nonTerminals.size(); i++) {
			this.nonTerminalMapper.put(nonTerminals.get(i).identifier, i);
		}

		processReverseMapper(content);
		processGrammar(content.getGrammar());
	}

	private void processReverseMapper(GrammarContent gc) {
		List<Symbol> nonTerminals = gc.getNonTerminalSymbols();
		for (Symbol symbol : nonTerminals)
			this.reversedSymbolMapper.put(symbol.type, symbol);

		List<Symbol> terminals = gc.getTerminalSymbols();
		for (Symbol symbol : terminals)
			this.reversedSymbolMapper.put(symbol.type, symbol);
	}

	private void processGrammar(Map<String, List<GrammarSentence>> grammar) throws Exception {
		Map<Integer, List<Edge>> edges = new HashMap<Integer, List<Edge>>();

		List<LRNode> processResult = new ArrayList<LRNode>();
		LRNode beginningNode = new LRNode();
		beginningNode.ID = 0;
		beginningNode.sentences.addAll(grammar.get(this.beginning.identifier));
		beginningNode.leftSymbols.add(this.beginning);
		processResult.add(beginningNode);

		int nodeIndex = 1;

		for (int i = 0; i < processResult.size(); i++) {
			LRNode present = processResult.get(i);
			// convert from set to array in convenience of index iterator
			List<GrammarSentence> sentences = present.sentences;
			List<Symbol> left = present.leftSymbols;

			for (int j = 0; j < sentences.size(); j++) {
				GrammarSentence sentence = sentences.get(j);
				if (sentence.pos == sentence.length
						||
						(sentence.contents.get(sentence.pos).equals(Symbol.Empty)
								&& sentence.pos == sentence.length - 1)
				) {
					present.reduceTarget.add(j);
					continue;
				}

				Symbol presentSymbol = sentence.contents.get(sentence.pos);

				if (!presentSymbol.isTerminal) {
					// Expand present node
					List<GrammarSentence> expanded = grammar.get(presentSymbol.identifier);
					if (null == expanded)
						continue;
					sentences.addAll(expanded);
					for (int count = 0; count < expanded.size(); count++) {
						left.add(presentSymbol);
					}
				}

				// Link to other nodes
				GrammarSentence newSentence = new GrammarSentence(sentence);
				newSentence.pos++;

				if (!edges.containsKey(present.ID))
					edges.put(present.ID, new ArrayList<Edge>());
				List<Edge> edge = edges.get(present.ID);

				int destEdge = findEdge(edge, presentSymbol);
				if (-1 == destEdge) {
					// Edge not exist, check node whether exist
					edges.get(present.ID).add(new Edge(nodeIndex, presentSymbol));

					LRNode newNode = new LRNode();
					newNode.ID = nodeIndex;
					nodeIndex++;
					newNode.sentences.add(newSentence);
					newNode.leftSymbols.add(left.get(j));

					processResult.add(newNode);
				} else {
					// Node exists, insert the rule
					LRNode tar = processResult.get(destEdge);
					if (!tar.sentences.contains(newSentence)) {
						tar.sentences.add(newSentence);
						tar.leftSymbols.add(left.get(j));
					}
				}
			}

			// Drop duplicate node when the node is formed
			for (int index = 0; index < i; index++) {
				LRNode targetNode = processResult.get(index);
				if (present.equals(targetNode)) {
					// Remove generated nodes and edges
					int presentID = present.ID;
					List<Edge> e = edges.get(presentID);

					if (e != null) {
						int finalI = i;
						List<Integer> remove = e.stream()
								.map(t -> t.getEnd())
								.filter(t -> t > finalI)
								.sorted()
								.collect(Collectors.toList());

						processResult = processResult.stream()
								.filter(t -> !remove.contains(t.ID))
								.collect(Collectors.toList());
					}

					int targetID = targetNode.ID;
					for (Map.Entry<Integer, List<Edge>> entry : edges.entrySet()) {
						edges.put(entry.getKey(),
								entry.getValue().stream()
										.peek(t -> {
											if (t.getEnd() == presentID)
												t.setEnd(targetID);
										})
										.distinct()
										.collect(Collectors.toList()));
					}
					processResult.remove(i);
					edges.remove(presentID);
					i--;
					break;
				}
			}
		}

		getTable(edges, processResult);
	}

	private int findEdge(List<Edge> edges, Symbol onEdge) {
		for (Edge edge : edges) {
			if (edge.getData().equals(onEdge)) {
				return edge.getEnd();
			}
		}
		return -1;
	}

	public long getID(int state, Symbol input) {
		if (input.isTerminal) {
			if (this.terminalMapper.get(input.type) == null)
				return -1;
			return (long) state << 32 | (long) this.terminalMapper.get(input.type);
		} else {
			if (this.nonTerminalMapper.get(input.identifier) == null)
				return -1;
			return (long) state << 32 | (long) this.nonTerminalMapper.get(input.identifier);
		}
	}

	private void getTable(Map<Integer, List<Edge>> edges, List<LRNode> processResult) throws Exception {
		for (Map.Entry<Integer, List<Edge>> entry : edges.entrySet()) {
			int index = entry.getKey();
			LRNode node = processResult.stream().filter(t -> t.ID == index).findFirst().get();
			List<Edge> edge = entry.getValue();
			// Shift and Goto
			for (Edge e : edge) {
				Symbol s = e.getData();
				int end = e.getEnd();
				long id = getID(index, s);
				if (this.actionsTable.get(id) != null)
					throw new GrammarDescriptorException(GrammarDescriptorException.NotSLR1GrammarRR);

				if (s.isTerminal) {
					this.actionsTable.put(id, new Action(end, ActionEnum.Shift));
				} else {
					this.gotoTable.put(id, new Action(end, ActionEnum.Goto));
				}
			}
		}

		for (LRNode node : processResult) {
			// Reduce
			for (Integer reduceIndex : node.reduceTarget) {
				GrammarSentence gs = node.sentences.get(reduceIndex);
				Symbol left = node.leftSymbols.get(reduceIndex);
				Set<Symbol> follow = gs.follows;
				for (Symbol s : follow) {
					long id = getID(node.ID, s);
					if (this.actionsTable.get(id) != null)
						throw new GrammarDescriptorException(GrammarDescriptorException.NotSLR1GrammarSR);

					if (s.equals(Symbol.End) && left.equals(this.beginning))
						this.actionsTable.put(id, new Action(-1, ActionEnum.Accept));
					else
						this.actionsTable.put(id, new Action(-1, ActionEnum.Reduce, left, gs));
				}
			}
		}
	}

	public Symbol getSymbolFromType(Object type) {
		if (type == TypeEnum.Empty)
			return Symbol.Empty;
		if (type == TypeEnum.End)
			return Symbol.End;

		return this.reversedSymbolMapper.get(type);
	}

	public Action getAction(int state, Symbol input) {
		Action res;
		if (input.isTerminal) {
			res = this.actionsTable.get(getID(state, input));
		} else {
			res = this.gotoTable.get(getID(state, input));
		}
		return res == null ?
				new Action(-1, ActionEnum.Error) : res;
	}

	public Map<String, Object> toInitMap() {
		Map<String, Object> res = new HashMap<>();
		res.put("actionTableInit", toStringKey(this.actionsTable));
		res.put("gotoTableInit", toStringKey(this.gotoTable));
		res.put("nonTerminalMapperInit", this.nonTerminalMapper);
		res.put("terminalMapperInit", toStringKey(this.terminalMapper));
		res.put("reversedSymbolMapperInit", toStringKey(this.reversedSymbolMapper));
		res.put("beginning", this.beginning);
		return res;
	}

	private <K, V> Map<String, V> toStringKey(Map<K, V> target) {
		Map<String, V> res = new HashMap<String, V>();
		for (Map.Entry<K, V> entry : target.entrySet()) {
			if (entry.getKey() == null)
				continue;
			res.put(entry.getKey().toString(), entry.getValue());
		}
		return res;
	}
}
