package com.school.service;

import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Class;
import java.util.List;
import com.school.entity.ClassEntity;

public interface ClassService {
	Long addClass(Class classDetail) throws ServiceException, NotFoundException ;
	List<ClassEntity> getAllClass() throws ServiceException;
    ClassEntity getParticularClass(Long roomNo) throws ServiceException, NotFoundException;
    ClassEntity updateClass(Long roomNo,Class classDetail) throws ServiceException, NotFoundException;
    Long getRoomNo(String standard,String section) throws ServiceException;
}
