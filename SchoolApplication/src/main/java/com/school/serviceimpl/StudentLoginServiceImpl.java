package com.school.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.dto.StudentLogin;
import com.school.entity.StudentLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.StudentLoginRepository;
import com.school.repository.StudentRepository;
import com.school.service.StudentLoginService;


@Service
public class StudentLoginServiceImpl implements StudentLoginService{
	static Logger logger = Logger.getLogger("StudenLogintServiceImpl.class");
	@Autowired
	private StudentLoginRepository studentLoginRepository;
	@Autowired
	private StudentRepository studentRepository;
	public Long createLogin(Long rollNo,StudentLogin login) throws ServiceException, NotFoundException
	{
		logger.debug("In Adding Student Login Details");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return studentLoginRepository.createLogin(rollNo,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public StudentLoginEntity getLoginDetails(Long rollNo) throws ServiceException, NotFoundException {
		logger.debug("In Retrieving Student Login Details");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return studentLoginRepository.getLoginDetails(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public Integer updateLoginDetails(Long rollNo,StudentLogin login) throws ServiceException, NotFoundException  {
		logger.debug("In Updating Student Login Details");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return studentLoginRepository.updateLoginDetails(rollNo,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Long getParticularLoginDetails(Long autoId) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving Student Login Id...");
		try {
			return studentLoginRepository.getParticularLoginDetails(autoId);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
