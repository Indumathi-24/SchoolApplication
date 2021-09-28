package com.school.repository;

import java.util.List;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface MarkRepository {
	 Long addMark(Long rollNo,Mark mark) throws DatabaseException;
     Integer updateMark(String code,Long rollNo,Mark mark) throws DatabaseException, NotFoundException;
     List<MarkEntity> getMarks(String code,Long rollNo) throws DatabaseException, NotFoundException;
     List<MarkEntity> getAllTermMarks(Long rollNo) throws DatabaseException;
}
