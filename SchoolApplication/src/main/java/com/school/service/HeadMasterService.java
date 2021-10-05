package com.school.service;

import java.util.List;
import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface HeadMasterService {
	
	Long addHeadMasterDetails(HeadMaster headMaster) throws ServiceException, NotFoundException;
	List<HeadMasterEntity> getAllHeadMasterDetails() throws ServiceException;
	HeadMasterEntity updateHeadMasterDetails(Long id,HeadMaster headMaster) throws  ServiceException, NotFoundException;
	HeadMasterEntity deleteHeadMasterDetails(Long id) throws ServiceException, NotFoundException;
	HeadMasterEntity getParticularHeadMasterDetails(Long id) throws ServiceException, NotFoundException;
}
