package com.school.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.StudentLogin;
import com.school.entity.StudentLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.StudentLoginService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/studentLogin")
@CrossOrigin("http://localhost:4200")
public class StudentLoginController {
	
	static Logger logger = Logger.getLogger("StudentLoginController.class");
	@Autowired
	private StudentLoginService studentLoginService;
	
	@PostMapping("/{rollNo}")
	public ResponseEntity<Response> createLogin(@PathVariable("rollNo") Long rollNo,@Valid @RequestBody StudentLogin login)
	{
		logger.debug("In Adding Student Login details...");
		ResponseEntity<Response> response = null;
		Long loginId = 0l;
		try {
			loginId = studentLoginService.createLogin(rollNo,login);
			response = ResponseUtil.getResponse(200,"Student Login Details Saved Successfully",loginId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),loginId);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),loginId);
		}
		return response;
	}
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("rollNo") Long rollNo) 
	{
		logger.debug("In Retrieving Student Login details...");
		ResponseEntity<Response> response =null;
		StudentLoginEntity login = new StudentLoginEntity();
		try {
			login = studentLoginService.getLoginDetails(rollNo);
			response = ResponseUtil.getResponse(200,"Student Login Details Retrieved Successfully",login);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),login);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),login);
		}
	  return response;
	}
	
	@PutMapping("/{rollNo}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("rollNo") Long rollNo,@Valid @RequestBody StudentLogin login)
	{
		logger.debug("In Updating Student Login details...");
		ResponseEntity<Response> response =null;
		int result = 0;
		try {
			result = studentLoginService.updateLoginDetails(rollNo,login);
			response = ResponseUtil.getResponse(200,"Student Login Details Updated Successfully",result);
		} catch (ServiceException  e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),result);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),result);
		}
		return response;
	}
	
	@GetMapping("/studentLoginId/{autoId}")
	public ResponseEntity<Response> getParticularLoginDetails(@PathVariable("autoId") Long autoId)
	{
		logger.debug("In Retrieving Student Login Id...");
		ResponseEntity<Response> response=null;
		Long id = 0l;
		try {
			id = studentLoginService.getParticularLoginDetails(autoId);
			response = ResponseUtil.getResponse(200,"Login Id Retrieved Successfully",id);
		}catch(ServiceException e)
		{
			response = ResponseUtil.getResponse(500,e.getMessage(), id);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), id);
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
