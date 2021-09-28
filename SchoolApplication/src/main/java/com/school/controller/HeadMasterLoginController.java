package com.school.controller;
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
import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterLoginService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/headMasterLogin")
@CrossOrigin("http://localhost:4200")
public class HeadMasterLoginController {
	
	static Logger logger = Logger.getLogger("HeadMasterLoginController.class");
	@Autowired
	private HeadMasterLoginService headMasterLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id,@Valid @RequestBody HeadMasterLogin login)
	{
		logger.debug("In Adding HeadMaster Login details...");
		ResponseEntity<Response> response = null;
		Long headMasterLoginId=0l;
		try {
			headMasterLoginId = headMasterLoginService.createLogin(id,login);
			response = ResponseUtil.getResponse(200,"Login Details Added Successfully", headMasterLoginId);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMasterLoginId);
			
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), headMasterLoginId);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving HeadMaster Login details...");
		ResponseEntity<Response> response = null;
		HeadMasterLoginEntity headMasterLogin = null; 
		try {
			headMasterLogin = headMasterLoginService.getLoginDetails(id);
			response = ResponseUtil.getResponse(200,"Login Details Retrieved Successfully", headMasterLogin);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), headMasterLogin);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), headMasterLogin);
		}
	  return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("id") Long id,@RequestBody HeadMasterLogin login)
	{
		logger.debug("In Updating HeadMaster Login details...");
		ResponseEntity<Response> response = null;
		Integer count = null;
		try {
			count= headMasterLoginService.updateLoginDetails(id,login);
			response = ResponseUtil.getResponse(200,"Login Details Updated Successfully", count);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(), count);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), count);
		}
		return response;
	}
	@GetMapping("/loginId/{autoId}")
	public ResponseEntity<Response> getParticularLoginDetails(@PathVariable("autoId") Long autoId)
	{
		logger.debug("In Retrieving HeadMaster Login Id...");
		ResponseEntity<Response> response=null;
		Long id = 0l;
		try {
			id = headMasterLoginService.getParticularLoginDetails(autoId);
			response = ResponseUtil.getResponse(200,"Login Id Retrieved Successfully",id);
		}catch(ServiceException e)
		{
			response = ResponseUtil.getResponse(500,e.getMessage(), id);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(), id);
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
