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
import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.HeadMasterService;

@RestController
@RequestMapping("/api/headMaster")
@CrossOrigin("http://localhost:4200")
public class HeadMasterController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof HeadMasterNotFoundException)
		{
			logger.error("Error Occured while Processing HeadMaster Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing HeadMaster Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	static Logger logger = Logger.getLogger("HeadMasterController.class");
	@Autowired
	private HeadMasterService headMasterServiceImpl;
	@PostMapping
	public ResponseEntity<Response> addHeadMasterDetails(@RequestBody HeadMaster headMaster)
	{
		logger.debug("In Adding HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			Long headMasterId = headMasterServiceImpl.addHeadMasterDetails(headMaster);
			response.setData(headMasterId);
			response.setStatusCode(200);
			response.setStatusText("HeadMaster Details Added Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			responseBody = errorStatement(e);
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
			List<HeadMasterEntity> headMasterList = headMasterServiceImpl.getAllHeadMasterDetails();
			response.setData(headMasterList);
			response.setStatusCode(200);
			response.setStatusText("All Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateHeadMasterDetails(@PathVariable("id") Long id,@RequestBody HeadMaster headMasterDetails)
	{
		logger.debug("In Updating HeadMaster details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		try {
			HeadMasterEntity headMaster = headMasterServiceImpl.updateHeadMasterDetails(id,headMasterDetails);
			response.setData(headMaster);
			response.setStatusCode(200);
			response.setStatusText("Particular Details Updated Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
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
			HeadMasterEntity headMaster =  headMasterServiceImpl.deleteHeadMasterDetails(id);
			response.setData(headMaster);
			response.setStatusCode(200);
			response.setStatusText("Particular Details Deleted Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
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
			HeadMasterEntity headMaster = headMasterServiceImpl.getParticularHeadMasterDetails(id);
			response.setData(headMaster);
			response.setStatusCode(200);
			response.setStatusText("Particular HeadMaster Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response, new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
