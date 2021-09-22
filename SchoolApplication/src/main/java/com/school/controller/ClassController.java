package com.school.controller;
import com.school.service.ClassService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.exception.ClassNotFoundException;
import com.school.exception.ServiceException;
import com.school.entity.Class;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/api/class")
@CrossOrigin("http://localhost:4200")
public class ClassController {
	  
	 static Logger logger = Logger.getLogger("ClassController.class");
	 @Autowired
	 private ClassService classService;
     
	 @PostMapping
     public ResponseEntity<Response> addClass(@RequestBody Class classEntity){
		logger.debug("In Add Class Details Method");
		ResponseEntity<Response> responseBody = null;
		Response response = new Response();
		Long roomNo=null;
		try {
			roomNo = classService.addClass(classEntity);
			response.setData(roomNo);
			response.setStatusText("Class Details Added");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			response.setStatusCode(404);
			response.setStatusText(e.getMessage());
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
		return responseBody;
	 }
	 @GetMapping
	 public ResponseEntity<Response> getAllClass()
	 {
		 logger.debug("In Get All Class Details Method");
		 ResponseEntity<Response> responseBody = null;
		 Response response = new Response();
		 List<Class> classList;
		try {
			classList = classService.getAllClass();
			response.setData(classList);
			response.setStatusText("All Class Details Retrieved");
			response.setStatusCode(200);
			responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			 response.setStatusCode(404);
			 response.setStatusText(e.getMessage());
			 responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
		 return responseBody;
	 }
	 
	 @GetMapping("/{roomNo}")
	 public ResponseEntity<Response> getParticularClass(@PathVariable("roomNo") Long roomNo){
		 logger.debug("In Get Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 Response response =new Response();
		 try {
			  Class classDetail=(Class) classService.getParticularClass(roomNo);
			 response.setData(classDetail);
			 response.setStatusText("Particular Class Details Retrieved");
			 response.setStatusCode(200);
			 responseBody = new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			 response.setStatusCode(404);
			 response.setStatusText(e.getMessage());
			 responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return responseBody; 
	 }
	 @PutMapping("/{roomNo}")
	 public ResponseEntity<Response> updateClass(@PathVariable("roomNo") Long roomNo,@RequestBody Class classEntity) throws ClassNotFoundException
	 {
		 logger.debug("In Update Class Details Method");
		 ResponseEntity<Response> responseBody=null;
		 Response response = new Response();
		 try {
			 	Class classDetail = classService.updateClass(roomNo,classEntity);
			 	response.setData(classDetail);
			 	response.setStatusText("Class Details Updated Successfully");
				response.setStatusCode(200);
			 	responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.OK);
			} 
		 catch (ServiceException e) 
		    {
			 response.setStatusCode(404);
			 response.setStatusText(e.getMessage());
			 responseBody = new ResponseEntity<Response>(response,new HttpHeaders(),HttpStatus.NOT_FOUND);
			}
	    return responseBody; 
	 }
}
