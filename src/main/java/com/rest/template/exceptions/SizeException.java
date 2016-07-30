package com.rest.template.exceptions;

public class SizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2010751479162968960L;
	
	public SizeException(int i) {
		super("Word size must not be greater then " + i + ".");
	}

}
