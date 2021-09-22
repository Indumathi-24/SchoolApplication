package com.school.service;
import org.springframework.http.ResponseEntity;
import com.school.entity.TeacherLogin;
import com.school.exception.ServiceException;


public interface TeacherLoginService {

	ResponseEntity<String> createLogin( Long id,TeacherLogin login);
	ResponseEntity<?> getLoginDetails(Long id) throws ServiceException ;
	ResponseEntity<String> updateLoginDetails(Long userName,TeacherLogin login) throws ServiceException;
	
	

}
