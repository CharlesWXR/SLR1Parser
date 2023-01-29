package cn.edu.njnu.SLR1.demo.parser;

import cn.edu.njnu.SLR1.generator.exception.GrammarDescriptorException;
import cn.edu.njnu.SLR1.generator.grammarelement.*;
import cn.edu.njnu.SLR1.generator.wordelement.ActionEnum;
import cn.edu.njnu.SLR1.generator.wordelement.TypeEnum;

import java.util.*;
import java.util.stream.Collectors;

import static cn.edu.njnu.SLR1.demo.element.OperatorEnum.*;
import static cn.edu.njnu.SLR1.generator.wordelement.TypeEnum.*;
import static cn.edu.njnu.SLR1.generator.wordelement.ActionEnum.*;

/*
 * Auto-generated SLR1 Table class
 */

public class Table {
    private static Map<Long, Action> actionTable = new HashMap<Long, Action>();
    private static Map<Long, Action> gotoTable = new HashMap<Long, Action>();
    private static Map<String, Integer> nonTerminalMapper = new HashMap<String, Integer>();
    private static Map<Object, Integer> terminalMapper = new HashMap<Object, Integer>();
    private static Map<Object, Symbol> reversedSymbolMapper = new HashMap<Object, Symbol>();
    private static Symbol beginning;

    static {
    	// Generate action table insertion
		Action t_42949672964 = new Action();
        t_42949672964.content = 4;
 		t_42949672964.type = Shift;
        actionTable.put(42949672964L, t_42949672964);

		Action t_25769803781 = new Action();
        t_25769803781.content = -1;
 		t_25769803781.type = Reduce;
        t_25769803781.leftSymbol = new Symbol(false, "F", null);
 		t_25769803781.sentence = new GrammarSentence();
        t_25769803781.sentence.contents.add(new Symbol(true, "double", Double));
        actionTable.put(25769803781L, t_25769803781);

		Action t_42949672961 = new Action();
        t_42949672961.content = 6;
 		t_42949672961.type = Shift;
        actionTable.put(42949672961L, t_42949672961);

		Action t_25769803783 = new Action();
        t_25769803783.content = -1;
 		t_25769803783.type = Reduce;
        t_25769803783.leftSymbol = new Symbol(false, "F", null);
 		t_25769803783.sentence = new GrammarSentence();
        t_25769803783.sentence.contents.add(new Symbol(true, "double", Double));
        actionTable.put(25769803783L, t_25769803783);

		Action t_42949672960 = new Action();
        t_42949672960.content = 5;
 		t_42949672960.type = Shift;
        actionTable.put(42949672960L, t_42949672960);

		Action t_111669149703 = new Action();
        t_111669149703.content = -1;
 		t_111669149703.type = Reduce;
        t_111669149703.leftSymbol = new Symbol(false, "F", null);
 		t_111669149703.sentence = new GrammarSentence();
        t_111669149703.sentence.contents.add(new Symbol(true, "(", LBracket));
        t_111669149703.sentence.contents.add(new Symbol(false, "E", null));
        t_111669149703.sentence.contents.add(new Symbol(true, ")", RBracket));
        actionTable.put(111669149703L, t_111669149703);

		Action t_21474836485 = new Action();
        t_21474836485.content = -1;
 		t_21474836485.type = Reduce;
        t_21474836485.leftSymbol = new Symbol(false, "F", null);
 		t_21474836485.sentence = new GrammarSentence();
        t_21474836485.sentence.contents.add(new Symbol(true, "int", Int));
        actionTable.put(21474836485L, t_21474836485);

		Action t_21474836487 = new Action();
        t_21474836487.content = -1;
 		t_21474836487.type = Reduce;
        t_21474836487.leftSymbol = new Symbol(false, "F", null);
 		t_21474836487.sentence = new GrammarSentence();
        t_21474836487.sentence.contents.add(new Symbol(true, "int", Int));
        actionTable.put(21474836487L, t_21474836487);

		Action t_111669149701 = new Action();
        t_111669149701.content = -1;
 		t_111669149701.type = Reduce;
        t_111669149701.leftSymbol = new Symbol(false, "F", null);
 		t_111669149701.sentence = new GrammarSentence();
        t_111669149701.sentence.contents.add(new Symbol(true, "(", LBracket));
        t_111669149701.sentence.contents.add(new Symbol(false, "E", null));
        t_111669149701.sentence.contents.add(new Symbol(true, ")", RBracket));
        actionTable.put(111669149701L, t_111669149701);

		Action t_21474836482 = new Action();
        t_21474836482.content = -1;
 		t_21474836482.type = Reduce;
        t_21474836482.leftSymbol = new Symbol(false, "F", null);
 		t_21474836482.sentence = new GrammarSentence();
        t_21474836482.sentence.contents.add(new Symbol(true, "int", Int));
        actionTable.put(21474836482L, t_21474836482);

		Action t_21474836483 = new Action();
        t_21474836483.content = -1;
 		t_21474836483.type = Reduce;
        t_21474836483.leftSymbol = new Symbol(false, "F", null);
 		t_21474836483.sentence = new GrammarSentence();
        t_21474836483.sentence.contents.add(new Symbol(true, "int", Int));
        actionTable.put(21474836483L, t_21474836483);

		Action t_94489280514 = new Action();
        t_94489280514.content = -1;
 		t_94489280514.type = Reduce;
        t_94489280514.leftSymbol = new Symbol(false, "T1", null);
 		t_94489280514.sentence = new GrammarSentence();
        t_94489280514.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(94489280514L, t_94489280514);

		Action t_94489280515 = new Action();
        t_94489280515.content = 10;
 		t_94489280515.type = Shift;
        actionTable.put(94489280515L, t_94489280515);

		Action t_94489280517 = new Action();
        t_94489280517.content = -1;
 		t_94489280517.type = Reduce;
        t_94489280517.leftSymbol = new Symbol(false, "T1", null);
 		t_94489280517.sentence = new GrammarSentence();
        t_94489280517.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(94489280517L, t_94489280517);

		Action t_17179869185 = new Action();
        t_17179869185.content = 6;
 		t_17179869185.type = Shift;
        actionTable.put(17179869185L, t_17179869185);

		Action t_94489280519 = new Action();
        t_94489280519.content = -1;
 		t_94489280519.type = Reduce;
        t_94489280519.leftSymbol = new Symbol(false, "T1", null);
 		t_94489280519.sentence = new GrammarSentence();
        t_94489280519.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(94489280519L, t_94489280519);

		Action t_17179869188 = new Action();
        t_17179869188.content = 4;
 		t_17179869188.type = Shift;
        actionTable.put(17179869188L, t_17179869188);

		Action t_34359738368 = new Action();
        t_34359738368.content = 5;
 		t_34359738368.type = Shift;
        actionTable.put(34359738368L, t_34359738368);

		Action t_0 = new Action();
        t_0.content = 5;
 		t_0.type = Shift;
        actionTable.put(0L, t_0);

		Action t_73014444037 = new Action();
        t_73014444037.content = -1;
 		t_73014444037.type = Reduce;
        t_73014444037.leftSymbol = new Symbol(false, "E1", null);
 		t_73014444037.sentence = new GrammarSentence();
        t_73014444037.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(73014444037L, t_73014444037);

		Action t_1 = new Action();
        t_1.content = 6;
 		t_1.type = Shift;
        actionTable.put(1L, t_1);

		Action t_34359738369 = new Action();
        t_34359738369.content = 6;
 		t_34359738369.type = Shift;
        actionTable.put(34359738369L, t_34359738369);

		Action t_73014444034 = new Action();
        t_73014444034.content = 8;
 		t_73014444034.type = Shift;
        actionTable.put(73014444034L, t_73014444034);

		Action t_4 = new Action();
        t_4.content = 4;
 		t_4.type = Shift;
        actionTable.put(4L, t_4);

		Action t_25769803779 = new Action();
        t_25769803779.content = -1;
 		t_25769803779.type = Reduce;
        t_25769803779.leftSymbol = new Symbol(false, "F", null);
 		t_25769803779.sentence = new GrammarSentence();
        t_25769803779.sentence.contents.add(new Symbol(true, "double", Double));
        actionTable.put(25769803779L, t_25769803779);

		Action t_30064771077 = new Action();
        t_30064771077.content = -1;
 		t_30064771077.type = Reduce;
        t_30064771077.leftSymbol = new Symbol(false, "E", null);
 		t_30064771077.sentence = new GrammarSentence();
        t_30064771077.sentence.contents.add(new Symbol(false, "T", null));
        t_30064771077.sentence.contents.add(new Symbol(false, "E1", null));
        actionTable.put(30064771077L, t_30064771077);

		Action t_25769803778 = new Action();
        t_25769803778.content = -1;
 		t_25769803778.type = Reduce;
        t_25769803778.leftSymbol = new Symbol(false, "F", null);
 		t_25769803778.sentence = new GrammarSentence();
        t_25769803778.sentence.contents.add(new Symbol(true, "double", Double));
        actionTable.put(25769803778L, t_25769803778);

		Action t_30064771079 = new Action();
        t_30064771079.content = -1;
 		t_30064771079.type = Reduce;
        t_30064771079.leftSymbol = new Symbol(false, "E", null);
 		t_30064771079.sentence = new GrammarSentence();
        t_30064771079.sentence.contents.add(new Symbol(false, "T", null));
        t_30064771079.sentence.contents.add(new Symbol(false, "E1", null));
        actionTable.put(30064771079L, t_30064771079);

		Action t_47244640261 = new Action();
        t_47244640261.content = 26;
 		t_47244640261.type = Shift;
        actionTable.put(47244640261L, t_47244640261);

		Action t_73014444039 = new Action();
        t_73014444039.content = -1;
 		t_73014444039.type = Reduce;
        t_73014444039.leftSymbol = new Symbol(false, "E1", null);
 		t_73014444039.sentence = new GrammarSentence();
        t_73014444039.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(73014444039L, t_73014444039);

		Action t_158913789957 = new Action();
        t_158913789957.content = -1;
 		t_158913789957.type = Reduce;
        t_158913789957.leftSymbol = new Symbol(false, "E1", null);
 		t_158913789957.sentence = new GrammarSentence();
        t_158913789957.sentence.contents.add(new Symbol(true, "+", Plus));
        t_158913789957.sentence.contents.add(new Symbol(false, "T", null));
        t_158913789957.sentence.contents.add(new Symbol(false, "E1", null));
        actionTable.put(158913789957L, t_158913789957);

		Action t_38654705666 = new Action();
        t_38654705666.content = -1;
 		t_38654705666.type = Reduce;
        t_38654705666.leftSymbol = new Symbol(false, "T", null);
 		t_38654705666.sentence = new GrammarSentence();
        t_38654705666.sentence.contents.add(new Symbol(false, "F", null));
        t_38654705666.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(38654705666L, t_38654705666);

		Action t_38654705669 = new Action();
        t_38654705669.content = -1;
 		t_38654705669.type = Reduce;
        t_38654705669.leftSymbol = new Symbol(false, "T", null);
 		t_38654705669.sentence = new GrammarSentence();
        t_38654705669.sentence.contents.add(new Symbol(false, "F", null));
        t_38654705669.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(38654705669L, t_38654705669);

		Action t_158913789959 = new Action();
        t_158913789959.content = -1;
 		t_158913789959.type = Reduce;
        t_158913789959.leftSymbol = new Symbol(false, "E1", null);
 		t_158913789959.sentence = new GrammarSentence();
        t_158913789959.sentence.contents.add(new Symbol(true, "+", Plus));
        t_158913789959.sentence.contents.add(new Symbol(false, "T", null));
        t_158913789959.sentence.contents.add(new Symbol(false, "E1", null));
        actionTable.put(158913789959L, t_158913789959);

		Action t_34359738372 = new Action();
        t_34359738372.content = 4;
 		t_34359738372.type = Shift;
        actionTable.put(34359738372L, t_34359738372);

		Action t_201863462914 = new Action();
        t_201863462914.content = -1;
 		t_201863462914.type = Reduce;
        t_201863462914.leftSymbol = new Symbol(false, "T1", null);
 		t_201863462914.sentence = new GrammarSentence();
        t_201863462914.sentence.contents.add(new Symbol(true, "*", Multiply));
        t_201863462914.sentence.contents.add(new Symbol(false, "F", null));
        t_201863462914.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(201863462914L, t_201863462914);

		Action t_12884901895 = new Action();
        t_12884901895.content = -1;
 		t_12884901895.type = Reduce;
        t_12884901895.leftSymbol = new Symbol(false, "T1", null);
 		t_12884901895.sentence = new GrammarSentence();
        t_12884901895.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(12884901895L, t_12884901895);

		Action t_17179869184 = new Action();
        t_17179869184.content = 5;
 		t_17179869184.type = Shift;
        actionTable.put(17179869184L, t_17179869184);

		Action t_4294967303 = new Action();
        t_4294967303.content = -1;
 		t_4294967303.type = Accept;
        actionTable.put(4294967303L, t_4294967303);

		Action t_12884901890 = new Action();
        t_12884901890.content = -1;
 		t_12884901890.type = Reduce;
        t_12884901890.leftSymbol = new Symbol(false, "T1", null);
 		t_12884901890.sentence = new GrammarSentence();
        t_12884901890.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(12884901890L, t_12884901890);

		Action t_12884901891 = new Action();
        t_12884901891.content = 10;
 		t_12884901891.type = Shift;
        actionTable.put(12884901891L, t_12884901891);

		Action t_8589934599 = new Action();
        t_8589934599.content = -1;
 		t_8589934599.type = Reduce;
        t_8589934599.leftSymbol = new Symbol(false, "E1", null);
 		t_8589934599.sentence = new GrammarSentence();
        t_8589934599.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(8589934599L, t_8589934599);

		Action t_201863462917 = new Action();
        t_201863462917.content = -1;
 		t_201863462917.type = Reduce;
        t_201863462917.leftSymbol = new Symbol(false, "T1", null);
 		t_201863462917.sentence = new GrammarSentence();
        t_201863462917.sentence.contents.add(new Symbol(true, "*", Multiply));
        t_201863462917.sentence.contents.add(new Symbol(false, "F", null));
        t_201863462917.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(201863462917L, t_201863462917);

		Action t_12884901893 = new Action();
        t_12884901893.content = -1;
 		t_12884901893.type = Reduce;
        t_12884901893.leftSymbol = new Symbol(false, "T1", null);
 		t_12884901893.sentence = new GrammarSentence();
        t_12884901893.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(12884901893L, t_12884901893);

		Action t_201863462919 = new Action();
        t_201863462919.content = -1;
 		t_201863462919.type = Reduce;
        t_201863462919.leftSymbol = new Symbol(false, "T1", null);
 		t_201863462919.sentence = new GrammarSentence();
        t_201863462919.sentence.contents.add(new Symbol(true, "*", Multiply));
        t_201863462919.sentence.contents.add(new Symbol(false, "F", null));
        t_201863462919.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(201863462919L, t_201863462919);

		Action t_8589934594 = new Action();
        t_8589934594.content = 8;
 		t_8589934594.type = Shift;
        actionTable.put(8589934594L, t_8589934594);

		Action t_8589934597 = new Action();
        t_8589934597.content = -1;
 		t_8589934597.type = Reduce;
        t_8589934597.leftSymbol = new Symbol(false, "E1", null);
 		t_8589934597.sentence = new GrammarSentence();
        t_8589934597.sentence.contents.add(new Symbol(true, "empty", Empty));
        actionTable.put(8589934597L, t_8589934597);

		Action t_38654705671 = new Action();
        t_38654705671.content = -1;
 		t_38654705671.type = Reduce;
        t_38654705671.leftSymbol = new Symbol(false, "T", null);
 		t_38654705671.sentence = new GrammarSentence();
        t_38654705671.sentence.contents.add(new Symbol(false, "F", null));
        t_38654705671.sentence.contents.add(new Symbol(false, "T1", null));
        actionTable.put(38654705671L, t_38654705671);

		Action t_111669149699 = new Action();
        t_111669149699.content = -1;
 		t_111669149699.type = Reduce;
        t_111669149699.leftSymbol = new Symbol(false, "F", null);
 		t_111669149699.sentence = new GrammarSentence();
        t_111669149699.sentence.contents.add(new Symbol(true, "(", LBracket));
        t_111669149699.sentence.contents.add(new Symbol(false, "E", null));
        t_111669149699.sentence.contents.add(new Symbol(true, ")", RBracket));
        actionTable.put(111669149699L, t_111669149699);

		Action t_111669149698 = new Action();
        t_111669149698.content = -1;
 		t_111669149698.type = Reduce;
        t_111669149698.leftSymbol = new Symbol(false, "F", null);
 		t_111669149698.sentence = new GrammarSentence();
        t_111669149698.sentence.contents.add(new Symbol(true, "(", LBracket));
        t_111669149698.sentence.contents.add(new Symbol(false, "E", null));
        t_111669149698.sentence.contents.add(new Symbol(true, ")", RBracket));
        actionTable.put(111669149698L, t_111669149698);

		// Generate goto table insertion
        Action t1_34359738370 = new Action();
        t1_34359738370.content = 17;
        t1_34359738370.type = Goto;
        gotoTable.put(34359738370L, t1_34359738370);

        Action t1_34359738372 = new Action();
        t1_34359738372.content = 3;
        t1_34359738372.type = Goto;
        gotoTable.put(34359738372L, t1_34359738372);

        Action t1_94489280515 = new Action();
        t1_94489280515.content = 47;
        t1_94489280515.type = Goto;
        gotoTable.put(94489280515L, t1_94489280515);

        Action t1_42949672964 = new Action();
        t1_42949672964.content = 22;
        t1_42949672964.type = Goto;
        gotoTable.put(42949672964L, t1_42949672964);

        Action t1_17179869186 = new Action();
        t1_17179869186.content = 2;
        t1_17179869186.type = Goto;
        gotoTable.put(17179869186L, t1_17179869186);

        Action t1_17179869188 = new Action();
        t1_17179869188.content = 3;
        t1_17179869188.type = Goto;
        gotoTable.put(17179869188L, t1_17179869188);

        Action t1_0 = new Action();
        t1_0.content = 1;
        t1_0.type = Goto;
        gotoTable.put(0L, t1_0);

        Action t1_2 = new Action();
        t1_2.content = 2;
        t1_2.type = Goto;
        gotoTable.put(2L, t1_2);

        Action t1_17179869184 = new Action();
        t1_17179869184.content = 11;
        t1_17179869184.type = Goto;
        gotoTable.put(17179869184L, t1_17179869184);

        Action t1_4 = new Action();
        t1_4.content = 3;
        t1_4.type = Goto;
        gotoTable.put(4L, t1_4);

        Action t1_73014444033 = new Action();
        t1_73014444033.content = 37;
        t1_73014444033.type = Goto;
        gotoTable.put(73014444033L, t1_73014444033);

        Action t1_12884901891 = new Action();
        t1_12884901891.content = 9;
        t1_12884901891.type = Goto;
        gotoTable.put(12884901891L, t1_12884901891);

        Action t1_8589934593 = new Action();
        t1_8589934593.content = 7;
        t1_8589934593.type = Goto;
        gotoTable.put(8589934593L, t1_8589934593);

		// Generate nonTerminalMapper insertion
        nonTerminalMapper.put("T", 2);
        nonTerminalMapper.put("E", 0);
        nonTerminalMapper.put("F", 4);
        nonTerminalMapper.put("$Begin", 5);
        nonTerminalMapper.put("E1", 1);
        nonTerminalMapper.put("T1", 3);
		// Generate nonTerminalMapper insertion
        terminalMapper.put(Empty, 6);
        terminalMapper.put(Multiply, 3);
        terminalMapper.put(End, 7);
        terminalMapper.put(Plus, 2);
        terminalMapper.put(Double, 1);
        terminalMapper.put(Int, 0);
        terminalMapper.put(LBracket, 4);
        terminalMapper.put(RBracket, 5);
		// Generate reversedSymbolMapper insertion
        reversedSymbolMapper.put(Empty, new Symbol(true, "empty", Empty));
        reversedSymbolMapper.put(Multiply, new Symbol(true, "*", Multiply));
        reversedSymbolMapper.put(Plus, new Symbol(true, "+", Plus));
        reversedSymbolMapper.put(Double, new Symbol(true, "double", Double));
        reversedSymbolMapper.put(Int, new Symbol(true, "int", Int));
        reversedSymbolMapper.put(LBracket, new Symbol(true, "(", LBracket));
        reversedSymbolMapper.put(RBracket, new Symbol(true, ")", RBracket));
		// Generate begging initializer
		beginning = new Symbol(false, "$Begin", null);

	}

	private long getID(int state, Symbol input) {
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
			res = this.actionTable.get(getID(state, input));
		} else {
			res = this.gotoTable.get(getID(state, input));
		}
		return res == null ?
				new Action(-1, ActionEnum.Error) : res;
	}
}