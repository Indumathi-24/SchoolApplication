package com.school.exception;

public class ConstraintViolationException extends NotFoundException{
	
	public ConstraintViolationException(String message)
	{
		super(message);
	}

}
