package com.school.controller;

import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;
import com.school.service.SubjectService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	
	@Autowired 
    private SubjectService subjectService;
	
	static Logger logger = Logger.getLogger("SubjectController.class");
	
	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addSubject(@PathVariable Long roomNo,@RequestBody Subject subject) 
	{
		logger.debug("In Adding Subject Details...");
		ResponseEntity<Response> responseBody = null;
		String subjectCode = null;
		try {
			 subjectCode = subjectService.addSubject(roomNo,subject);
			 responseBody = ResponseUtil.getResponse(200,"Subject Details Added Successfully",subjectCode);
		} catch (ServiceException e) {
		     responseBody = ResponseUtil.getResponse(500,e.getMessage(),subjectCode);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),subjectCode);
		}
		return responseBody;
	}
	@GetMapping("/{roomNo}")
	public ResponseEntity<Response> getAllSubject(@PathVariable("roomNo") Long roomNo) 
	{
		logger.debug("In Retrieving All Subject Details...");
		ResponseEntity<Response> responseBody = null;
		List<SubjectEntity> subjectEntity = new ArrayList<SubjectEntity>();
		try {
			subjectEntity = subjectService.getAllSubject(roomNo);
			responseBody = ResponseUtil.getResponse(200,"Subject Details Retrieved Successfully",subjectEntity);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return responseBody;
	}
	@GetMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> getParticularSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  
     {
		logger.debug("In Retrieving Subject Details...");
		ResponseEntity<Response> responseBody=null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.getParticularSubject(roomNo,code);
			responseBody = ResponseUtil.getResponse(200,"Subject Details Retrieved Successfully",subjectEntity);
		} catch (ServiceException  e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return responseBody;
	 }
	
	@PutMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> updateSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code,@RequestBody Subject subject) {
		logger.debug("In Updating Subject Details...");
		ResponseEntity<Response> responseBody = null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.updateSubject(roomNo,code,subject);
			responseBody = ResponseUtil.getResponse(200,"Subject Details Updated Successfully",subjectEntity);
		} catch (ServiceException  e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return responseBody;
	}
	
	@DeleteMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> deleteSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  {
		logger.debug("In Deleting Subject Details...");
		ResponseEntity<Response> responseBody=null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity= subjectService.deleteSubject(roomNo,code);
			responseBody = ResponseUtil.getResponse(200,"Subject Details Deleted Successfully",subjectEntity);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return responseBody;
	}
}
