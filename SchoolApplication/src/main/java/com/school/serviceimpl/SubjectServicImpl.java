package com.school.serviceimpl;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.school.exception.ConstraintViolationException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectService;

@Service
public class SubjectServicImpl implements SubjectService{

	static Logger logger = Logger.getLogger("SubjectServiceImpl.class");
	@Autowired
	private SubjectRepository subjectRepository;
	
	
	@Override
	public String addSubject(Subject subject) throws ServiceException, NotFoundException 
	{
		logger.debug("In Adding Subject Details...");
		try {
			return subjectRepository.addSubject(subject);
		} 
		catch(DataIntegrityViolationException e)
		{
			throw new ConstraintViolationException("Violating Integrity Constraints, Duplicate Key Entered");
		}
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public 	List<SubjectEntity> getAllSubject() throws  ServiceException
	{
		logger.debug("In Retrieving All Subject Details...");
		try {
			return subjectRepository.getAllSubject();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public 	SubjectEntity getParticularSubject(String code) throws ServiceException, NotFoundException 
	{
		logger.debug("In Retrieving Subject Details...");
		try {
			return subjectRepository.getParticularSubject(code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public SubjectEntity updateSubject(String code,Subject subject) throws ServiceException, NotFoundException
	{
		logger.debug("In Updating Subject Details...");
		try {
			return subjectRepository.updateSubject(code, subject);
		} 
		catch(DataIntegrityViolationException e)
		{
			throw new ConstraintViolationException("Violating Integrity Constraints, Duplicate Key Entered");
		}
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public SubjectEntity deleteSubject(String code) throws ServiceException, NotFoundException 
	{
		logger.debug("In Deleting Subject Details...");
		try {
			return subjectRepository.deleteSubject(code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
