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
import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterLoginEntity;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterLoginService;

@RestController
@RequestMapping("/api/headMasterLogin")
@CrossOrigin("http://localhost:4200")
public class HeadMasterLoginController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof HeadMasterNotFoundException)
		{
			logger.error("Error Occured while Processing HeadMaster Login Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing HeadMaster Login Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
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
			Long headMasterLoginId = headMasterLoginService.createLogin(id,login);
			response.setData(headMasterLoginId);
			response.setStatusCode(200);
			response.setStatusText("Login Details Added Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
			
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
			HeadMasterLoginEntity headMasterLogin = headMasterLoginService.getLoginDetails(id);
			response.setData(headMasterLogin);
			response.setStatusCode(200);
			response.setStatusText("Login Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
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
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
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
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		}catch(ServiceException e)
		{
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
