package com.school.controller;
import com.school.service.ClassService;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.Class;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.ClassNotFoundException;
import com.school.entity.ClassEntity;

@RestController
@RequestMapping("/api/class")
@CrossOrigin("http://localhost:4200")
public class ClassController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof ClassNotFoundException)
		{
			logger.error("Error Occured while Processing Class Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Class Login Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	 static Logger logger = Logger.getLogger("ClassController.class");
	 @Autowired
	 private ClassService classService;
     
	 @PostMapping
     public ResponseEntity<Response> addClass(@RequestBody Class classDetail){
		logger.debug("In Add Class Details Method");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long roomNo=null;
		try {
			roomNo = classService.addClass(classDetail);
			response.setData(roomNo);
			response.setStatusText("Class Details Added");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			responseBody = errorStatement(e);
		}
		
		return responseBody;
	 }
	 @GetMapping
	 public ResponseEntity<Response> getAllClass()
	 {
		 logger.debug("In Get All Class Details Method");
		 ResponseEntity<Response> responseBody = null;
		 Response response = new Response();
		 List<ClassEntity> classList;
		try {
			classList = classService.getAllClass();
			response.setData(classList);
			response.setStatusText("All Class Details Retrieved");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			responseBody = errorStatement(e);
		}
		
		 return responseBody;
	 }
	 
	 @GetMapping("/{roomNo}")
	 public ResponseEntity<Response> getParticularClass(@PathVariable("roomNo") Long roomNo){
		 logger.debug("In Get Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 Response response =new Response();
		 try {
			  ClassEntity classDetail= classService.getParticularClass(roomNo);
			 response.setData(classDetail);
			 response.setStatusText("Particular Class Details Retrieved");
			 response.setStatusCode(200);
			 responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody; 
	 }
	 @PutMapping("/{roomNo}")
	 public ResponseEntity<Response> updateClass(@PathVariable("roomNo") Long roomNo,@RequestBody Class classDetail) 
	 {
		 logger.debug("In Update Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 Response response = new Response();
		 try {
			 	ClassEntity classDetails = classService.updateClass(roomNo,classDetail);
			 	response.setData(classDetails);
			 	response.setStatusText("Class Details Updated Successfully");
				response.setStatusCode(200);
			 	responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
			} 
		 catch (ServiceException | NotFoundException e) 
		    {
			 responseBody = errorStatement(e);
			}
	    return responseBody; 
	 }
}
