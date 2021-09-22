package com.school.serviceimpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.school.entity.Student;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	static Logger logger = Logger.getLogger("StudentServiceImpl.class");
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Long addStudent(Long roomNo,Student student) throws ServiceException{
		logger.debug("In Adding Student Details");
		try {
			return studentRepository.addStudent(roomNo,student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	@Override
	public List<Student> getAllStudent(Long roomNo) throws ServiceException{
		logger.debug("In Retrieving all Student Details");
		try {
			return studentRepository.getAllStudent(roomNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Student getParticularStudent(Long roomNo,Long rollNo) throws ServiceException{
		logger.debug("In Retrieving Student Details");
		try {
			return studentRepository.getParticularStudent(roomNo,rollNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Student updateStudent(Long roomNo,Long rollNo,Student student) throws ServiceException{
		logger.debug("In Updating Student Details");
		try {
			return studentRepository.updateStudent(roomNo,rollNo,student);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Student deleteStudent(Long roomNo,Long rollNo) throws ServiceException{
		logger.debug("In Deleting Student Details");
		try {
			return studentRepository.deleteStudent(roomNo,rollNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
}
