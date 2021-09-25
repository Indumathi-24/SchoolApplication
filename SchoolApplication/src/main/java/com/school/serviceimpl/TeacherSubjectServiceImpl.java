package com.school.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.dto.TeacherSubject;
import com.school.entity.TeacherSubjectEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.SubjectNotFoundException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherSubjectRepository;
import com.school.repositoryimpl.SubjectRepositoryImpl;
import com.school.repositoryimpl.TeacherRepositoryImpl;
import com.school.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;
	@Autowired
	private TeacherRepositoryImpl teacherRepositoryImpl;
	@Autowired
	private SubjectRepositoryImpl subjectRepositoryImpl;
	
	
	@Override
	public Long assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException {
		
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Long updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException  {
	
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Long deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException, NotFoundException {
		
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
