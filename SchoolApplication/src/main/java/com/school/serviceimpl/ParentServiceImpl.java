package com.school.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.dto.Parent;
import com.school.entity.ParentEntity;
import com.school.exception.ConstraintViolationException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ParentNotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ParentRepository;
import com.school.repository.StudentRepository;
import com.school.service.ParentService;
import com.school.util.ResponseUtil;

@Service
public class ParentServiceImpl implements ParentService {
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Long addParent(Long rollNo,Parent parent) throws ServiceException, NotFoundException
	{
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return parentRepository.addParent(rollNo,parent);
		} 
		catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
	@Override
	public List<ParentEntity> getParent(Long rollNo) throws ServiceException, NotFoundException
	{
		try {
			studentRepository.checkStudentRollNo(rollNo);
			return parentRepository.getParent(rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
     
    @Override
	public ParentEntity updateParent(Long id,Parent parent) throws ServiceException, NotFoundException
	{
		try {
			return parentRepository.updateParent(id,parent);
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
    public ParentEntity deleteParent(Long id) throws ServiceException, NotFoundException
    {
    	try {
			return parentRepository.deleteParent(id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
    }
}
