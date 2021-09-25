package com.school.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.school.exception.StudentNotFoundException;
import com.school.service.StudentLoginService;

@RestController
@RequestMapping("/api/studentLogin")
public class StudentLoginController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if( e instanceof StudentNotFoundException)
		{
			logger.error("Error Occured while Saving Student Login Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Saving Student Login Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	static Logger logger = Logger.getLogger("StudentLoginController.class");
	@Autowired
	private StudentLoginService studentLoginService;
	
	@PostMapping("/{rollNo}")
	public ResponseEntity<Response> createLogin(@PathVariable("rollNo") Long rollNo,@RequestBody StudentLogin login)
	{
		logger.debug("In Adding Student Login details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Long loginId = studentLoginService.createLogin(rollNo,login);
			response.setData(loginId);
			response.setStatusCode(200);
			response.setStatusText("Student Login Details Saved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("rollNo") Long rollNo) 
	{
		logger.debug("In Retrieving Student Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		StudentLoginEntity login = new StudentLoginEntity();
		try {
			login = studentLoginService.getLoginDetails(rollNo);
			response.setData(login);
			response.setStatusCode(200);
			response.setStatusText("Student Login Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
	  return responseBody;
	}
	
	@PutMapping("/{rollNo}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("rollNo") Long rollNo,@RequestBody StudentLogin login)
	{
		logger.debug("In Updating Student Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		int result;
		try {
			result = studentLoginService.updateLoginDetails(rollNo,login);
			response.setData(result);
			response.setStatusCode(200);
			response.setStatusText("Student Login Details Updated Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
