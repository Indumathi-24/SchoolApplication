package com.school.service;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface MarkService {
     Integer updateMark(String code,Long rollNo,Mark mark) throws ServiceException, NotFoundException;
}
