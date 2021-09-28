package com.school.service;
import java.util.List;

import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface ResultService {
	Long addResult(Long roomNo,Long rollNo,Result result) throws ServiceException, NotFoundException;
	ResultEntity getResult(Long roomNo,Long rollNo) throws ServiceException, NotFoundException;
	ResultEntity updateResult(Long roomNo,Long rollNo,Long resultId,Result result) throws ServiceException, NotFoundException;
	List<ResultEntity> getResultByClass(Long roomNo) throws ServiceException, NotFoundException;
}
