package com.school.service;

import com.school.exception.ServiceException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.school.entity.Class;

public interface ClassService {
	Long addClass(Class classEntity) throws ServiceException ;
	List<Class> getAllClass() throws ServiceException;
    Object getParticularClass(Long roomNo) throws ServiceException;
    Class updateClass(Long roomNo,Class classEntity) throws ServiceException;
}
