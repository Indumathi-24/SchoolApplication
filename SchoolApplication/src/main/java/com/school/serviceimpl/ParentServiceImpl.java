package com.school.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.entity.Parent;
import com.school.exception.DatabaseException;
import com.school.exception.ParentNotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ParentRepository;
import com.school.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepository;
	@Override
	public Long addParent(Long rollNo,Parent parent) throws ServiceException
	{
		try {
			return parentRepository.addParent(rollNo,parent);
		} 
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
	@Override
	public List<Parent> getParent(Long rollNo) throws ServiceException
	{
		try {
			return parentRepository.getParent(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
     
    /*@Override
	public Parent getParent(Long id) throws ParentNotFoundException
	{
		return parentRepository.getParent(id);
	}*/
    @Override
	public Parent updateParent(Long id,Parent parent) throws ServiceException
	{
		try {
			return parentRepository.updateParent(id,parent);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
    
    @Override
    public Parent deleteParent(Long id) throws ServiceException
    {
    	try {
			return parentRepository.deleteParent(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
    }
}
