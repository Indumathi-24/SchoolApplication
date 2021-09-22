package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.entity.HeadMaster;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.HeadMasterRepository;
import com.school.service.HeadMasterService;

@Service
public class HeadMasterServiceImpl implements HeadMasterService{
	static Logger logger = Logger.getLogger("HeadMasterService.class");
	@Autowired
	private HeadMasterRepository HeadMasterRepositoryImpl;
	@Override
	public HeadMaster addHeadMasterDetails(HeadMaster headMasterDetails) throws ServiceException {
		logger.debug("In Adding HeadMaster details...");
		try {
			return HeadMasterRepositoryImpl.addHeadMasterDetails(headMasterDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<HeadMaster> getAllHeadMasterDetails() throws ServiceException {
		logger.debug("In Retrieving All HeadMaster details...");
		try {
			return HeadMasterRepositoryImpl.getAllHeadMasterDetails();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMaster updateHeadMasterDetails(Long id, HeadMaster headMasterDetails) throws ServiceException {
		logger.debug("In Updating HeadMaster details...");
		try {
			return HeadMasterRepositoryImpl.updateHeadMasterDetails(id,headMasterDetails);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String deleteHeadMasterDetails(Long id) throws ServiceException {
		logger.debug("In Deleting HeadMaster details...");
		try {
			return HeadMasterRepositoryImpl.deleteHeadMasterDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMaster getParticularHeadMasterDetails(Long id) throws ServiceException {
		logger.debug("In Retrieving HeadMaster details...");
		try {
			return HeadMasterRepositoryImpl.getParticularHeadMasterDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
