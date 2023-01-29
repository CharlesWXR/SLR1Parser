package ${packageName};

import cn.edu.njnu.SLR1.generator.exception.GrammarDescriptorException;
import cn.edu.njnu.SLR1.generator.grammarelement.*;
import cn.edu.njnu.SLR1.generator.wordelement.ActionEnum;
import cn.edu.njnu.SLR1.generator.wordelement.TypeEnum;

import java.util.*;
import java.util.stream.Collectors;

<#list dependencies as dependency>
import static ${dependency}.*;
</#list>
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
    <#list actionTableInit as key, value>
		Action t_${key} = new Action();
        t_${key}.content = ${value.content};
 		t_${key}.type = ${value.type};
		<#if value.leftSymbol??>
        t_${key}.leftSymbol = new Symbol(${value.leftSymbol.isTerminal?string("true","false")}, "${value.leftSymbol.identifier}", ${value.leftSymbol.type!"null"});
		</#if>
		<#if value.sentence?? && value.sentence.contents??>
 		t_${key}.sentence = new GrammarSentence();
			<#list value.sentence.contents as symbol>
        t_${key}.sentence.contents.add(new Symbol(${symbol.isTerminal?string("true","false")}, "${symbol.identifier}", ${symbol.type!"null"}));
			</#list>
		</#if>
        actionTable.put(${key}L, t_${key});

    </#list>
		// Generate goto table insertion
    <#list gotoTableInit as key, value>
        Action t1_${key} = new Action();
        t1_${key}.content = ${value.content};
        t1_${key}.type = ${value.type};
		<#if value.leftSymbol??>
        t1_${key}.leftSymbol = new Symbol(${value.leftSymbol.isTerminal?string("true","false")}, "${value.leftSymbol.identifier}", ${value.leftSymbol.type!"null"});
        </#if>
		<#if value.sentence??>
        t1_${key}.sentence = new GrammarSentence();
			<#list value.sentence.contents as symbol>
        t1_${key}.sentence.contents.add(new Symbol(${symbol.isTerminal?string("true","false")}, "${symbol.identifier}", ${symbol.type!"null"}));
			</#list>
		</#if>
        gotoTable.put(${key}L, t1_${key});

	</#list>
		// Generate nonTerminalMapper insertion
    <#list nonTerminalMapperInit as key, value>
        nonTerminalMapper.put("${key}", ${value});
	</#list>
		// Generate nonTerminalMapper insertion
    <#list terminalMapperInit as key, value>
        terminalMapper.put(${key}, ${value});
	</#list>
		// Generate reversedSymbolMapper insertion
	<#list reversedSymbolMapperInit as key, value>
        reversedSymbolMapper.put(${key}, new Symbol(${value.isTerminal?string("true","false")}, "${value.identifier}", ${value.type!"null"}));
	</#list>
		// Generate begging initializer
		beginning = new Symbol(${beginning.isTerminal?string("true","false")}, "${beginning.identifier}", ${beginning.type!"null"});

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