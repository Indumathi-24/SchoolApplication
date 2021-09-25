package com.school.repository;
import java.util.List;
import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;
import com.school.exception.DatabaseException;
import com.school.exception.HeadMasterNotFoundException;
import com.school.exception.NotFoundException;

public interface HeadMasterRepository {
	void checkHeadMaster(Long id) throws HeadMasterNotFoundException;
	Long addHeadMasterDetails(HeadMaster headMasterDeteails) throws DatabaseException;
	List<HeadMasterEntity> getAllHeadMasterDetails() throws DatabaseException;
	HeadMasterEntity updateHeadMasterDetails(Long id,HeadMaster headMasterDetails) throws DatabaseException, NotFoundException;
	HeadMasterEntity deleteHeadMasterDetails(Long id) throws DatabaseException, NotFoundException;
	HeadMasterEntity getParticularHeadMasterDetails(Long id) throws DatabaseException, NotFoundException;
}
