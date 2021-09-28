package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.ResultRepository;
import com.school.repository.StudentRepository;
import com.school.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	static Logger logger = Logger.getLogger("ResultServiceImpl.class");
	@Override
	public Long addResult(Long roomNo,Long rollNo,Result result) throws ServiceException, NotFoundException
	{
		logger.debug("In Adding Student's Result...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			studentRepository.checkStudentRollNo(rollNo);
			return resultRepository.addResult(roomNo,rollNo,result);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public ResultEntity getResult(Long roomNo,Long rollNo) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving Student's Result...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			studentRepository.checkStudentRollNo(rollNo);
			return resultRepository.getResult(roomNo,rollNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public ResultEntity updateResult(Long roomNo,Long rollNo,Long resultId,Result result) throws ServiceException, NotFoundException
	{
		logger.debug("In Updating Student's Result...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			studentRepository.checkStudentRollNo(rollNo);
			return resultRepository.updateResult(roomNo,rollNo,resultId,result);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public List<ResultEntity> getResultByClass(Long roomNo) throws ServiceException, NotFoundException
	{
		logger.debug("In Retrieving Student's Result...");
		try {
			classRepository.checkClassRoomNo(roomNo);
			return resultRepository.getResultByClass(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
//	@Override
//	public Integer updateTermMarks(Long totalMarks,Long rollNo) throws ServiceException, NotFoundException 
//	{
//		logger.debug("In Updating Student's Result...");
//		try {
//			studentRepository.checkStudentRollNo(rollNo);
//			return resultRepository.updateTermMarks(totalMarks,rollNo);
//		} catch (DatabaseException e) {
//			throw new ServiceException(e.getMessage());
//		}
//	}
}
