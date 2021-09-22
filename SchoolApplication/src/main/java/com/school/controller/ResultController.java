package com.school.controller;

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
import com.school.entity.Result;
import com.school.exception.ServiceException;
import com.school.service.ResultService;

@RestController
@RequestMapping("/api/result")
public class ResultController {
      @Autowired
      private ResultService resultService;
      
      @PostMapping("/{rollNo}")
      public ResponseEntity<Response> addResult(@PathVariable Long rollNo,@RequestBody Result result)
      {
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  Long resultId = null;
    	  try {
			resultId = resultService.addResult(rollNo,result);
			response.setData(resultId);
			response.setStatusCode(200);
			response.setStatusText("Result Details For Student Saved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);		
			}
    	  return responseBody;
      }
      
      @GetMapping("/{rollNo}")
      public ResponseEntity<Response> getResult(@PathVariable Long rollNo)
      {
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  Result result = new Result();
    	  try {
			result = resultService.getResult(rollNo);
			response.setData(result);
			response.setStatusCode(200);
			response.setStatusText("Result Details For Student Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);	
		}
    	  return responseBody;
      }
      @PutMapping("/{rollNo}/{resultId}")
      public ResponseEntity<Response> updateResult(@PathVariable("rollNo") Long rollNo,@PathVariable("resultId") Long resultId,@RequestBody Result resultDetail)
      {
    	  ResponseEntity<Response> responseBody = null;
    	  Response response = new Response();
    	  Result result = new Result();  
    	  try 
    	  {
    		  result = resultService.updateResult(rollNo,resultId,resultDetail);
    		  response.setData(result);
  			  response.setStatusCode(200);
  			  response.setStatusText("Result Details For Student Updated Successfully");
  			  responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
    	  } catch (ServiceException e) {
  			response.setStatusCode(404);
  			response.setStatusText(e.getMessage());
  			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);	
  		}
    	  return responseBody;
      }
}
