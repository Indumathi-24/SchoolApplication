package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.TeacherLogin;
import com.school.exception.ServiceException;
import com.school.service.TeacherLoginService;

@RestController
@RequestMapping("/api/login")
public class TeacherLoginController {

	@Autowired
	private TeacherLoginService teacherLoginService;
	
	@PostMapping("/{id}")
	public ResponseEntity<String> createLogin(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		return teacherLoginService.createLogin(id,login);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLoginDetails(@PathVariable("id") Long id)
	{
		ResponseEntity<?> responseBody =null;
		try {
			responseBody = teacherLoginService.getLoginDetails(id);
		} catch (ServiceException e) {
			responseBody = new ResponseEntity<>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
	   return responseBody;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateLoginDetails(@PathVariable("id") Long id,@RequestBody TeacherLogin login)
	{
		ResponseEntity<String> response =null;
		try {
			response= teacherLoginService.updateLoginDetails(id,login);
		}
		catch(ServiceException e)
		{
			response = new ResponseEntity<>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
