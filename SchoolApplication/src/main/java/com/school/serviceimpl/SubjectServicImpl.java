package com.school.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.ServiceException;
import com.school.exception.SubjectNotFoundException;
import com.school.entity.Subject;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectService;

@Service
public class SubjectServicImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public ResponseEntity<String> addSubject(Long roomNo,Subject subject) throws ServiceException 
	{
		try {
			return subjectRepository.addSubject(roomNo,subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ResponseEntity<?> getAllSubject(Long roomNo) throws  ServiceException
	{
		try {
			return subjectRepository.getAllSubject(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public 	ResponseEntity<?> getParticularSubject(Long roomNo,String code) throws ServiceException 
	{
		try {
			return subjectRepository.getParticularSubject(roomNo,code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public ResponseEntity<String> updateSubject(Long roomNo,String code,Subject subject) throws ServiceException
	{
		try {
			return subjectRepository.updateSubject(roomNo, code, subject);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public ResponseEntity<String> deleteSubject(Long roomNo, String code) throws ServiceException 
	{
		try {
			return subjectRepository.deleteSubject(roomNo, code);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
