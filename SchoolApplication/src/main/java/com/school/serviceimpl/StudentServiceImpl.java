package com.school.serviceimpl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.dto.Student;
import com.school.entity.StudentEntity;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	static Logger logger = Logger.getLogger("StudentServiceImpl.class");
	@Autowired
	private StudentRepository studentRepository;
    
	@Autowired
	private ClassRepository classRepository;
	@Override
	public Long addStudent(Long roomNo,Student student) throws ServiceException,NotFoundException{
		logger.debug("In Adding Student Details");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return studentRepository.addStudent(roomNo,student);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	@Override
	public List<StudentEntity> getAllStudent(Long roomNo) throws ServiceException, NotFoundException{
		logger.debug("In Retrieving all Student Details");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return studentRepository.getAllStudent(roomNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public StudentEntity getParticularStudent(Long rollNo) throws ServiceException, NotFoundException{
		logger.debug("In Retrieving Student Details");
		try {
			return studentRepository.getParticularStudent(rollNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public StudentEntity updateStudent(Long roomNo,Long rollNo,Student student) throws ServiceException, NotFoundException{
		logger.debug("In Updating Student Details");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return studentRepository.updateStudent(roomNo,rollNo,student);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public StudentEntity deleteStudent(Long roomNo,Long rollNo) throws ServiceException, NotFoundException{
		logger.debug("In Deleting Student Details");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return studentRepository.deleteStudent(roomNo,rollNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Long getStudentRoomNo(Long roomNo) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving Student Room No");
		try {
			return studentRepository.getStudentRoomNo(roomNo);
		}
		catch(DatabaseException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
}
