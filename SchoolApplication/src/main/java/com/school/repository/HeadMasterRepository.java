package com.school.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.entity.HeadMaster;
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterNotFoundException;

public interface HeadMasterRepository {
	boolean checkHeadMaster(Long id) throws HeadMasterNotFoundException;
	HeadMaster addHeadMasterDetails(HeadMaster headMasterDeteails) throws DatabaseException;
	List<HeadMaster> getAllHeadMasterDetails() throws DatabaseException;
	HeadMaster updateHeadMasterDetails(Long id,HeadMaster headMasterDetails) throws DatabaseException;
	String deleteHeadMasterDetails(Long id) throws DatabaseException;
	HeadMaster getParticularHeadMasterDetails(Long id) throws DatabaseException;
}
