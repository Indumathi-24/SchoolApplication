package com.school.service;

import com.school.dto.SubjectClass;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface SubjectClassService {
	
	Long assignSubjectClass(SubjectClass subjectClass) throws NotFoundException, ServiceException;
}
