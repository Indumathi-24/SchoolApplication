package com.school.repository;

import org.springframework.http.ResponseEntity;
import com.school.entity.TeacherLogin;
import com.school.exception.DatabaseException;

public interface TeacherLoginRepository {

	 ResponseEntity<String> createLogin(Long id,TeacherLogin login);
	 ResponseEntity<?> getLoginDetails(Long id) throws DatabaseException;
	 ResponseEntity<String> updateLoginDetails(Long id,TeacherLogin login) throws DatabaseException;
		

}
