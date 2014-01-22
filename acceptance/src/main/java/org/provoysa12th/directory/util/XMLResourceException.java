package org.provoysa12th.directory.util;

public class XMLResourceException extends RuntimeException {

	private static final long serialVersionUID = 5348678240941284325L;

	public XMLResourceException() {
	}

	public XMLResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XMLResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLResourceException(String message) {
		super(message);
	}

	public XMLResourceException(Throwable cause) {
		super(cause);
	}

}
