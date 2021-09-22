package com.school.repository;
import com.school.entity.StudentLogin;
import com.school.exception.DatabaseException;

public interface StudentLoginRepository {
	Long createLogin(Long rollNo,StudentLogin login)throws DatabaseException;
	StudentLogin getLoginDetails(Long rollNo) throws DatabaseException;
	Integer updateLoginDetails(Long rollNo,StudentLogin student) throws DatabaseException;
}
