package com.school.repository;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.entity.Class;

public interface ClassRepository {
	
	void checkClassRoomNo(Long rno) throws ClassNotFoundException;
	Long addClass(Class classEntity) throws DatabaseException ;
	List<Class> getAllClass() throws DatabaseException;
	Class getParticularClass(Long roomNo) throws DatabaseException;
	Class updateClass(Long roomNo,Class classEntity) throws DatabaseException;
}
