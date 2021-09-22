package com.school.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.entity.TeacherSubject;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.TeacherSubjectRepository;
import com.school.service.TeacherSubjectService;
@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;
	@Override
	public ResponseEntity<String> assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException {
		
		try {
			return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException  {
	
		try {
			return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException {
		
		try {
			return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
