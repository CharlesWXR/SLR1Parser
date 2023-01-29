package cn.edu.njnu.SLR1.demo;

import cn.edu.njnu.SLR1.generator.Generator;

import java.io.*;

public class AutoGenerate {
	public static void main(String[] args) {
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

			Generator generator = new Generator();
			generator.generate(content.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
