package com.school.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.HeadMasterLogin;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterLoginService;

@RestController
@RequestMapping("/api/headMasterLogin")
@CrossOrigin("http://localhost:4200")
public class HeadMasterLoginController {
	static Logger logger = Logger.getLogger("HeadMasterLoginController.class");
	@Autowired
	private HeadMasterLoginService headMasterLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id,@RequestBody HeadMasterLogin login)
	{
		logger.debug("In Adding HeadMaster Login details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Long headMasterLogin = headMasterLoginService.createLogin(id,login);
			response.setData(headMasterLogin);
			response.setStatusCode(200);
			response.setStatusText("Login Details Added Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving HeadMaster Login details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			HeadMasterLogin headMasterLogin = headMasterLoginService.getLoginDetails(id);
			response.setData(headMasterLogin);
			response.setStatusCode(200);
			response.setStatusText("Login Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
	  return responseBody;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("id") Long id,@RequestBody HeadMasterLogin login)
	{
		logger.debug("In Updating HeadMaster Login details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Integer count= headMasterLoginService.updateLoginDetails(id,login);
			response.setData(count);
			response.setStatusCode(200);
			response.setStatusText("Login Details Updated Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	@GetMapping("/loginId/{autoId}")
	public ResponseEntity<Response> getParticularLoginDetails(@PathVariable("autoId") Long autoId)
	{
		logger.debug("In Retrieving HeadMaster Login Id...");
		ResponseEntity<Response> responseBody=null;
		Response response = new Response();
		try {
			Long id = headMasterLoginService.getParticularLoginDetails(autoId);
			response.setData(id);
			response.setStatusCode(200);
			response.setStatusText("Login Id Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		}catch(ServiceException e)
		{
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
}
