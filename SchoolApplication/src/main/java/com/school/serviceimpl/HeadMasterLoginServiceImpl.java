package com.school.serviceimpl;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.HeadMasterLoginRepository;
import com.school.repository.HeadMasterRepository;
import com.school.service.HeadMasterLoginService;

@Service
public class HeadMasterLoginServiceImpl implements HeadMasterLoginService{
	static Logger logger = Logger.getLogger("HeadMasterLoginServiceImpl.class");
	
	@Autowired
	private HeadMasterRepository headMasterRepository;
	
	@Autowired
	private HeadMasterLoginRepository headMasterLoginRepository;
	
	public Long createLogin(Long id,HeadMasterLogin login) throws ServiceException, NotFoundException
	{
		logger.debug("In Adding HeadMaster Login details...");
		Long headMasterLoginId;
		try {
			headMasterRepository.checkHeadMaster(id);
			headMasterLoginId = headMasterLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return headMasterLoginId;
	}
	@Override
	public HeadMasterLoginEntity getLoginDetails(Long id) throws ServiceException, NotFoundException  {
		logger.debug("In Retrieving HeadMaster Login details...");
		try {
			headMasterRepository.checkHeadMaster(id);
			return headMasterLoginRepository.getLoginDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public Integer updateLoginDetails(Long id,HeadMasterLogin login) throws ServiceException,NotFoundException {
		logger.debug("In Updating HeadMaster Login details...");
		try {
			headMasterRepository.checkHeadMaster(id);
			return headMasterLoginRepository.updateLoginDetails(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Long getParticularLoginDetails(Long autoId) throws ServiceException {
		logger.debug("In Retrieving HeadMaster Login Id...");
		try {
			return headMasterLoginRepository.getParticularLoginDetails(autoId);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
