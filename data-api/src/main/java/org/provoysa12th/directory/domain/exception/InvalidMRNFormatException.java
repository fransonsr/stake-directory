package org.provoysa12th.directory.domain.exception;

public class InvalidMRNFormatException extends RuntimeException {

	private static final long serialVersionUID = 260996760687133372L;

	public InvalidMRNFormatException() {
	}

	public InvalidMRNFormatException(String message) {
		super(message);
	}

	public InvalidMRNFormatException(Throwable cause) {
		super(cause);
	}

	public InvalidMRNFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
