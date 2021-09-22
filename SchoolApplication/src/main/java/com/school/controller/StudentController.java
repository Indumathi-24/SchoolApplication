package com.school.controller;
import java.util.ArrayList;
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
import com.school.entity.Student;
import com.school.exception.ServiceException;
import com.school.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {
	static Logger logger = Logger.getLogger("StudentController.class");
	@Autowired 
	private StudentService studentService;
	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addStudent(@PathVariable("roomNo")Long roomNo,@RequestBody Student student){
		logger.debug("In Adding Student details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long studentId;
		try {
			studentId = studentService.addStudent(roomNo,student);
			response.setData(studentId);
			response.setStatusCode(200);
			response.setStatusText("Student Details Saved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}

	@GetMapping("/{roomNo}")
	public ResponseEntity<Response> getAllStudent(@PathVariable("roomNo") Long roomNo) 
	{
		logger.debug("In Retrieving All Student details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList=studentService.getAllStudent(roomNo);
			response.setData(studentList);
			response.setStatusCode(200);
			response.setStatusText("Student Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} 
		catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	
	@GetMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> getParticularStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo) 
     {
		logger.debug("In Retrieving Student details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Student student = new Student();
		try {
			student = studentService.getParticularStudent(roomNo,rollNo);
			response.setData(student);
			response.setStatusCode(200);
			response.setStatusText("Student Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} 
		catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	 }
	
	@PutMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> updateStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo,@RequestBody Student student){
		logger.debug("In Updating Student details...");
		ResponseEntity<Response> responseBody=null;
		Response response = new Response();
		Student studentDetail = new Student();
		try {
			studentDetail=studentService.updateStudent(roomNo,rollNo,student);
			response.setData(studentDetail);
			response.setStatusCode(200);
			response.setStatusText("Student Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
	
	@DeleteMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> deleteStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo) {
		logger.debug("In Deleting Student details...");
		ResponseEntity<Response> responseBody=null;
		Response response = new Response();
		Student studentDetail = new Student();
		try {
			studentDetail=studentService.deleteStudent(roomNo,rollNo);
			response.setData(studentDetail);
			response.setStatusCode(200);
			response.setStatusText("Student Details Retrieved Successfully");
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		}
		catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody;
	}
}
