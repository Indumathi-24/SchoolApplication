package com.school.exception;

import org.springframework.http.ResponseEntity;

public class ServiceException extends Exception{
	
	public ServiceException(String msg)
	{
		super(msg);
	}

}
