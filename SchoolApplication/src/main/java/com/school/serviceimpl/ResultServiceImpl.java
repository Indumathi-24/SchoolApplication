package com.school.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.entity.Result;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.repository.ResultRepository;
import com.school.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{
	@Autowired
	private ResultRepository resultRepository;
	
	@Override
	public Long addResult(Long rollNo,Result result) throws ServiceException
	{
		try {
			return resultRepository.addResult(rollNo,result);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Result getResult(Long rollNo) throws ServiceException
	{
		try {
			return resultRepository.getResult(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Result updateResult(Long rollNo,Long resultId,Result result) throws ServiceException
	{
		try {
			return resultRepository.updateResult(rollNo,resultId,result);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
