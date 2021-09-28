package com.school.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.repository.MarkRepository;
import com.school.service.MarkService;

@Service
public class MarkServiceImpl implements MarkService{

	@Autowired
	private MarkRepository markRepository;
	
	@Override
	public Integer updateMark(String code,Long rollNo,Mark mark) throws ServiceException,NotFoundException
	{
		try {
			return markRepository.updateMark(code,rollNo,mark);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
}
