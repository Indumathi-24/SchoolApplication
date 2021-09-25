package com.school.service;

import com.school.dto.TeacherLogin;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface TeacherLoginService {

	Long createLogin( Long id,TeacherLogin login) throws NotFoundException, ServiceException;
	TeacherLoginEntity getLoginDetails(Long id) throws ServiceException, NotFoundException ;
	Integer updateLoginDetails(Long userName,TeacherLogin login) throws ServiceException, NotFoundException;
	
	

}
