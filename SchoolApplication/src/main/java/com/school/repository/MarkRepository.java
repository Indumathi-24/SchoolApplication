package com.school.repository;

import java.util.List;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface MarkRepository {
	 Long addMark(Long rollNo,Mark mark) throws DatabaseException;
     Integer updateMark(String code,Long rollNo,Mark mark) throws DatabaseException, NotFoundException;
     List<MarkEntity> getMarks(Long rollNo) throws DatabaseException, NotFoundException;
     List<MarkEntity> getAllTermMarks(Long rollNo) throws DatabaseException;
     List<MarkEntity> getAllMarks() throws DatabaseException;
}
