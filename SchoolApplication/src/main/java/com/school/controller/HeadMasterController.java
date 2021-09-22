package com.school.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.school.entity.HeadMaster;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterService;

@RestController
@RequestMapping("/api/headMaster")
@CrossOrigin("http://localhost:4200")
public class HeadMasterController {
	static Logger logger = Logger.getLogger("HeadMasterController.class");
	@Autowired
	private HeadMasterService headMasterServiceImpl;
	@PostMapping
	public ResponseEntity<Response> addHeadMasterDetails(@RequestBody HeadMaster HeadMasterDetails)
	{
		logger.debug("In Adding HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Long headMasterId = headMasterServiceImpl.addHeadMasterDetails(HeadMasterDetails);
			response.setData(headMasterId);
			response.setStatusCode(200);
			response.setStatusText("HeadMaster Details Added Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	@GetMapping
	public ResponseEntity<Response> getAllHeadMasterDetails()
	{
		logger.debug("In Retrieving All HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			List<HeadMaster> headMasterList = headMasterServiceImpl.getAllHeadMasterDetails();
			response.setData(headMasterList);
			response.setStatusCode(200);
			response.setStatusText("All Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateHeadMasterDetails(@PathVariable("id") Long id,@RequestBody HeadMaster HeadMasterDetails) throws HeadMasterNotFoundException
	{
		logger.debug("In Updating HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			HeadMaster headMaster = headMasterServiceImpl.updateHeadMasterDetails(id,HeadMasterDetails);
			response.setData(headMaster);
			response.setStatusCode(200);
			response.setStatusText("Particular Details Updated Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteHeadMasterDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Deleting HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			String status =  headMasterServiceImpl.deleteHeadMasterDetails(id);
			response.setData(status);
			response.setStatusCode(200);
			response.setStatusText("Particular Details Deleted Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularHeadMasterDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			HeadMaster headMaster = headMasterServiceImpl.getParticularHeadMasterDetails(id);
			System.out.println(headMaster);
			response.setData(headMaster);
			response.setStatusCode(200);
			response.setStatusText("Particular HeadMaster Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response, new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
}
