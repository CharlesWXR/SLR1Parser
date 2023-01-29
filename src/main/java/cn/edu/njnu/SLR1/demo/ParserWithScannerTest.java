package cn.edu.njnu.SLR1.demo;

import cn.edu.njnu.SLR1.demo.element.Word;
import cn.edu.njnu.SLR1.demo.wordscanner.Scanner;
import cn.edu.njnu.SLR1.generator.grammarelement.GrammarContent;
import cn.edu.njnu.SLR1.generator.grammarelement.Symbol;
import cn.edu.njnu.SLR1.generator.scanner.GrammarProcessor;
import cn.edu.njnu.SLR1.generator.scanner.GrammarScanner;
import cn.edu.njnu.SLR1.generator.scanner.TableDriver;

import java.io.*;

public class ParserWithScannerTest {
	public static void main(String[] args) {
		GrammarScanner g = new GrammarScanner();
		Scanner wordScanner = new Scanner();
		String s = "1+3.3*(1+3)";
		wordScanner.appendBuffer(s);

		try {
			FileInputStream in = new FileInputStream(new File("SLR1.txt"));
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader bufferReader = new BufferedReader(reader);

			String buffer;
			StringBuilder content = new StringBuilder();
			while((buffer = bufferReader.readLine()) != null) {
				content.append(buffer + "\n");
			}
			bufferReader.close();
			g.scan(content.toString());


			GrammarProcessor processor = new GrammarProcessor();
			processor.process(g.getContent());

			TableDriver driver = new TableDriver(processor);
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
