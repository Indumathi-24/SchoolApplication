package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.MarkRepository;
import com.school.repository.StudentRepository;
import com.school.service.MarkService;

@Service
public class MarkServiceImpl implements MarkService{

	static Logger logger = Logger.getLogger("MarkServiceImpl.class");
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private StudentRepository studentRepository; 
	
    @Override
    public Long addMark(Long rollNo,Mark mark) throws ServiceException, NotFoundException
    {
    	logger.debug("In Adding Mark details...");
    	try
    	{
    		studentRepository.checkStudentRollNo(rollNo);
    		return markRepository.addMark(rollNo,mark);
    	}
    	catch(DatabaseException e)
    	{
    		throw new ServiceException(e.getMessage());
    	}
    }
	@Override
	public Integer updateMark(String code,Long rollNo,Mark mark) throws ServiceException,NotFoundException
	{
		logger.debug("In Updating Mark details...");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return markRepository.updateMark(code,rollNo,mark);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
	@Override
	
	public List<MarkEntity> getMarks(Long rollNo) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving Mark details...");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return markRepository.getMarks(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
	@Override
	public List<MarkEntity> getAllTermMarks(Long rollNo) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving All Term  Mark details...");
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return markRepository.getAllTermMarks(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
	@Override
	public List<MarkEntity> getAllMarks() throws ServiceException
	{
		try {
			return markRepository.getAllMarks();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
}
