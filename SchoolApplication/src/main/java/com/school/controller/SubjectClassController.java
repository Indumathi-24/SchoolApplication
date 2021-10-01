package com.school.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.SubjectClassService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/subjectClass")
@CrossOrigin("http://localhost:4200")
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
      
      @GetMapping("/{roomNo}")
      public ResponseEntity<Response> viewSubjectClass(@PathVariable("roomNo") Long roomNo)
      {
    	  logger.debug("In Retrieving SubjectClass Details");
    	  ResponseEntity<Response> response = null;
    	  List<String> subjectClass = null;
    	  try {
    		  subjectClass = subjectClassService.viewSubjectClass(roomNo);
			response = ResponseUtil.getResponse(200,"Subject is Retrieved that is Assigned Class Successfully",subjectClass);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectClass);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectClass);
		}
    	  return response;
      }
      
      @GetMapping("/{code}/{roomNo}")
      public ResponseEntity<Response> getSubjectClassAssignId(@PathVariable("code") String code,@PathVariable("roomNo") Long roomNo)
      {
    	  logger.debug("In Retrieving SubjectClass Details");
    	  ResponseEntity<Response> response = null;
    	  Long subjectClassAssignId = null;
    	  try {
    		  subjectClassAssignId = subjectClassService.getSubjectClassAssignId(code,roomNo);
			response = ResponseUtil.getResponse(200,"Subject Assigned Id is Retrieved Successfully",subjectClassAssignId);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectClassAssignId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectClassAssignId);
		}
    	  return response;
      }
      
      @GetMapping("subjectAssign/{id}")
      public ResponseEntity<Response> getRoomNoForAssignId(@PathVariable("id") Long id)
      {
    	  logger.debug("In Retrieving SubjectClass Details");
    	  ResponseEntity<Response> response = null;
    	  Long roomNo = null;
    	  try {
    		  roomNo = subjectClassService.getRoomNoForAssignId(id);
			response = ResponseUtil.getResponse(200,"Subject Assigned Id is Retrieved Successfully",roomNo);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),roomNo);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),roomNo);
		}
    	  return response;
      }
      
      @GetMapping("/subjectCode/{roomNo}/{id}")
      public ResponseEntity<Response> getSubjectCode(@PathVariable("roomNo") Long roomNo,@PathVariable("id") Long id)
      {
    	  logger.debug("In Retrieving SubjectCode Details");
    	  ResponseEntity<Response> response = null;
    	  String subjectCode = null;
    	  try {
    		  subjectCode = subjectClassService.getSubjectCode(roomNo,id);
			response = ResponseUtil.getResponse(200,"Subject Assigned Id is Retrieved Successfully",subjectCode);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectCode);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectCode);
		}
    	  return response;
      }
}

