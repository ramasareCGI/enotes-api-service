package com.service.demo.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Object> error;

	public ValidationException(Map<String, Object> error) {
		super("validation faild");
		this.error = error;
	}

	public Map<String, Object> getError() {
		return error;
	}

}
