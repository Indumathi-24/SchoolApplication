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

import com.school.dto.TeacherLogin;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.service.TeacherLoginService;

@RestController
@RequestMapping("/api/login")
public class TeacherLoginController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof TeacherNotFoundException)
		{
			logger.error("Error Occured while Processing Teacher Login Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Teacher Login Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	static Logger logger = Logger.getLogger("TeacherLoginController.class");
	
	@Autowired
	private TeacherLoginService teacherLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		logger.debug("In Adding Teacher Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		Long loginId;
		try {
			loginId = teacherLoginService.createLogin(id,login);
			response.setData(loginId);
			response.setStatusCode(200);
			response.setStatusText("Teacher Login Details Added Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving Teacher Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		TeacherLoginEntity teacherLoginEntity = new TeacherLoginEntity();
		try {
			teacherLoginEntity = teacherLoginService.getLoginDetails(id);
			response.setData(teacherLoginEntity);
			response.setStatusCode(200);
			response.setStatusText("Teacher Login Details Retrieved Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
	   return responseBody;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		logger.debug("In Updating Teacher Login details...");
		ResponseEntity<Response> responseBody =null;
		Response response = new Response();
		Integer result;
		try {
			 result = teacherLoginService.updateLoginDetails(id,login);
			 response.setData(result);
			 response.setStatusCode(200);
			 response.setStatusText("Teacher Details Updated Successfully");
			 responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		}
		catch(ServiceException | NotFoundException e)
		{
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
