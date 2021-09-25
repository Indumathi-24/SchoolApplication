package com.school.service;
import com.school.dto.StudentLogin;
import com.school.entity.StudentLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface StudentLoginService {
	Long createLogin( Long id,StudentLogin login) throws ServiceException, NotFoundException;
    StudentLoginEntity  getLoginDetails(Long id) throws ServiceException, NotFoundException ;
	Integer updateLoginDetails(Long userName,StudentLogin student) throws ServiceException, NotFoundException;
	Long getParticularLoginDetails(Long autoId) throws ServiceException, NotFoundException;
}
