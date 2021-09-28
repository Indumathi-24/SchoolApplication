package com.school.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.SubjectClass;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.SubjectClassService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/subjectClass")
public class SubjectClassController {
	
	  static Logger logger = Logger.getLogger("SubjectClassController.class");
	  @Autowired
	  SubjectClassService subjectClassService;
	
      @PostMapping
      public ResponseEntity<Response> assignSubjectClass(@RequestBody SubjectClass subjectClass)
      {
    	  logger.debug("In Adding SubjectClass Details");
    	  ResponseEntity<Response> response = null;
    	  Long assignId=0l;
    	  try {
			assignId = subjectClassService.assignSubjectClass(subjectClass);
			response = ResponseUtil.getResponse(200,"Subject is Assigned to Class Successfully",assignId);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),assignId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),assignId);
		}
    	  return response;
      }
}
