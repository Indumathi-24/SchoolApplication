package com.school.repository;
import com.school.entity.Result;
import com.school.exception.DatabaseException;

public interface ResultRepository {
	Long addResult(Long rollNo,Result result) throws DatabaseException;
	Result getResult(Long rollNo) throws DatabaseException;
	Result updateResult(Long rollNo,Long resultId,Result result) throws DatabaseException;
}
