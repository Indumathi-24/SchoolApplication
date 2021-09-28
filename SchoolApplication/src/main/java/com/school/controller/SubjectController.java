package com.school.controller;

import com.school.exception.NotFoundException;

import com.school.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	@PostMapping
	public ResponseEntity<Response> addSubject(@Valid @RequestBody Subject subject) 
	{
		logger.debug("In Adding Subject Details...");
		ResponseEntity<Response> response = null;
		String subjectCode = null;
		try {
			 subjectCode = subjectService.addSubject(subject);
			 response = ResponseUtil.getResponse(200,"Subject Details Added Successfully",subjectCode);
		} catch (ServiceException e) {
		     response = ResponseUtil.getResponse(500,e.getMessage(),subjectCode);
		} 
		return response;
	}
	@GetMapping
	public ResponseEntity<Response> getAllSubject() 
	{
		logger.debug("In Retrieving All Subject Details...");
		ResponseEntity<Response> response = null;
		List<SubjectEntity> subjectEntity = new ArrayList<>();
		try {
			subjectEntity = subjectService.getAllSubject();
			response = ResponseUtil.getResponse(200,"Subject Details Retrieved Successfully",subjectEntity);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} 
		return response;
	}
	@GetMapping("/{code}")
	public ResponseEntity<Response> getParticularSubject(@PathVariable("code") String code)  
     {
		logger.debug("In Retrieving Subject Details...");
		ResponseEntity<Response> response=null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.getParticularSubject(code);
			response = ResponseUtil.getResponse(200,"Subject Details Retrieved Successfully",subjectEntity);
		} catch (ServiceException  e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return response;
	 }
	
	@PutMapping("/{code}")
	public ResponseEntity<Response> updateSubject(@PathVariable("code") String code,@Valid @RequestBody Subject subject) {
		logger.debug("In Updating Subject Details...");
		ResponseEntity<Response> response = null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.updateSubject(code,subject);
			response = ResponseUtil.getResponse(200,"Subject Details Updated Successfully",subjectEntity);
		} catch (ServiceException  e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return response;
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<Response> deleteSubject(@PathVariable("code") String code)  {
		logger.debug("In Deleting Subject Details...");
		ResponseEntity<Response> response=null;
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity= subjectService.deleteSubject(code);
			response = ResponseUtil.getResponse(200,"Subject Details Deleted Successfully",subjectEntity);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),subjectEntity);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),subjectEntity);
		}
		return response;
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Response> validationFailed(MethodArgumentNotValidException e) {
	        logger.error("Validation fails, Check your input!");
	        Response response = new Response();
	        ResponseEntity<Response> responseEntity = null;
	        response.setStatusCode(422);
	        response.setStatusText("Validation fails!");
	        responseEntity = new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	        return responseEntity;
	 }
}
