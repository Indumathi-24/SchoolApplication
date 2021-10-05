package com.school.service;

import java.util.List;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface MarkService {
	 Long addMark(Long rollNo,Mark mark) throws ServiceException, NotFoundException;
     Integer updateMark(String code,Long rollNo,Mark mark) throws ServiceException, NotFoundException;
     List<MarkEntity> getMarks(Long rollNo) throws ServiceException, NotFoundException;
     List<MarkEntity> getAllMarks() throws ServiceException;
     List<MarkEntity> getAllTermMarks(Long rollNo) throws ServiceException, NotFoundException;
     List<MarkEntity> getAllStudentMarks(Long roomNo) throws ServiceException, NotFoundException;
}
