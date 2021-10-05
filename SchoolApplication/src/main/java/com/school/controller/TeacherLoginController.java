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
import com.school.dto.TeacherLogin;
import com.school.entity.TeacherLoginEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

import com.school.service.TeacherLoginService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("http://localhost:4200")
public class TeacherLoginController {
	
	
	static Logger logger = Logger.getLogger("TeacherLoginController.class");
	
	@Autowired
	private TeacherLoginService teacherLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<Response> createLogin(@PathVariable("id") Long id,@Valid @RequestBody TeacherLogin login)
	{
		logger.debug("In Adding Teacher Login details...");
		ResponseEntity<Response> response =null;
		Long loginId = null;
		try {
			loginId = teacherLoginService.createLogin(id,login);
			response =  ResponseUtil.getResponse(200,"Teacher Login Details Added Successfully",loginId);
		} catch (ServiceException  e) {
			response =  ResponseUtil.getResponse(500,e.getMessage(),loginId);
		} catch (NotFoundException e) {
			response =  ResponseUtil.getResponse(404,e.getMessage(),loginId);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getLoginDetails(@PathVariable("id") Long id)
	{
		logger.debug("In Retrieving Teacher Login details...");
		ResponseEntity<Response> response =null;
		TeacherLoginEntity teacherLoginEntity = new TeacherLoginEntity();
		try {
			teacherLoginEntity = teacherLoginService.getLoginDetails(id);
			response = ResponseUtil.getResponse(200,"Teacher Login Details Retrieved Successfully",teacherLoginEntity);
		} catch (ServiceException e) {
			response =  ResponseUtil.getResponse(500,e.getMessage(),teacherLoginEntity);
		} catch (NotFoundException e) {
			response =  ResponseUtil.getResponse(404,e.getMessage(),teacherLoginEntity);
		}
	   return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateLoginDetails(@PathVariable("id") Long id,@Valid @RequestBody TeacherLogin login)
	{
		logger.debug("In Updating Teacher Login details...");
		ResponseEntity<Response> response =null;
		Integer result = null;
		try {
			 result = teacherLoginService.updateLoginDetails(id,login);
			 response =  ResponseUtil.getResponse(200,"Teacher Login Details Updated Successfully",result);
		}
		catch(ServiceException  e)
		{
			response = ResponseUtil.getResponse(500,e.getMessage(),result);
		} catch (NotFoundException e) {
			response =  ResponseUtil.getResponse(404,e.getMessage(),result);
		}
		return response;
	}
	
	@GetMapping("/teacherLoginId/{autoId}")
	public ResponseEntity<Response> getParticularLoginDetails(@PathVariable("autoId") Long autoId)
	{
		logger.debug("In Retrieving Teacher Login Id...");
		ResponseEntity<Response> response=null;
		Long id = 0l;
		try {
			id = teacherLoginService.getParticularLoginDetails(autoId);
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
