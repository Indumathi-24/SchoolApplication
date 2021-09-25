package com.school.service;
import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface HeadMasterLoginService {
	Long createLogin( Long id,HeadMasterLogin login) throws ServiceException, NotFoundException;
    HeadMasterLoginEntity getLoginDetails(Long id) throws ServiceException, NotFoundException;
	Integer updateLoginDetails(Long userName,HeadMasterLogin login) throws ServiceException, NotFoundException;
	Long getParticularLoginDetails(Long autoId) throws ServiceException, NotFoundException;
}
