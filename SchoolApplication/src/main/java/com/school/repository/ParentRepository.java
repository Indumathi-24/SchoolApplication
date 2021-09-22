package com.school.repository;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.school.entity.Parent;
import com.school.exception.DatabaseException;


public interface ParentRepository {
	 Long addParent(Long rollNo,Parent parent) throws  DatabaseException;
	 List<Parent> getParent(Long rollNo) throws  DatabaseException;
	 //Parent getParent(Long id) throws ParentNotFoundException;
	 Parent updateParent(Long id,Parent parent) throws DatabaseException;
	 Parent deleteParent(Long id) throws DatabaseException;

}
