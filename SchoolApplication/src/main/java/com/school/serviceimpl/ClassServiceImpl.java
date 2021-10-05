package com.school.serviceimpl;
import com.school.exception.ClassAlreadyExistException;
import com.school.exception.ConstraintViolationException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.school.repository.ClassRepository;
import com.school.service.ClassService;
import com.school.entity.ClassEntity;
import com.school.dto.Class;

@Service
public class ClassServiceImpl implements ClassService {
	
	static Logger logger = Logger.getLogger("ClassServiceImpl.class");
	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public Long addClass(Class classEntity) throws ServiceException, NotFoundException {
		    logger.debug("In Add Class Details Method");
			try {
				return classRepository.addClass(classEntity);
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
	public  List<ClassEntity> getAllClass() throws ServiceException{
		    logger.debug("In Get All Class Details Method");
			try {
				return classRepository.getAllClass();
			} catch (DatabaseException e) {
				throw new ServiceException(e.getMessage());
			}
		
	}
	
	@Override
	public ClassEntity getParticularClass(Long roomNo) throws ServiceException, NotFoundException {
		logger.debug("In Get Particular Class Detail Method");
		try {
			return classRepository.getParticularClass(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public ClassEntity updateClass(Long roomNo,Class classDetail) throws ServiceException, NotFoundException
	{
		logger.debug("In Update Class Details Method");
		try {
			return  classRepository.updateClass(roomNo,classDetail);
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
	public Long getRoomNo(String standard,String section) throws ServiceException
	{
		logger.debug("In Retrieving Class Room No's Method");
		try {
			return  classRepository.getRoomNo(standard,section);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
