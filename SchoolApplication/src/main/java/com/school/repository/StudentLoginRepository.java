package com.school.repository;
import com.school.dto.StudentLogin;
import com.school.entity.StudentLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface StudentLoginRepository {
	Long createLogin(Long rollNo,StudentLogin login)throws DatabaseException;
	StudentLoginEntity getLoginDetails(Long rollNo) throws DatabaseException;
	Integer updateLoginDetails(Long rollNo,StudentLogin student) throws DatabaseException;
	Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException;
}
