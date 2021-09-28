package com.school.controller;

import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.TeacherSubject;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.TeacherSubjectService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/teacherSubject")
public class TeacherSubjectController {

	static Logger logger = Logger.getLogger("TeacherSubjectController.class");
	@Autowired
	private TeacherSubjectService teacherSubjectServiceImpl;
	@PostMapping
	public ResponseEntity<Response> assignTeacherSubject(@RequestBody TeacherSubject teacherSubjectDetails) 
	{
		logger.debug("In Adding Teacher Subject details...");
		ResponseEntity<Response> response = null;
		Long id = 0l;
		try {
			 id = teacherSubjectServiceImpl.assignTeacherSubject(teacherSubjectDetails);
			 response = ResponseUtil.getResponse(200,"Teacher is Assigned to Subject Successfully", id);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),id);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),id);
		}
		return response;
	}
	@PutMapping("/{id}/{code}")
	public ResponseEntity<Response> updateTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode, @RequestBody TeacherSubject teacherSubjectDetails) 
	{
		logger.debug("In Updating Teacher Subject details...");
		ResponseEntity<Response> response = null;

		Integer count = null;
		try {
			count = teacherSubjectServiceImpl.updateTeacherSubjectAssign(teacherId,subjectCode,teacherSubjectDetails);
			response =  ResponseUtil.getResponse(200,"Teacher Subject Details Updated Successfully",count);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),count);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),count);
		}
		return response;
	}
	@DeleteMapping("/{id}/{code}")
	public ResponseEntity<Response> deleteTeacherSubjectAssign(@PathVariable("id") Long teacherId,@PathVariable("code") String subjectCode)
	{
		logger.debug("In Deleting Teacher Subject details...");
		ResponseEntity<Response> response = null;
		Integer count = null;
		try {
			count = teacherSubjectServiceImpl.deleteTeacherSubjectAssign(teacherId,subjectCode);
			response = ResponseUtil.getResponse(200,"Teacher Subject Details Deleted Successfully",count);
		} catch (ServiceException  e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),count);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),count);
		}
		return response;
	}
}
