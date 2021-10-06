package com.school.controller;
import com.school.service.ClassService;

import java.util.ArrayList;
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
import com.school.exception.ConstraintViolationException;
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
		ResponseEntity<Response> response = null;
		Long roomNo=0l;
		try {
			roomNo = classService.addClass(classDetail);
			response = ResponseUtil.getResponse(200,"Class Details Added Successfully",roomNo);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),roomNo);
		}catch (NotFoundException e) {
			if(e instanceof ConstraintViolationException)
			{
			  response = ResponseUtil.getResponse(422,e.getMessage(),roomNo);
			}
			else
			{
			  response = ResponseUtil.getResponse(404,e.getMessage(),roomNo);
			}
		}
		
		return response;
	 }
	 @GetMapping
	 public ResponseEntity<Response> getAllClass()
	 {
		 logger.debug("In Get All Class Details Method");
		 ResponseEntity<Response> response = null;
		 List<ClassEntity> classList=null;
		try {
			classList = classService.getAllClass();
			response = ResponseUtil.getResponse(200,"Class Details Retrieved Successfully",classList);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),classList);
		}
		
		 return response;
	 }
	 
	 @GetMapping("/{roomNo}")
	 public ResponseEntity<Response> getParticularClass(@PathVariable("roomNo") Long roomNo){
		 logger.debug("In Get Class Details Method");
		 ResponseEntity<Response> response=null;
		 ClassEntity classDetail = null;
		 try {
			 classDetail= classService.getParticularClass(roomNo);
			 response = ResponseUtil.getResponse(200,"Particular Class Details Retrieved",classDetail);
		} catch (ServiceException  e) {
			 response = ResponseUtil.getResponse(500,e.getMessage(),classDetail);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),classDetail);
		}
		return response; 
	 }
	 @GetMapping("/{standard}/{section}")
	 public ResponseEntity<Response> getRoomNo(@PathVariable("standard") String standard,@PathVariable("section") String section){
		 logger.debug("In Get Class Room No Method");
		 ResponseEntity<Response> response=null;
		 Long roomNo = null;
		 try {
			 roomNo = classService.getRoomNo(standard,section);
			 response = ResponseUtil.getResponse(200,"Room No's Retrieved",roomNo);
		} catch (ServiceException  e) {
			 response = ResponseUtil.getResponse(500,e.getMessage(),roomNo);
		}
		return response; 
	 }
	 
	 @GetMapping("/roomNoList/{roomNoList}")
	 public ResponseEntity<Response> getClassList(@PathVariable("roomNoList") List<Long> roomNoList){
		 logger.debug("In Get Class Details Method");
		 ResponseEntity<Response> response=null;
		 List<ClassEntity> classList = new ArrayList<>();
		 try {
			 classList= classService.getClassList(roomNoList);
			 response = ResponseUtil.getResponse(200,"Class Details Retrieved",classList);
		} catch (ServiceException  e) {
			 response = ResponseUtil.getResponse(500,e.getMessage(),classList);
		} 
		return response; 
	 }
	 
	 @PutMapping("/{roomNo}")
	 public ResponseEntity<Response> updateClass(@PathVariable("roomNo") Long roomNo,@Valid @RequestBody Class classDetail) 
	 {
		 logger.debug("In Update Class Details Method");
		 ResponseEntity<Response> response=null;
		 ClassEntity classDetails = null;
		 try {
			 	classDetails = classService.updateClass(roomNo,classDetail);
			 	response = ResponseUtil.getResponse(200,"Class Details Updated",classDetails);
			} 
		 catch (ServiceException  e) 
		    {
			 response = ResponseUtil.getResponse(500,e.getMessage(),classDetails);
			} catch (NotFoundException e) {
				if(e instanceof ConstraintViolationException)
				{
				  response = ResponseUtil.getResponse(422,e.getMessage(),classDetails);
				}
				else
				{
				  response = ResponseUtil.getResponse(404,e.getMessage(),classDetails);
				}
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
