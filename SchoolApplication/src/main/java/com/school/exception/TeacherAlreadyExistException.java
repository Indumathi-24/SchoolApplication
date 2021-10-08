package com.school.exception;

public class TeacherAlreadyExistException extends NotFoundException{
	
	public TeacherAlreadyExistException(String message)
	{
		super(message);
	}

}
