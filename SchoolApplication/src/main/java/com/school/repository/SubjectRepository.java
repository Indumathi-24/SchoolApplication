package com.school.repository;

import java.util.List;

import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.SubjectNotFoundException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;

public interface SubjectRepository {

	void checkSubjectCode(String code) throws SubjectNotFoundException;
	String addSubject(Subject subject) throws DatabaseException;
	List<SubjectEntity> getAllSubject() throws DatabaseException;
	SubjectEntity getParticularSubject(String code) throws DatabaseException, NotFoundException ;
	SubjectEntity updateSubject(String code,Subject subject) throws  DatabaseException, NotFoundException;
	SubjectEntity deleteSubject( String code) throws DatabaseException, NotFoundException;
	List<SubjectEntity> getSubjects(List<String> subjectCodeList) throws DatabaseException;
}
