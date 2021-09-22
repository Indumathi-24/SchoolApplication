package com.school.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.school.entity.Teacher;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherRepository teacherRepositoryImpl;
	@Override
	public ResponseEntity<String> addTeacherDetails(Teacher teacherDetails) {
		
		return teacherRepositoryImpl.addTeacherDetails(teacherDetails);
	}
	@Override
	public ResponseEntity<?> getAllTeacherDetails() {
		
		return teacherRepositoryImpl.getAllTeacherDetails();
	}
	@Override
	public ResponseEntity<String> updateTeacherDetails(Long id, Teacher teacherDetails) throws ServiceException  {
		
		try {
			return teacherRepositoryImpl.updateTeacherDetails(id,teacherDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<String> deleteTeacherDetails(Long id) throws ServiceException {
		
		try {
			return teacherRepositoryImpl.deleteTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<?> getParticularTeacherDetails(Long id) throws ServiceException {
		
		try {
			return teacherRepositoryImpl.getParticularTeacherDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
