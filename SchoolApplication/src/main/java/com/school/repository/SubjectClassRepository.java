package com.school.repository;

import com.school.dto.SubjectClass;
import com.school.exception.DatabaseException;

public interface SubjectClassRepository {
     public Long assignSubjectClass(SubjectClass subjectClass) throws DatabaseException;
}
