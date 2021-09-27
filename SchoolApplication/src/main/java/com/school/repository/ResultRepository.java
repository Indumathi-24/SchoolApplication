package com.school.repository;
import java.util.List;

import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.DatabaseException;

public interface ResultRepository {
	Long addResult(Long rollNo,Result result) throws DatabaseException;
	ResultEntity getResult(Long rollNo) throws DatabaseException;
	ResultEntity updateResult(Long rollNo,Long resultId,Result result) throws DatabaseException;
	List<ResultEntity> getResultByClass(Long roomNo) throws DatabaseException;
}
