package com.rest.template.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5024750417733656514L;
	
	public ValidationException() {
		super();
	}

	public ValidationException(BindingResult result) {
		super(getErrors(result));		
	}
	
	private static String getErrors(BindingResult result) {
		StringBuilder errors = new StringBuilder();
		
		String delim = "";
		errors.append("[");
		for (FieldError error : result.getFieldErrors()) {
			errors.append(delim).append("{");
			errors.append(error.getField());
			errors.append(":");
			errors.append(error.getDefaultMessage());
			errors.append("}");
			delim = ",";
		}
		errors.append("],");
		
		delim = "";
		errors.append("[");
		for (ObjectError error : result.getGlobalErrors()) {
			errors.append(delim).append("{");
			errors.append(error.getObjectName());
			errors.append(":");
			errors.append(error.getDefaultMessage());
			errors.append("}");
			delim = ",";
		}
		errors.append("] ");
		
		return errors.toString();
	}


}
