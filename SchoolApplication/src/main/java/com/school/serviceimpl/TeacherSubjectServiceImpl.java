package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.TeacherSubject;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.SubjectRepository;
import com.school.repository.TeacherRepository;
import com.school.repository.TeacherSubjectRepository;
import com.school.repositoryimpl.SubjectRepositoryImpl;
import com.school.repositoryimpl.TeacherRepositoryImpl;
import com.school.service.TeacherSubjectService;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	static Logger logger = Logger.getLogger("TeacherSubjectServiceImpl.class");
	
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	
	@Override
	public Long assignTeacherSubject(TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException {
		logger.debug("In Adding TeacherSubject Details...");
		try {
			System.out.println(teacherSubjectDetails);
			return teacherSubjectRepository.assignTeacherSubject(teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Integer updateTeacherSubjectAssign(Long teacherId, String subjectCode,
			TeacherSubject teacherSubjectDetails) throws ServiceException, NotFoundException  {
		logger.debug("In Updating TeacherSubject Details");
		try {
			teacherRepository.checkTeacher(teacherId);
			subjectRepository.checkSubjectCode(subjectCode);
			return teacherSubjectRepository.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Integer deleteTeacherSubjectAssign(Long teacherId, String subjectCode) throws ServiceException, NotFoundException {
		logger.debug("In Deleting TeacherSubject Details...");
		try {
			teacherRepository.checkTeacher(teacherId);
			subjectRepository.checkSubjectCode(subjectCode);
			return teacherSubjectRepository.deleteTeacherSubjectAssign(teacherId,subjectCode);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public List<Long> getSubjectClassId(Long teacherId) throws NotFoundException, ServiceException
	{
		logger.debug("In Retrieving SubjectClassAssignId...");
		try {
			teacherRepository.checkTeacher(teacherId);
			return teacherSubjectRepository.getSubjectClassId(teacherId);
	} catch (DatabaseException e) {
		throw new ServiceException(e.getMessage());
	}
	}
	
	

}
