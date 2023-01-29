package cn.edu.njnu.SLR1.generator.exception;

public class DriverException extends Exception {
	public static final String InvalidInput = "Fail to find a correspond rule to reduce.";

	public DriverException() {
		super();
	}

	public DriverException(String msg) {
		super(msg);
	}
}