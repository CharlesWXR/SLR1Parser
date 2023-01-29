import cn.edu.njnu.SLR1.generator.scanner.GrammarScanner;
import org.junit.Test;

import java.io.*;

public class ScannerTest {
	@Test
	public void testScanner() {
		GrammarScanner g = new GrammarScanner();
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
			System.out.println(g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
