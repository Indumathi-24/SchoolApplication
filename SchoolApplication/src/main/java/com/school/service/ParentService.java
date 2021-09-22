package com.school.service;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.school.entity.Parent;
import com.school.exception.ServiceException;


public interface ParentService {
	 Long addParent(Long rollNo, Parent parent) throws ServiceException;
	 List<Parent> getParent(Long rollNo) throws ServiceException;
	 //Parent getParent(Long id) throws ParentNotFoundException;
	 Parent updateParent(Long id,Parent parent)throws  ServiceException;
	 Parent deleteParent(Long id) throws ServiceException;
}
