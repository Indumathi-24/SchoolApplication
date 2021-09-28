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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.MarkService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
	
	static Logger logger = Logger.getLogger("MarkController.class");
	@Autowired
	
	MarkService markService;
	
	@PostMapping("/{rollNo}")
	public ResponseEntity<Response> addMark(@PathVariable("rollNo") Long rollNo,@Valid @RequestBody Mark mark)
	{
		logger.debug("In Adding Mark details...");
		ResponseEntity<Response> response=null;
		Long markId = 0l;
		try {
			markId=markService.addMark(rollNo,mark);
			response = ResponseUtil.getResponse(200,"Mark Details Added Successfully",markId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),markId);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),markId);
		}
		return response;
	}

	@PutMapping("/{code}/{rollNo}")
	public ResponseEntity<Response> updateMark(@PathVariable("code") String code,@PathVariable("rollNo") Long rollNo,@Valid @RequestBody Mark mark){
		logger.debug("In Updating Mark details...");
		ResponseEntity<Response> response=null;
		int count = 0;
		try {
			count=markService.updateMark(code,rollNo,mark);
			response = ResponseUtil.getResponse(200,"Mark Details Updated Successfully",count);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),count);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),count);
		}
		return response;
	}
	
	@GetMapping("/{code}/{rollNo}")
	public ResponseEntity<Response> getMarks(@PathVariable("code") String code,@PathVariable("rollNo") Long rollNo)
	{
		logger.debug("In Retrieving Marks details...");
		ResponseEntity<Response> response=null;
		List<MarkEntity> markList = new ArrayList<>();
		try {
			markList=markService.getMarks(code,rollNo);
			response = ResponseUtil.getResponse(200,"Mark Details Retrieved Successfully",markList);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),markList);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),markList);
		}
		return response;
	}
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<Response> getAllTermMarks(@PathVariable("rollNo") Long rollNo)
	{
		logger.debug("In Retrieving Marks details...");
		ResponseEntity<Response> response=null;
		List<MarkEntity> markList = new ArrayList<>();
		try {
			markList=markService.getAllTermMarks(rollNo);
			response = ResponseUtil.getResponse(200,"Mark Details Retrieved Successfully",markList);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),markList);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),markList);
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
