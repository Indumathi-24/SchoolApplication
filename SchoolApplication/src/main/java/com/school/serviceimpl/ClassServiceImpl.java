package com.school.serviceimpl;
import com.school.exception.DatabaseException;

import com.school.exception.ServiceException;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.school.repository.ClassRepository;
import com.school.service.ClassService;
import com.school.entity.Class;

@Service
public class ClassServiceImpl implements ClassService {
	
	static Logger logger = Logger.getLogger("ClassServiceImpl.class");
	@Autowired
	private ClassRepository classRepository;
	
	@Override
	public Long addClass(Class classEntity) throws ServiceException {
		    logger.debug("In Add Class Details Method");
			try {
				return classRepository.addClass(classEntity);
			} catch (DatabaseException e) {
				throw new ServiceException(e.getMessage());
			}
		
	}
	
	@Override
	public  List<Class> getAllClass() throws ServiceException{
		    logger.debug("In Get All Class Details Method");
			try {
				return classRepository.getAllClass();
			} catch (DatabaseException e) {
				throw new ServiceException(e.getMessage());
			}
		
	}
	
	@Override
	public Class getParticularClass(Long roomNo) throws ServiceException {
		logger.debug("In Get Particular Class Detail Method");
		try {
			return classRepository.getParticularClass(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public Class updateClass(Long roomNo,Class classEntity) throws ServiceException
	{
		logger.debug("In Update Class Details Method");
		try {
			return  classRepository.updateClass(roomNo,classEntity);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
