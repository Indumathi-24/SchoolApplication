package com.school.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dto.SubjectClass;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
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
}
