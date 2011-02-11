package net.sf.wlogging;

public class PaintMessageException extends Exception {

	String message = "";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaintMessageException(String s) {
		super(s);
		message = s;
	}

	public String getMessage() {
		return message;
	}

}
