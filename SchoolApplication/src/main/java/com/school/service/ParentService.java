package com.school.service;

import java.util.List;
import com.school.dto.Parent;
import com.school.entity.ParentEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface ParentService {
	 Long addParent(Long rollNo, Parent parent) throws ServiceException, NotFoundException;
	 List<ParentEntity> getParent(Long rollNo) throws ServiceException, NotFoundException;
	 //Parent getParent(Long id) throws ParentNotFoundException;
	 ParentEntity updateParent(Long id,Parent parent)throws  ServiceException, NotFoundException;
	 ParentEntity deleteParent(Long id) throws ServiceException, NotFoundException;
}
