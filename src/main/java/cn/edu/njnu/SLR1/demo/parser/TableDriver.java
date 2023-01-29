package cn.edu.njnu.SLR1.demo.parser;

import cn.edu.njnu.SLR1.generator.exception.DriverException;
import cn.edu.njnu.SLR1.generator.grammarelement.Action;
import cn.edu.njnu.SLR1.generator.grammarelement.Symbol;

import java.util.Stack;

public class TableDriver {
	private Table table = new Table();
	private Stack<Integer> stateStack = new Stack<>();
	private Stack<Symbol> symbolStack = new Stack<>();

	public TableDriver() {
		init();
	}

	public void init() {
		this.stateStack.clear();
		this.symbolStack.clear();
		this.stateStack.push(0);
		this.symbolStack.push(Symbol.End);
	}

	public boolean next(Object type) throws Exception {
		Symbol input = this.table.getSymbolFromType(type);
		Action action = this.table.getAction(this.stateStack.peek(), input);

		switch (action.type) {
			case Accept: {
				System.out.println("Accepted!");
				break;
			}
			case Shift: {
				this.symbolStack.push(input);
				this.stateStack.push(action.content);
				break;
			}
			case Reduce: {
				for (int i = 0; i < action.sentence.contents.size(); i++) {
					if (!action.sentence.contents.get(i).equals(Symbol.Empty)) {
						this.stateStack.pop();
						this.symbolStack.pop();
					}
				}
				this.symbolStack.push(action.leftSymbol);
				Action a = this.table.getAction(this.stateStack.peek(), this.symbolStack.peek());
				this.stateStack.push(a.content);
				printReduce(action);

				return false;
			}
			default: {
				throw new DriverException(DriverException.InvalidInput);
			}
		}
		return true;
	}

	private void printReduce(Action action) {
		System.out.print(action.leftSymbol.identifier);
		System.out.print("\t==>\t");
		for (Symbol symbol : action.sentence.contents) {
			System.out.print(symbol.identifier);
		}
		System.out.println("");
	}
}
