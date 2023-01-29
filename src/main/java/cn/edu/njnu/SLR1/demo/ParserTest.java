package cn.edu.njnu.SLR1.demo;

import cn.edu.njnu.SLR1.demo.element.Word;
import cn.edu.njnu.SLR1.demo.parser.TableDriver;
import cn.edu.njnu.SLR1.demo.wordscanner.Scanner;
import cn.edu.njnu.SLR1.generator.grammarelement.Symbol;


import java.io.*;

public class ParserTest {
	public static void main(String[] args) {
		Scanner wordScanner = new Scanner();
		String s = "1+3.3*(1+3)";
		wordScanner.appendBuffer(s);

		try {
			TableDriver driver = new TableDriver();
			boolean getNext = false;

			Word w = (Word)(wordScanner.getNext());
			while (!wordScanner.isEnd() || (wordScanner.isEnd() == true && getNext == false)) {
				if (getNext) {
					w = (Word)(wordScanner.getNext());
				}
				getNext = driver.next(w.getType());
			}
			while (!driver.next(Symbol.End.type));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
