package com.school.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.dto.TeacherLogin;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.TeacherLoginRepository;
import com.school.repository.TeacherRepository;
import com.school.service.TeacherLoginService;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {
    
	static Logger logger = Logger.getLogger("TeacherLoginServiceImpl.class");
	
	@Autowired
	private TeacherLoginRepository teacherLoginRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public Long createLogin(Long id,TeacherLogin login) throws NotFoundException,ServiceException
	{
		logger.debug("In Adding Teacher Login details...");
		try {
			teacherRepository.checkTeacher(id);
			return teacherLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public TeacherLoginEntity getLoginDetails(Long id) throws ServiceException, NotFoundException {
		logger.debug("In Retrieving Teacher Login details...");
		try {
			teacherRepository.checkTeacher(id);
			return teacherLoginRepository.getLoginDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public Integer updateLoginDetails(Long id,TeacherLogin login) throws ServiceException, NotFoundException {
		logger.debug("In Updating Teacher Login details...");
		try {
			teacherRepository.checkTeacher(id);
			return teacherLoginRepository.updateLoginDetails(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
