package com.school.exception;

import org.springframework.http.ResponseEntity;

public class DatabaseException extends Exception {
       public DatabaseException(String msg)
       {
    	   super(msg);
       }

	public DatabaseException(ResponseEntity responseEntity) {
	}
}
