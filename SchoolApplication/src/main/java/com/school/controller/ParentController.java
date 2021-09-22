package com.school.controller;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Parent;
import com.school.exception.ServiceException;
import com.school.service.ParentService;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
	  static Logger logger = Logger.getLogger("ParentController.class");
      @Autowired
      private ParentService parentService;
      
      @PostMapping("/{rollNo}")
      public ResponseEntity<Response> addParent(@PathVariable Long rollNo,@RequestBody Parent parent) {
    	  logger.debug("In Adding Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  Response response = new Response();
    	  try {
    		  Long parentId= parentService.addParent(rollNo,parent);
    		  response.setData(parentId);
  			  response.setStatusCode(200);
  			  response.setStatusText("Parent Details Added Successfully");
  			  responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
    	  } 
    	  catch (ServiceException e) {
    		  response.setStatusCode(404);
  			  response.setStatusText(e.getMessage());
    		  responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
    	  }
    	  return responseBody;
      	}
      
      @GetMapping("/{rollNo}")
      public ResponseEntity<Response> getParent(@PathVariable Long rollNo)  {
    	  logger.debug("In Retrieving Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  Response response = new Response();
    	  try {
			List<Parent> parent = parentService.getParent(rollNo);
			response.setData(parent);
			response.setStatusCode(200);
			response.setStatusText("Parent Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
    	  return responseBody;
      }
      /*@GetMapping("/getParent/{id}")
      public Parent getParent(@PathVariable("id") Long id) throws ParentNotFoundException {
    	  return parentService.getParent(id);
      }*/
      
      @PutMapping("/{id}")
      public ResponseEntity<Response> updateParent(@PathVariable Long id,@RequestBody Parent parent)  
      {
    	  logger.debug("In Updating Parent details...");
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  try {
			Parent parentDetail = parentService.updateParent(id,parent);
			response.setData(parentDetail);
			response.setStatusCode(200);
			response.setStatusText("Parent Details Updated Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
    	  	}
    	  catch (ServiceException e) {
    		response.setStatusCode(404);
  			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
    	  	}
    	  return responseBody;
      }
      
      @DeleteMapping("/{id}")
      public ResponseEntity<Response> deleteParent(@PathVariable Long id) 
      {
    	  logger.debug("In Deleting Parent details...");
    	  ResponseEntity<Response> responseBody=null;
    	  Response response = new Response();
    	  try {
			Parent parent = parentService.deleteParent(id);
			response.setData(parent);
			response.setStatusCode(200);
			response.setStatusText("Parent Details Deleted Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
    	  	} 
    	  catch (ServiceException e) {
    		response.setStatusCode(404);
    		response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
    	  	}
    	  return responseBody;
      }
      
}
