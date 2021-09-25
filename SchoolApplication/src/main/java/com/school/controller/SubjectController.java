package com.school.controller;
import com.school.exception.ClassNotFoundException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.SubjectNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;
import com.school.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof ClassNotFoundException | e instanceof SubjectNotFoundException)
		{
			logger.error("Error Occured while Processing Subject Details,Enter Valid Code");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Subject Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	@Autowired 
    private SubjectService subjectService;
	
	static Logger logger = Logger.getLogger("SubjectController.class");
	
	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addSubject(@PathVariable Long roomNo,@RequestBody Subject subject) 
	{
		logger.debug("In Adding Subject Details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		String subjectCode = null;
		try {
			 subjectCode = subjectService.addSubject(roomNo,subject);
			 response.setData(subjectCode);
			 response.setStatusCode(200);
			 response.setStatusText("Subject Details Added Successfully");
			 responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
		     responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@GetMapping("/{roomNo}")
	public ResponseEntity<Response> getAllSubject(@PathVariable("roomNo") Long roomNo) 
	{
		logger.debug("In Retrieving All Subject Details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		List<SubjectEntity> subjectEntity = new ArrayList<SubjectEntity>();
		try {
			subjectEntity = subjectService.getAllSubject(roomNo);
			response.setData(subjectEntity);
			response.setStatusCode(200);
			response.setStatusText("Subject Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@GetMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> getParticularSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  
     {
		logger.debug("In Retrieving Subject Details...");
		ResponseEntity<Response> responseBody=null;
		Response response = new Response();
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.getParticularSubject(roomNo,code);
			response.setData(subjectEntity);
			response.setStatusCode(200);
			response.setStatusText("Subject Details Retrieved Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	 }
	
	@PutMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> updateSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code,@RequestBody Subject subject) {
		logger.debug("In Updating Subject Details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity = subjectService.updateSubject(roomNo,code,subject);
			response.setData(subjectEntity);
			response.setStatusCode(200);
			response.setStatusText("Subject Details Updated Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	
	@DeleteMapping("/{roomNo}/{code}")
	public ResponseEntity<Response> deleteSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  {
		logger.debug("In Deleting Subject Details...");
		ResponseEntity<Response> responseBody=null;
		Response response = new Response();
		SubjectEntity subjectEntity = new SubjectEntity();
		try {
			subjectEntity= subjectService.deleteSubject(roomNo,code);
			response.setData(subjectEntity);
			response.setStatusCode(200);
			response.setStatusText("Subject Details Deleted Successfully");
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
