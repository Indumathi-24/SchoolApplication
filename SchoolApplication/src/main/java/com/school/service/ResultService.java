package com.school.service;
import com.school.entity.Result;
import com.school.exception.ServiceException;

public interface ResultService {
	Long addResult(Long rollNo,Result result) throws ServiceException;
	Result getResult(Long rollNo) throws ServiceException;
	Result updateResult(Long rollNo,Long resultId,Result result) throws ServiceException;
}
