package com.school.controller;
import java.util.ArrayList;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.TeacherService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	static Logger logger = Logger.getLogger("TeacherController.class");
	
	@PostMapping
	public ResponseEntity<Response> addTeacherDetails(@RequestBody Teacher teacherDetails)
	{
		logger.debug("In Adding Teacher details...");
		ResponseEntity<Response> responseBody = null;
		Long teacherId = null;
		try {
			teacherId = teacherService.addTeacherDetails(teacherDetails);
			responseBody =  ResponseUtil.getResponse(200,"Teacher Details Added Successfully",teacherId);
		} catch (ServiceException e) {
		    responseBody = ResponseUtil.getResponse(500,e.getMessage(),teacherId);
		}
		return responseBody;
	}
	@GetMapping
	public ResponseEntity<Response> getAllTeacherDetails()
	{
		logger.debug("In Retrieving All Teacher details...");
		ResponseEntity<Response> responseBody = null;
		List<TeacherEntity> teacherEntityList = new ArrayList<>();
		 try {
			teacherEntityList = teacherService.getAllTeacherDetails();
			responseBody =  ResponseUtil.getResponse(200,"Teacher Details Retrieved Successfully",teacherEntityList);
		} catch (ServiceException e) {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),teacherEntityList);
		}
		 return responseBody;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateTeacherDetails(@PathVariable("id") Long id,@RequestBody Teacher teacherDetails) 
	{
		logger.debug("In Updating Teacher details...");
		ResponseEntity<Response> responseBody = null;
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.updateTeacherDetails(id,teacherDetails);
			responseBody =  ResponseUtil.getResponse(200,"Teacher Details Updated Successfully",teacherEntity);
		} catch (ServiceException e) {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),teacherEntity);		
		} 
		catch (NotFoundException e) {
			 responseBody = ResponseUtil.getResponse(404,e.getMessage(),teacherEntity);		
		}
		return responseBody;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteTeacherDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Deleting Teacher details...");
		ResponseEntity<Response> responseBody = null;
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.deleteTeacherDetails(id);
			responseBody =  ResponseUtil.getResponse(200,"Teacher Details Deleted Successfully",teacherEntity);
		} catch (ServiceException e) {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),teacherEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),teacherEntity);
		}
		return responseBody;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response> getParticularTeacherDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving Teacher details...");
		ResponseEntity<Response> responseBody = null;
		TeacherEntity teacherEntity = new TeacherEntity();
		try {
			teacherEntity = teacherService.getParticularTeacherDetails(id);
			responseBody =  ResponseUtil.getResponse(200,"Teacher Details Retrieved Successfully",teacherEntity);
		} catch (ServiceException e) {
			 responseBody = ResponseUtil.getResponse(500,e.getMessage(),teacherEntity);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),teacherEntity);
		}
		return responseBody;
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
