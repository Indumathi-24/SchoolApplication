package com.school.controller;
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
import com.school.dto.Parent;
import com.school.entity.ParentEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.ParentService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
	
	  static Logger logger = Logger.getLogger("ParentController.class");
	  
      @Autowired
      private ParentService parentService;
      
      @PostMapping("/{rollNo}")
      public ResponseEntity<Response> addParent(@PathVariable Long rollNo,@Valid @RequestBody Parent parent) {
    	  logger.debug("In Adding Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  Long parentId = null;
    	  try {
    		  parentId= parentService.addParent(rollNo,parent);
  			  responseBody = ResponseUtil.getResponse(200,"Parent Details Added Successfully",parentId);
    	  } 
    	  catch (ServiceException e) {
    		  responseBody = ResponseUtil.getResponse(500,e.getMessage(), parentId);
    	  } catch (NotFoundException e) {
    		  responseBody = ResponseUtil.getResponse(404,e.getMessage(), parentId);
		}
    	  return responseBody;
      	}
      
      @GetMapping("/{rollNo}")
      public ResponseEntity<Response> getParent(@PathVariable Long rollNo)  {
    	  logger.debug("In Retrieving Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  List<ParentEntity> parent = null;
    	  try {
			parent = parentService.getParent(rollNo);
			responseBody = ResponseUtil.getResponse(200,"Parent Details Retrieved Successfully", parent);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(), parent);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(), parent);
		}
    	  return responseBody;
      }
      
      @PutMapping("/{id}")
      public ResponseEntity<Response> updateParent(@PathVariable Long id,@Valid @RequestBody Parent parent)  
      {
    	  logger.debug("In Updating Parent details...");
    	  ResponseEntity<Response> responseBody = null;
    	  ParentEntity parentDetail = null;
    	  try {
			parentDetail = parentService.updateParent(id,parent);
			responseBody = ResponseUtil.getResponse(200,"Parent Details Updated Successfully", parentDetail);
    	  	}
    	  catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(), parentDetail);
    	  	} 
    	  catch (NotFoundException e) {
    	  	responseBody = ResponseUtil.getResponse(404,e.getMessage(), parentDetail);
		}
    	  return responseBody;
      }
      
      @DeleteMapping("/{id}")
      public ResponseEntity<Response> deleteParent(@PathVariable Long id) 
      {
    	  logger.debug("In Deleting Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  ParentEntity parent = null;
    	  try {
    		  parent = parentService.deleteParent(id);
    		  responseBody = ResponseUtil.getResponse(200,"Parent Details Deleted Successfully", parent);
    	  	} 
    	  catch (ServiceException e) {
    		  responseBody = ResponseUtil.getResponse(500,e.getMessage(), parent);  
    	  	}
    	  catch (NotFoundException e) {
    	  		responseBody = ResponseUtil.getResponse(404,e.getMessage(), parent);
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
