package com.bestpharma.exception;
/**
 * @author PPaswan
 *
 */
public class BestPharmaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	private String message;

	public BestPharmaException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
