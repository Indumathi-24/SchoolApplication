package com.school.serviceimpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.entity.HeadMasterLogin;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.HeadMasterLoginRepository;
import com.school.service.HeadMasterLoginService;

@Service
public class HeadMasterLoginServiceImpl implements HeadMasterLoginService{
	static Logger logger = Logger.getLogger("HeadMasterLoginService.class");
	@Autowired
	private HeadMasterLoginRepository headMasterLoginRepository;
	public Long createLogin(Long id,HeadMasterLogin login) throws ServiceException
	{
		logger.debug("In Adding HeadMaster Login details...");
		try {
			return headMasterLoginRepository.createLogin(id,login);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public HeadMasterLogin getLoginDetails(Long id) throws ServiceException  {
		logger.debug("In Retrieving HeadMaster Login details...");
		try {
			return headMasterLoginRepository.getLoginDetails(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public Integer updateLoginDetails(Long id,HeadMasterLogin login) throws ServiceException {
		logger.debug("In Updating HeadMaster Login details...");
		try {
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
