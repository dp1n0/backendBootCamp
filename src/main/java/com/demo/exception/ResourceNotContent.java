package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNotContent extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotContent(String message) {
		super(message);
	}
}
