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

import com.school.entity.StudentLogin;
import com.school.exception.ServiceException;
import com.school.service.StudentLoginService;

@RestController
@RequestMapping("/api/studentLogin")
public class StudentLoginController {
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
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("rollNo") Long rollNo) 
	{
		logger.debug("In Retrieving Student Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		StudentLogin login = new StudentLogin();
		try {
			login = studentLoginService.getLoginDetails(rollNo);
			response.setData(login);
			response.setStatusCode(200);
			response.setStatusText("Student Login Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
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
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
}
