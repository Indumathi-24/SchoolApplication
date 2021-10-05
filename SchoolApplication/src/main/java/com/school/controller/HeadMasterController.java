package com.school.controller;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.ConstraintViolationException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/headMaster")
@CrossOrigin("http://localhost:4200")
public class HeadMasterController {
	
	static Logger logger = Logger.getLogger("HeadMasterController.class");
	@Autowired
	private HeadMasterService headMasterServiceImpl;
	@PostMapping
	public ResponseEntity<Response> addHeadMasterDetails(@Valid @RequestBody HeadMaster headMaster)
	{
		logger.debug("In Adding HeadMaster details...");
		ResponseEntity<Response> response = null;
		Long headMasterId =0l;
		try {
			headMasterId = headMasterServiceImpl.addHeadMasterDetails(headMaster);
			response = ResponseUtil.getResponse(200, "HeadMaster Details Added Succesfully", headMasterId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMasterId);
		} catch (NotFoundException e) {
			if(e instanceof ConstraintViolationException)
			{
			  response = ResponseUtil.getResponse(422,e.getMessage(),headMasterId);
			}
			else
			{
			  response = ResponseUtil.getResponse(404,e.getMessage(),headMasterId);
			}
		}
		return response;
	}
	
	@GetMapping
	public ResponseEntity<Response> getAllHeadMasterDetails()
	{
		logger.debug("In Retrieving All HeadMaster details...");
		ResponseEntity<Response> response = null;
		List<HeadMasterEntity> headMasterList = null;
		try {
		    headMasterList = headMasterServiceImpl.getAllHeadMasterDetails();
			response = ResponseUtil.getResponse(200, "HeadMaster Details Retrieved Succesfully", headMasterList);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMasterList);
		}
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateHeadMasterDetails(@PathVariable("id") Long id,@Valid @RequestBody HeadMaster headMasterDetails)
	{
		logger.debug("In Updating HeadMaster details...");
		ResponseEntity<Response> response = null;
		HeadMasterEntity headMaster =null;
		try {
			headMaster = headMasterServiceImpl.updateHeadMasterDetails(id,headMasterDetails);
			response = ResponseUtil.getResponse(200, "HeadMaster Details Updated Succesfully", headMaster);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMaster);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), headMaster);
		}
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteHeadMasterDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Deleting HeadMaster details...");
		ResponseEntity<Response> response = null;
		HeadMasterEntity headMaster = null;
		try {
			headMaster =  headMasterServiceImpl.deleteHeadMasterDetails(id);
			response = ResponseUtil.getResponse(200, "HeadMaster Details Deleted Succesfully", headMaster);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMaster);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), headMaster);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularHeadMasterDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving HeadMaster details...");
		ResponseEntity<Response> response = null;
		HeadMasterEntity headMaster = null;
		try {
			headMaster = headMasterServiceImpl.getParticularHeadMasterDetails(id);
			response = ResponseUtil.getResponse(200, "HeadMaster Details Retrieved Succesfully", headMaster);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMaster);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), headMaster);
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
