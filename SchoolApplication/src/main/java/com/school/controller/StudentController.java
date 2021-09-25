package com.school.controller;
import java.util.ArrayList;



import java.util.List;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.Student;
import com.school.entity.StudentEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.StudentService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("http://localhost:4200")
public class StudentController {
	
	static Logger logger = Logger.getLogger("StudentController.class");
	@Autowired 
	private StudentService studentService;
	@PostMapping("/{roomNo}")
	public ResponseEntity<Response> addStudent(@PathVariable("roomNo")Long roomNo,@Valid @RequestBody Student student){
		logger.debug("In Adding Student details...");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long studentId = 0l;
		try {
			studentId = studentService.addStudent(roomNo,student);
			response.setData(studentId);
			response.setStatusCode(200);
			response.setStatusText("Student Details Saved Successfully");
			responseBody = ResponseUtil.getResponse(200,"Student Details Saved Successfully",studentId);
		} catch (ServiceException  e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),studentId);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),studentId);
		}
		return responseBody;
	}

	@GetMapping("/{roomNo}")
	public ResponseEntity<Response> getAllStudent(@PathVariable("roomNo") Long roomNo) 
	{
		logger.debug("In Retrieving All Student details...");
		ResponseEntity<Response> responseBody = null;
		List<StudentEntity> studentList = new ArrayList<>();
		try {
			studentList=studentService.getAllStudent(roomNo);
			responseBody = ResponseUtil.getResponse(200,"Student Details Retrieved Successfully",studentList);
		} 
		catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),studentList);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),studentList);
		}
		return responseBody;
	}
	
	@GetMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> getParticularStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo) 
     {
		logger.debug("In Retrieving Student details...");
		ResponseEntity<Response> responseBody = null;
		StudentEntity student = new StudentEntity();
		try {
			student = studentService.getParticularStudent(roomNo,rollNo);
			responseBody = ResponseUtil.getResponse(200,"Particular Student Details Retrieved Successfully",student);
		} 
		catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),student);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),student);
		}
		return responseBody;
	 }
	
	@PutMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> updateStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo,@Valid @RequestBody Student student){
		logger.debug("In Updating Student details...");
		ResponseEntity<Response> responseBody=null;
		StudentEntity studentDetail = new StudentEntity();
		try {
			studentDetail=studentService.updateStudent(roomNo,rollNo,student);
			responseBody = ResponseUtil.getResponse(200,"Student Details Updated Successfully",studentDetail);
		} catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),studentDetail);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),studentDetail);
		}
		return responseBody;
	}
	
	@DeleteMapping("/{roomNo}/{rollNo}")
	public ResponseEntity<Response> deleteStudent(@PathVariable("roomNo") Long roomNo,@PathVariable("rollNo") Long rollNo) {
		logger.debug("In Deleting Student details...");
		ResponseEntity<Response> responseBody=null;
		StudentEntity studentDetail = new StudentEntity();
		try {
			studentDetail=studentService.deleteStudent(roomNo,rollNo);
			responseBody = ResponseUtil.getResponse(200,"Student Details Deleted Successfully",studentDetail);
		}
		catch (ServiceException e) {
			responseBody = ResponseUtil.getResponse(500,e.getMessage(),studentDetail);
		} catch (NotFoundException e) {
			responseBody = ResponseUtil.getResponse(404,e.getMessage(),studentDetail);
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
