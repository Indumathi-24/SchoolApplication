package com.school.repository;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.dto.Parent;
import com.school.entity.ParentEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;


public interface ParentRepository {
	 Long addParent(Long rollNo,Parent parent) throws  DatabaseException;
	 List<ParentEntity> getParent(Long rollNo) throws  DatabaseException;
	 //Parent getParent(Long id) throws ParentNotFoundException;
	 ParentEntity updateParent(Long id,Parent parent) throws DatabaseException, NotFoundException;
	 ParentEntity deleteParent(Long id) throws DatabaseException, NotFoundException;

}
