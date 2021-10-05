package com.school.repository;
import java.util.List;
import com.school.dto.Class;
import com.school.exception.ClassAlreadyExistException;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.entity.ClassEntity;

public interface ClassRepository {
	
	void checkClassRoomNo(Long rno) throws ClassNotFoundException;
	Long addClass(Class classDetail) throws DatabaseException, NotFoundException;
	List<ClassEntity> getAllClass() throws DatabaseException;
	ClassEntity getParticularClass(Long roomNo) throws DatabaseException, NotFoundException;
	ClassEntity updateClass(Long roomNo,Class classDetail) throws DatabaseException, NotFoundException;
	Long getRoomNo(String standard,String section) throws DatabaseException;
}
