package com.school.service;

import org.springframework.http.ResponseEntity;


import com.school.entity.HeadMasterLogin;
import com.school.exception.ServiceException;

public interface HeadMasterLoginService {
	Long createLogin( Long id,HeadMasterLogin login) throws ServiceException;
    HeadMasterLogin getLoginDetails(Long id) throws ServiceException;
	Integer updateLoginDetails(Long userName,HeadMasterLogin login) throws ServiceException;
	Long getParticularLoginDetails(Long autoId) throws ServiceException;
}
