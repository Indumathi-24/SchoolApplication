package com.school.repository;

import com.school.dto.Mark;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface MarkRepository {
     Integer updateMark(String code,Long rollNo,Mark mark) throws DatabaseException, NotFoundException;
}
