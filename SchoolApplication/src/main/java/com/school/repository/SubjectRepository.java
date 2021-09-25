package com.school.repository;

import java.util.List;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;

public interface SubjectRepository {

	String addSubject(Long roomNo,Subject subject) throws DatabaseException;
	List<SubjectEntity> getAllSubject(Long roomNo) throws DatabaseException;
	SubjectEntity getParticularSubject(Long roomNo,String code) throws DatabaseException, NotFoundException ;
	SubjectEntity updateSubject(Long roomNo,String code,Subject subject) throws  DatabaseException, NotFoundException;
	SubjectEntity deleteSubject(Long roomNo, String code) throws DatabaseException, NotFoundException;
}
