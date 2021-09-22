package com.school.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.entity.HeadMaster;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.ServiceException;

public interface HeadMasterService {
	Long addHeadMasterDetails(HeadMaster headMasterDetails) throws ServiceException;
	List<HeadMaster> getAllHeadMasterDetails() throws ServiceException;
	HeadMaster updateHeadMasterDetails(Long id,HeadMaster headMasterDetails) throws  ServiceException;
	String deleteHeadMasterDetails(Long id) throws ServiceException;
	HeadMaster getParticularHeadMasterDetails(Long id) throws ServiceException;
}
