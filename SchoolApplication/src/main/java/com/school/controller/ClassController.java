package com.school.controller;
import com.school.service.ClassService;


import java.util.List;

import javax.validation.Valid;
import com.school.util.ResponseUtil;
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
import com.school.dto.Class;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.entity.ClassEntity;

@RestController
@RequestMapping("/api/class")
@CrossOrigin("http://localhost:4200")
public class ClassController {
	 static Logger logger = Logger.getLogger("ClassController.class");
	 @Autowired
	 private ClassService classService;
     
	 @PostMapping
     public ResponseEntity<Response> addClass(@Valid @RequestBody Class classDetail){
		logger.debug("In Add Class Details Method");
		ResponseEntity<Response> responseBody = null;
		Long roomNo=0l;
		try {
			roomNo = classService.addClass(classDetail);
			responseBody = ResponseUtil.getResponse(200,"Class Details Added Successfully",roomNo);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),roomNo);
		}
		
		return responseBody;
	 }
	 @GetMapping
	 public ResponseEntity<Response> getAllClass()
	 {
		 logger.debug("In Get All Class Details Method");
		 ResponseEntity<Response> responseBody = null;
		 List<ClassEntity> classList=null;
		try {
			classList = classService.getAllClass();
			responseBody = ResponseUtil.getResponse(200,"Class Details Retrieved Successfully",classList);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),classList);
		}
		
		 return responseBody;
	 }
	 
	 @GetMapping("/{roomNo}")
	 public ResponseEntity<Response> getParticularClass(@PathVariable("roomNo") Long roomNo){
		 logger.debug("In Get Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 ClassEntity classDetail = null;
		 try {
			 classDetail= classService.getParticularClass(roomNo);
			 responseBody = ResponseUtil.getResponse(200,"Particular Class Details Retrieved",classDetail);
		} catch (ServiceException  e) {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),classDetail);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),classDetail);
		}
		return responseBody; 
	 }
	 @PutMapping("/{roomNo}")
	 public ResponseEntity<Response> updateClass(@PathVariable("roomNo") Long roomNo,@Valid @RequestBody Class classDetail) 
	 {
		 logger.debug("In Update Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 ClassEntity classDetails = null;
		 try {
			 	classDetails = classService.updateClass(roomNo,classDetail);
			 	responseBody = ResponseUtil.getResponse(200,"Class Details Updated",classDetails);
			} 
		 catch (ServiceException  e) 
		    {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),classDetails);
			} catch (NotFoundException e) {
				responseBody = ResponseUtil.getResponse(404,e.getMessage(),classDetails);
		}
	    return responseBody; 
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
