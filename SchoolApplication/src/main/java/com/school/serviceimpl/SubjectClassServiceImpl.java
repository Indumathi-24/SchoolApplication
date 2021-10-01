package com.school.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.SubjectNotFoundException;
import com.school.repository.ClassRepository;
import com.school.repository.SubjectClassRepository;
import com.school.repository.SubjectRepository;
import com.school.service.SubjectClassService;

@Service
public class SubjectClassServiceImpl implements SubjectClassService{

	  static Logger logger = Logger.getLogger("SubjectClassServiceImpl.class");
      @Autowired
      SubjectClassRepository subjectClassRepository;
      
      @Autowired
      SubjectRepository subjectRepository;
      
      @Autowired
      ClassRepository classRepository;
      
      @Override
      public Long assignSubjectClass(SubjectClass subjectClass) throws NotFoundException, ServiceException
      {
    	  logger.debug("In Adding SubjectClass Details");
    	  try {
    		  System.out.println(subjectClass);
    		  classRepository.checkClassRoomNo(subjectClass.getClassDetail().getRoomNo());
    		  subjectRepository.checkSubjectCode(subjectClass.getSubject().getCode());
			return subjectClassRepository.assignSubjectClass(subjectClass);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
      }
      @Override
      public List<String> viewSubjectClass(Long roomNo) throws NotFoundException, ServiceException
      {
    	  logger.debug("In Retrieving SubjectClass Details");
    	  try {
    		  System.out.println(roomNo);
    		  classRepository.checkClassRoomNo(roomNo);
			return subjectClassRepository.viewSubjectClass(roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
      }
      
      @Override
     public  Long getSubjectClassAssignId(String code,Long roomNo) throws NotFoundException, ServiceException
      {
    	  logger.debug("In Retrieving SubjectClass Details");
    	  try {
    		  System.out.println(code);
    		  subjectRepository.checkSubjectCode(code);
			return subjectClassRepository.getSubjectClassAssignId(code,roomNo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
      }
      
      @Override
      public  Long getRoomNoForAssignId(Long id) throws NotFoundException, ServiceException
       {
     	  logger.debug("In Retrieving SubjectClass Details");
     	  try {
 			return subjectClassRepository.getRoomNoForAssignId(id);
 		} catch (DatabaseException e) {
 			throw new ServiceException(e.getMessage());
 		}
       }
      
      @Override
      public String getSubjectCode(Long roomNo,Long id) throws NotFoundException, ServiceException
      {
    	  logger.debug("In Retrieving SubjectCode Details");
     	  try {
     		  classRepository.checkClassRoomNo(roomNo);
 			return subjectClassRepository.getSubjectCode(roomNo,id);
 		} catch (DatabaseException e) {
 			throw new ServiceException(e.getMessage());
 		} 
      }
}
