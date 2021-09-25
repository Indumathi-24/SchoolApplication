package com.school.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.exception.SubjectNotFoundException;
import com.school.dto.TeacherSubject;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.service.TeacherSubjectService;

@RestController
@RequestMapping("/api/teacherSubject")
public class TeacherSubjectController {

	public ResponseEntity<Response> errorStatement(Exception e)
	{
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		if(e instanceof TeacherNotFoundException | e instanceof SubjectNotFoundException)
		{
			logger.error("Error Occured while Processing Teacher Subject Details,Enter Valid Id");
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		else if (e instanceof ServiceException)
		{
			logger.error("Error Occured while Processing Teacher Subject Details");
			response.setStatusCode(500);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseBody;
	}
	
	static Logger logger = Logger.getLogger("TeacherSubjectController.class");
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;
	@PostMapping("/{id}/{code}")
	public ResponseEntity<Response> assignTeacherSubject(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode, @RequestBody TeacherSubject teacherSubjectDetails) 
	{
		logger.debug("In Adding Teacher Subject details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long id;
		try {
			 id = teacherSubjectServiceImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
			 response.setData(id);
			 response.setStatusCode(200);
			 response.setStatusText("Teacher Subject Details Added Successfully");
			 responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@PutMapping("/{id}/{code}")
	public ResponseEntity<Response> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode, @RequestBody TeacherSubject teacherSubjectDetails) 
	{
		logger.debug("In Updating Teacher Subject details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Integer count;
		try {
			count = teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
			response.setData(count);
			response.setStatusCode(200);
			response.setStatusText("Teacher Subject Details Updated Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
	@DeleteMapping("/{id}/{code}")
	public ResponseEntity<Response> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode)
	{
		logger.debug("In Deleting Teacher Subject details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Integer count;
		try {
			count = teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
			response.setData(count);
			response.setStatusCode(200);
			response.setStatusText("Teacher Subject Details Deleted Successfully");
			responseBody =  new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException | NotFoundException e) {
			responseBody = errorStatement(e);
		}
		return responseBody;
	}
}
