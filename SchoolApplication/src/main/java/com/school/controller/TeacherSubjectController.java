package com.school.controller;
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
import com.school.entity.TeacherSubject;
import com.school.exception.ServiceException;
import com.school.service.TeacherSubjectService;

@RestController
@RequestMapping("/api/teacherSubject")
public class TeacherSubjectController {
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;
	@PostMapping("/{id}/{code}")
	public ResponseEntity<String> assignTeacherSubject(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode,@RequestBody TeacherSubject teacherSubjectDetails) 
	{
		ResponseEntity<String> response = null;
		try {
			response =teacherSubjectServiceImpl.assignTeacherSubject(teacherId,subjectCode,teacherSubjectDetails);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@PutMapping("/{id}/{code}")
	public ResponseEntity<String> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode,@RequestBody TeacherSubject teacherSubjectDetails) 
	{
		ResponseEntity<String> response = null;
		try {
			response = teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@DeleteMapping("/{id}/{code}")
	public ResponseEntity<String> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode)
	{
		ResponseEntity<String> response = null;
		try {
			response = teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
