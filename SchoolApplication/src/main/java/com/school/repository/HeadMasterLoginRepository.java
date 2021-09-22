package com.school.repository;
import com.school.entity.HeadMasterLogin;
import com.school.exception.DatabaseException;
public interface HeadMasterLoginRepository {
	Long createLogin(Long id,HeadMasterLogin login) throws DatabaseException;
	HeadMasterLogin getLoginDetails(Long id) throws DatabaseException;
	Integer updateLoginDetails(Long id,HeadMasterLogin login) throws DatabaseException;
	Long getParticularLoginDetails(Long autoId) throws DatabaseException;
}
