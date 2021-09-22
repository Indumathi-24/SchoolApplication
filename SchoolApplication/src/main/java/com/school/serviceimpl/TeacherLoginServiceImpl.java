package com.school.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.school.entity.TeacherLogin;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.repository.TeacherLoginRepository;
import com.school.service.TeacherLoginService;

@Service
public class TeacherLoginServiceImpl implements TeacherLoginService {

	@Autowired
	private TeacherLoginRepository teacherLoginRepository;
	public ResponseEntity<String> createLogin(Long id,TeacherLogin login)
	{
		return teacherLoginRepository.createLogin(id,login);
	}
	@Override
	public ResponseEntity<?> getLoginDetails(Long id) throws ServiceException {
		
		try {
			return teacherLoginRepository.getLoginDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public ResponseEntity<String> updateLoginDetails(Long id,TeacherLogin login) throws ServiceException {
		try {
			return teacherLoginRepository.updateLoginDetails(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
