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
import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.StudentNotFoundException;
import com.school.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof StudentNotFoundException)
		{
			logger.error("Error Occured while Processing Student's Result Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Student's Result Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
      @Autowired
      private ResultService resultService;
      
      static Logger logger = Logger.getLogger("ResultController.class");
      
      @PostMapping("/{rollNo}")
      public ResponseEntity<Response> addResult(@PathVariable Long rollNo,@RequestBody Result result)
      {
    	  logger.debug("In Adding Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  Long resultId = null;
    	  try {
			resultId = resultService.addResult(rollNo,result);
			response.setData(resultId);
			response.setStatusCode(200);
			response.setStatusText("Result Details For Student Saved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);		
			}
    	  return responseBody;
      }
      
      @GetMapping("/{rollNo}")
      public ResponseEntity<Response> getResult(@PathVariable Long rollNo)
      {
    	  logger.debug("In Retrieving Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  ResultEntity result = new ResultEntity();
    	  try {
			result = resultService.getResult(rollNo);
			response.setData(result);
			response.setStatusCode(200);
			response.setStatusText("Result Details For Student Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);	
		}
    	  return responseBody;
      }
      @PutMapping("/{rollNo}/{resultId}")
      public ResponseEntity<Response> updateResult(@PathVariable("rollNo") Long rollNo,@PathVariable("resultId") Long resultId,@RequestBody Result resultDetail)
      {
    	  logger.debug("In Updating Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  ResultEntity result = new ResultEntity();  
    	  try 
    	  {
    		  result = resultService.updateResult(rollNo,resultId,resultDetail);
    		  response.setData(result);
  			  response.setStatusCode(200);
  			  response.setStatusText("Result Details For Student Updated Successfully");
  			  responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
    	  } catch (ServiceException | NotFoundException e) {
    		  responseBody = errorStatement(e);	
  		}
    	  return responseBody;
      }
}
