package io.app.exception;

public class WrongAccountNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongAccountNumberException(String message){
		super(message);
	}
	
	

}
