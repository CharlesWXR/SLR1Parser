package cn.edu.njnu.SLR1.demo.wordscanner;

import cn.edu.njnu.SLR1.demo.exception.ScannerException;

public class Scanner {
	// The manual driver for auto-generated WordScanner by previous program MiniCLex
	private int index = 0;
	private StringBuffer buffer = new StringBuffer();
	private WordScanner wordscanner = new WordScanner();

	public void appendBuffer(String s) {
		this.buffer.append(s);
	}

	public void clear() {
		this.buffer.setLength(0);
		this.index = 0;
	}

	public boolean isEnd() {
		return this.buffer.length() <= this.index;
	}

	public Object getNext() throws Exception {
		int length = this.buffer.length();
		wordscanner.init();

		if (this.index >= length) {
			throw new ScannerException(ScannerException.NoMatch);
		}

		while (this.index < length) {
			String s = this.wordscanner.next(this.buffer.charAt(this.index));
			if (s != null) {
				this.index -= s.length();
				this.buffer.delete(0, this.index + 1);
				this.index = 0;
				return this.wordscanner.execute();
			}
			this.index++;
		}

		if (this.wordscanner.hasMatched()) {
			String s = this.wordscanner.getUnMatched();
			this.index = 0;
			this.buffer.setLength(0);
			this.buffer.append(s);
			return this.wordscanner.execute();
		}

		// Clear the unmatched buffer
		String unmatched = this.wordscanner.getUnMatched();
		if (unmatched != null && unmatched.length() > 0) {
			wordscanner.init();
			for (int i = 0; i < unmatched.length(); i++) {
				String s = this.wordscanner.next(unmatched.charAt(i));
				if (s != null) {
					i -= s.length();
					return this.wordscanner.execute();
				}
			}
		}

		throw new ScannerException(ScannerException.NoMatch);
	}
}
