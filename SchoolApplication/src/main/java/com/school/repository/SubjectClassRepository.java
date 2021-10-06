package com.school.repository;

import java.util.List;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface SubjectClassRepository {
    Long assignSubjectClass(SubjectClass subjectClass) throws DatabaseException;
    List<String> viewSubjectClass(Long roomNo) throws NotFoundException, DatabaseException;
    Long  getSubjectClassAssignId(String code,Long roomNo) throws DatabaseException;
    Long getRoomNoForAssignId(Long id) throws DatabaseException, NotFoundException;
    String getSubjectCode(Long roomNo,Long id) throws NotFoundException, DatabaseException;
    List<Long> getRoomNoList(List<Long> assignIdList) throws DatabaseException;
    List<String> getSubjectCodeList(Long roomNo,List<Long> assignIdList) throws DatabaseException;
}
