package com.school.service;

import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Class;
import java.util.List;
import com.school.entity.ClassEntity;

public interface ClassService {
	Long addClass(Class classDetail) throws ServiceException ;
	List<ClassEntity> getAllClass() throws ServiceException;
    ClassEntity getParticularClass(Long roomNo) throws ServiceException, NotFoundException;
    ClassEntity updateClass(Long roomNo,Class classDetail) throws ServiceException, NotFoundException;
}
