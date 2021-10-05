package com.school.exception;

public class ClassAlreadyExistException extends NotFoundException{

	public ClassAlreadyExistException(String message)
	{
		super(message);
	}
}
