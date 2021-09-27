package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.ResultService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/result")
@CrossOrigin("http://localhost:4200")
public class ResultController {
	
      @Autowired
      private ResultService resultService;
      
      static Logger logger = Logger.getLogger("ResultController.class");
      
      @PostMapping("/{rollNo}")
      public ResponseEntity<Response> addResult(@PathVariable Long rollNo,@Valid @RequestBody Result result)
      {
    	  logger.debug("In Adding Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  Long resultId = null;
    	  try {
			resultId = resultService.addResult(rollNo,result);
			responseBody = ResponseUtil.getResponse(200,"Result Details For Student Saved Successfully",resultId);
		} catch (ServiceException e) {
		    responseBody = ResponseUtil.getResponse(500,e.getMessage(),resultId);
			} catch (NotFoundException e) {
		   responseBody = ResponseUtil.getResponse(404,e.getMessage(),resultId);
		}
    	  return responseBody;
      }
      
      @GetMapping("/{rollNo}")
      public ResponseEntity<Response> getResult(@PathVariable Long rollNo)
      {
    	  logger.debug("In Retrieving Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  ResultEntity result = new ResultEntity();
    	  try {
			result = resultService.getResult(rollNo);
			responseBody = ResponseUtil.getResponse(200,"Result Details For Student Retrieved Successfully",result);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),result);	
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),result);
		}
    	  return responseBody;
      }
      
      @GetMapping("/class/{roomNo}")
      public ResponseEntity<Response> getResultByClass(@PathVariable("roomNo") Long roomNo)
      {
    	  logger.debug("In Retrieving Student's Result by Class");
    	  ResponseEntity<Response> responseBody = null;
    	  List<ResultEntity> resultList = new ArrayList<ResultEntity>();
    	  try {
			resultList = resultService.getResultByClass(roomNo);
			responseBody = ResponseUtil.getResponse(200,"Result Details For Student Retrieved Successfully",resultList);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),resultList);	
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),resultList);
		}
    	  return responseBody;
      }
      @PutMapping("/{rollNo}/{resultId}")
      public ResponseEntity<Response> updateResult(@PathVariable("rollNo") Long rollNo,@PathVariable("resultId") Long resultId,@Valid @RequestBody Result resultDetail)
      {
    	  logger.debug("In Updating Student's Result...");
    	  ResponseEntity<Response> responseBody = null;
    	  ResultEntity result = new ResultEntity();  
    	  try 
    	  {
    		  result = resultService.updateResult(rollNo,resultId,resultDetail);
  			responseBody = ResponseUtil.getResponse(200,"Result Details For Student Updated Successfully",result);
    	  } catch (ServiceException e) {
    		  responseBody = ResponseUtil.getResponse(500,e.getMessage(),result);	
  		} catch (NotFoundException e) {
  			responseBody = ResponseUtil.getResponse(404,e.getMessage(),result);
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
