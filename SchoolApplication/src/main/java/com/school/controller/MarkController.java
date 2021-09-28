package com.school.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.Mark;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.service.MarkService;
import com.school.util.ResponseUtil;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
	
	@Autowired
	
	MarkService markService;

	@PutMapping("/{code}/{rollNo}")
	public ResponseEntity<Response> updateMark(@PathVariable("code") String code,@PathVariable("rollNo") Long rollNo,@Valid @RequestBody Mark mark){
		//logger.debug("In Updating Student details...");
		ResponseEntity<Response> response=null;
		int count = 0;
		try {
			count=markService.updateMark(code,rollNo,mark);
			response = ResponseUtil.getResponse(200,"Mark Details Updated Successfully",count);
		} catch (ServiceException e) {
			response = ResponseUtil.getResponse(500,e.getMessage(),count);
		} catch (NotFoundException e) {
			response = ResponseUtil.getResponse(404,e.getMessage(),count);
		}
		return response;
	}
}
