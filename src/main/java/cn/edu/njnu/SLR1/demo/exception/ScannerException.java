package cn.edu.njnu.SLR1.demo.exception;

public class ScannerException extends Exception {
	public static final String NoMatch = "No matching state found";

	private String unaccepted = "";

	public ScannerException(String message, String unaccepted) {
		super(message);
		this.unaccepted = unaccepted;
	}

	public ScannerException(String message) {
		super(message);
	}

	public String getUnaccepted() {
		return this.unaccepted;
	}
}
