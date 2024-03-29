package com.school.repository;
import java.util.List;

import com.school.dto.Result;
import com.school.entity.ResultEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;

public interface ResultRepository {
	Long addResult(Long roomNo,Long rollNo,Result result) throws DatabaseException;
	ResultEntity getResult(Long roomNo,Long rollNo) throws DatabaseException;
	ResultEntity updateResult(Long roomNo,Long rollNo,Long resultId,Result result) throws DatabaseException;
	List<ResultEntity> getResultByClass(Long roomNo) throws DatabaseException, NotFoundException;
	Integer updateTermMarks(String termType,Long totalMarks,String termStatus,Long rollNo) throws DatabaseException;
}
