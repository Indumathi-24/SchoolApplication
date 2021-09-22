package com.school.controller;
import com.school.exception.ServiceException;
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
import com.school.entity.Subject;
import com.school.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

	@Autowired 
    private SubjectService subjectService;
	
	@PostMapping("/{roomNo}")
	public ResponseEntity<String> addSubject(@PathVariable Long roomNo,@RequestBody Subject subject) 
	{
		ResponseEntity<String> response=null;
		try {
			response = subjectService.addSubject(roomNo,subject);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@GetMapping("/{roomNo}")
	public ResponseEntity<?> getAllSubject(@PathVariable("roomNo") Long roomNo) 
	{
		ResponseEntity<?> response=null;
		try {
			response = subjectService.getAllSubject(roomNo);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	@GetMapping("/{roomNo}/{code}")
	public ResponseEntity<?> getParticularSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  
     {
		ResponseEntity<?> response=null;
		try {
			response = subjectService.getParticularSubject(roomNo,code);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	 }
	
	@PutMapping("/{roomNo}/{code}")
	public ResponseEntity<String> updateSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code,@RequestBody Subject subject) {
		ResponseEntity<String> response=null;
		try {
			response= subjectService.updateSubject(roomNo,code,subject);
		} catch (ServiceException e) {
			response = new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@DeleteMapping("/{roomNo}/{code}")
	public ResponseEntity<String> deleteSubject(@PathVariable("roomNo") Long roomNo,@PathVariable("code") String code)  {
		ResponseEntity<String> response=null;
		try {
			response= subjectService.deleteSubject(roomNo,code);
		} catch (ServiceException e) {
			response=new ResponseEntity<String>(e.getMessage(),new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
