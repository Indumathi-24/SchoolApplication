package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.ConstraintViolationException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	static Logger logger = Logger.getLogger("TeacherServiceImpl.class");
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Long addTeacherDetails(Teacher teacherDetails) throws ServiceException, NotFoundException {
		logger.debug("In Adding Teacher Details");
		try {
			return teacherRepository.addTeacherDetails(teacherDetails);
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
	public List<TeacherEntity> getAllTeacherDetails() throws ServiceException {
		logger.debug("In Retrieving All Teacher Details");
		try {
			return teacherRepository.getAllTeacherDetails();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public TeacherEntity updateTeacherDetails(Long id, Teacher teacherDetails) throws ServiceException, NotFoundException  {
		logger.debug("In Updating Teacher Details");
		try {
			return teacherRepository.updateTeacherDetails(id,teacherDetails);
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
	public TeacherEntity deleteTeacherDetails(Long id) throws ServiceException, NotFoundException {
		logger.debug("In Deleting Teacher Details");
		try {
			return teacherRepository.deleteTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public TeacherEntity getParticularTeacherDetails(Long id) throws ServiceException, NotFoundException {
		logger.debug("In Retrieving Teacher Details");
		try {
			return teacherRepository.getParticularTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
