package com.school.controller;
import java.util.ArrayList;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof TeacherNotFoundException)
		{
			logger.error("Error Occured while Processing Teacher Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Teacher Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	@Autowired
	private TeacherService teacherService;
	
	static Logger logger = Logger.getLogger("TeacherController.class");
	
	@PostMapping
	public ResponseEntity<Response> addTeacherDetails(@RequestBody Teacher teacherDetails)
	{
		logger.debug("In Adding Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long teacherId;
		try {
			teacherId = teacherService.addTeacherDetails(teacherDetails);
			response.setData(teacherId);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details Added Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
		    responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@GetMapping
	public ResponseEntity<Response> getAllTeacherDetails()
	{
		logger.debug("In Retrieving All Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		List<TeacherEntity> teacherEntity = new ArrayList<>();
		 try {
			teacherEntity = teacherService.getAllTeacherDetails();
			response.setData(teacherEntity);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details Retrieved Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			 responseBody = errorStatement(e);
		}
		 return responseBody;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateTeacherDetails(@PathVariable("id") Long id,@RequestBody Teacher teacherDetails) 
	{
		logger.debug("In Updating Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.updateTeacherDetails(id,teacherDetails);
			response.setData(teacherEntity);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details Updated Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			 responseBody = errorStatement(e);		
			 }
		return responseBody;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteTeacherDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Deleting Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.deleteTeacherDetails(id);
			response.setData(teacherEntity);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details Deleted Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			 responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularTeacherDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.getParticularTeacherDetails(id);
			response.setData(teacherEntity);
			response.setStatusCode(200);
			response.setStatusText("Teacher Details Retrieved Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			 responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
