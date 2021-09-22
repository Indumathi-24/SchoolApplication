package com.school.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.entity.StudentLogin;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.StudentLoginRepository;
import com.school.service.StudentLoginService;


@Service
public class StudentLoginServiceImpl implements StudentLoginService{
	static Logger logger = Logger.getLogger("StudenLogintServiceImpl.class");
	@Autowired
	private StudentLoginRepository StudentLoginRepository;
	public Long createLogin(Long id,StudentLogin login) throws ServiceException
	{
		logger.debug("In Adding Student Login Details");
		try {
			return StudentLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public StudentLogin getLoginDetails(Long id) throws ServiceException {
		logger.debug("In Retrieving Student Login Details");
		try {
			return StudentLoginRepository.getLoginDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public Integer updateLoginDetails(Long id,StudentLogin login) throws ServiceException  {
		logger.debug("In Updating Student Login Details");
		try {
			return StudentLoginRepository.updateLoginDetails(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
