package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.ConstraintViolationException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.HeadMasterRepository;
import com.school.service.HeadMasterService;

@Service
public class HeadMasterServiceImpl implements HeadMasterService{
	
	static Logger logger = Logger.getLogger("HeadMasterServiceImpl.class");
	@Autowired
	private HeadMasterRepository headMasterRepository;
	@Override
	public Long addHeadMasterDetails(HeadMaster headMaster) throws ServiceException, NotFoundException {
		logger.debug("In Adding HeadMaster details...");
		try {
			return headMasterRepository.addHeadMasterDetails(headMaster);
		} 
		catch(DataIntegrityViolationException e)
		{
			throw new ConstraintViolationException("Violating Integrity Constraints, Duplicate Key Entered");
		}
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<HeadMasterEntity> getAllHeadMasterDetails() throws ServiceException {
		logger.debug("In Retrieving All HeadMaster details...");
		try {
			return headMasterRepository.getAllHeadMasterDetails();
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMasterEntity updateHeadMasterDetails(Long id, HeadMaster headMaster) throws ServiceException, NotFoundException {
		logger.debug("In Updating HeadMaster details...");
		try {
			return headMasterRepository.updateHeadMasterDetails(id,headMaster);
		} 
		catch(DataIntegrityViolationException e)
		{
			throw new ConstraintViolationException("Violating Integrity Constraints, Duplicate Key Entered");
		}catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMasterEntity deleteHeadMasterDetails(Long id) throws ServiceException, NotFoundException {
		logger.debug("In Deleting HeadMaster details...");
		try {
			return headMasterRepository.deleteHeadMasterDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMasterEntity getParticularHeadMasterDetails(Long id) throws ServiceException, NotFoundException {
		logger.debug("In Retrieving HeadMaster details...");
		try {
			return headMasterRepository.getParticularHeadMasterDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
