package com.school.service;
import com.school.entity.StudentLogin;
import com.school.exception.ServiceException;


public interface StudentLoginService {
	Long createLogin( Long id,StudentLogin login) throws ServiceException;
    StudentLogin  getLoginDetails(Long id) throws ServiceException ;
	Integer updateLoginDetails(Long userName,StudentLogin student) throws ServiceException ;
}
