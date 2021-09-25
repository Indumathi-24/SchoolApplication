package com.school.repository;

import com.school.dto.TeacherLogin;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface TeacherLoginRepository {

	 Long createLogin(Long id,TeacherLogin login) throws DatabaseException;
	 TeacherLoginEntity getLoginDetails(Long id) throws DatabaseException;
	 Integer updateLoginDetails(Long id,TeacherLogin login) throws DatabaseException;
	 Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException;	

}
