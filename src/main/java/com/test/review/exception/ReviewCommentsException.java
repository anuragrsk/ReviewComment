package com.test.review.exception;

public class ReviewCommentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251780178841283991L;
	private int errorCode;
	private String message;
	 public ReviewCommentsException() {
	        super();
	    }
	 public ReviewCommentsException(String message) {
	        super(message);
	    }
	 public ReviewCommentsException(String message, Throwable cause) {
	        super(message, cause);
	    }
	 public ReviewCommentsException(String message,int code) {
	        super(message);
	        this.errorCode=code;
	        this.message=message;
	        
	    }
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
