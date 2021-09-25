package com.school.util;

import com.school.dto.HeadMaster;
import com.school.entity.HeadMasterEntity;

public class HeadMasterMapper {
    public static HeadMasterEntity mapHeadMaster(HeadMaster headMaster)
    {
    	HeadMasterEntity headMasterEntity = new HeadMasterEntity();
    	headMasterEntity.setId(headMaster.getId());
    	headMasterEntity.setName(headMaster.getName());
    	headMasterEntity.setGender(headMaster.getGender());
    	headMasterEntity.setQualification(headMaster.getQualification());
    	headMasterEntity.setDateOfBirth(headMaster.getDateOfBirth());
    	headMasterEntity.setEmail(headMaster.getEmail());
    	headMasterEntity.setAddress(headMaster.getAddress());
    	headMasterEntity.setContactNo(headMaster.getContactNo());
    	return headMasterEntity;
    }
}
