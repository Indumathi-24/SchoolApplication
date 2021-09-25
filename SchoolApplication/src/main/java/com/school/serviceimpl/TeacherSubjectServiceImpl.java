package com.school.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.TeacherSubject;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.TeacherSubjectRepository;
import com.school.repositoryimpl.SubjectRepositoryImpl;
import com.school.repositoryimpl.TeacherRepositoryImpl;
import com.school.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	static Logger logger = Logger.getLogger("TeacherSubjectServiceImpl.class");
	
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepositoryImpl;
	@Autowired
	private TeacherRepositoryImpl teacherRepositoryImpl;
	@Autowired
	private SubjectRepositoryImpl subjectRepositoryImpl;
	
	
	@Override
	public Long assignTeacherSubject(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException {
		logger.debug("In Adding TeacherSubject Details");
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Integer updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException  {
		logger.debug("In Updating TeacherSubject Details");
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Integer deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException, NotFoundException {
		logger.debug("In Deleting TeacherSubject Details");
		try {
			teacherRepositoryImpl.checkTeacher(teacherId);
			subjectRepositoryImpl.checkSubjectCode(subjectCode);
			return teacherSubjectRepositoryImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	

}
