package com.school.repository;
import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;


public interface HeadMasterLoginRepository {
	
	Long createLogin(Long id,HeadMasterLogin login) throws DatabaseException;
	HeadMasterLoginEntity getLoginDetails(Long id) throws DatabaseException;
	Integer updateLoginDetails(Long id,HeadMasterLogin login) throws DatabaseException;
	Long getParticularLoginDetails(Long autoId) throws DatabaseException, NotFoundException;
}
