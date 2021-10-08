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
      
      @PostMapping("/{roomNo}/{rollNo}")
      public ResponseEntity<Response> addResult(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo, @RequestBody Result result)
      {
    	  logger.debug("In Adding Student's Result...");
    	  ResponseEntity<Response> response = null;
    	  Long resultId = null;
    	  try {
			resultId = resultService.addResult(roomNo,rollNo,result);
			response = ResponseUtil.getResponse(200,"Result Details For Student Saved Successfully",resultId);
		} catch (ServiceException e) {
		    response = ResponseUtil.getResponse(500,e.getMessage(),resultId);
			} catch (NotFoundException e) {
		   response = ResponseUtil.getResponse(404,e.getMessage(),resultId);
		}
    	  return response;
      }
      
      @GetMapping("/{roomNo}/{rollNo}")
      public ResponseEntity<Response> getResult(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo)
      {
    	  logger.debug("In Retrieving Student's Result...");
    	  ResponseEntity<Response> response = null;
    	  ResultEntity result = new ResultEntity();
    	  try {
			result = resultService.getResult(roomNo,rollNo);
			response = ResponseUtil.getResponse(200,"Result Details For Student Retrieved Successfully",result);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),result);	
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),result);
		}
    	  return response;
      }
      
      @GetMapping("/class/{roomNo}")
      public ResponseEntity<Response> getResultByClass(@PathVariable("roomNo") Long roomNo)
      {
    	  logger.debug("In Retrieving Student's Result by Class");
    	  ResponseEntity<Response> response = null;
    	  List<ResultEntity> resultList = new ArrayList<ResultEntity>();
    	  try {
			resultList = resultService.getResultByClass(roomNo);
			response = ResponseUtil.getResponse(200,"Result Details For Student Retrieved Successfully",resultList);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),resultList);	
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),resultList);
		}
    	  return response;
      }
      @PutMapping("/{roomNo}/{rollNo}/{resultId}")
      public ResponseEntity<Response> updateResult(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo,@PathVariable("resultId") Long resultId,@Valid @RequestBody Result resultDetail)
      {
    	  logger.debug("In Updating Student's Result...");
    	  ResponseEntity<Response> response = null;
    	  ResultEntity result = new ResultEntity();  
    	  try 
    	  {
    		  result = resultService.updateResult(roomNo,rollNo,resultId,resultDetail);
  			response = ResponseUtil.getResponse(200,"Result Details For Student Updated Successfully",result);
    	  } catch (ServiceException e) {
    		  response = ResponseUtil.getResponse(500,e.getMessage(),result);	
  		} catch (NotFoundException e) {
  			response = ResponseUtil.getResponse(404,e.getMessage(),result);
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
