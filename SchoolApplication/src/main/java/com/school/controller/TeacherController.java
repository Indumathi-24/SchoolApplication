package com.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.school.entity.Teacher;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;
import com.school.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherServiceImpl;
	@PostMapping
	public ResponseEntity<String> addTeacherDetails(@RequestBody Teacher teacherDetails)
	{
		return teacherServiceImpl.addTeacherDetails(teacherDetails);
	}
	@GetMapping
	public ResponseEntity<?> getAllTeacherDetails()
	{
		return teacherServiceImpl.getAllTeacherDetails();
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateTeacherDetails(@PathVariable("id") Long id,@RequestBody Teacher teacherDetails) throws TeacherNotFoundException
	{
		ResponseEntity<String> response=null;
		try {
			response = teacherServiceImpl.updateTeacherDetails(id,teacherDetails);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTeacherDetails(@PathVariable("id") Long id)
	{
		ResponseEntity<String> response=null;
		try {
			response = teacherServiceImpl.deleteTeacherDetails(id);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getParticularTeacherDetails(@PathVariable("id") Long id)
	{
		ResponseEntity<?> response=null;
		try {
			response = teacherServiceImpl.getParticularTeacherDetails(id);
		} catch (ServiceException e) {
			response = new ResponseEntity<>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
