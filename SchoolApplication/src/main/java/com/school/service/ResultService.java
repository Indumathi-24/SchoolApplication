package com.school.service;
import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface ResultService {
	Long addResult(Long rollNo,Result result) throws ServiceException, NotFoundException;
	ResultEntity getResult(Long rollNo) throws ServiceException, NotFoundException;
	ResultEntity updateResult(Long rollNo,Long resultId,Result result) throws ServiceException, NotFoundException;
}
