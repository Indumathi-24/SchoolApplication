package com.school.repository;

import java.util.List;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface SubjectClassRepository {
    Long assignSubjectClass(SubjectClass subjectClass) throws DatabaseException;
    List<SubjectClassEntity> viewSubjectClass(Long roomNo) throws NotFoundException, DatabaseException;
    Long  getSubjectClassAssignId(String code,Long roomNo) throws DatabaseException;
    String getSubjectClassAssignDetails(Long id) throws DatabaseException, NotFoundException;
}
