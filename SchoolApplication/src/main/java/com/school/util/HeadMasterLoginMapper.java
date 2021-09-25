package com.school.util;

import com.school.dto.HeadMasterLogin;
import com.school.entity.HeadMasterEntity;
import com.school.entity.HeadMasterLoginEntity;

public class HeadMasterLoginMapper {
      public static HeadMasterLoginEntity mapHeadMasterLogin(HeadMasterLogin headMasterLogin,Long id)
      {
    	  HeadMasterLoginEntity headMasterLoginEntity = new HeadMasterLoginEntity();
    	  HeadMasterEntity headMasterEntity = new HeadMasterEntity();
    	  headMasterEntity.setId(id);
    	  headMasterLoginEntity.setPassword(headMasterLogin.getPassword());
    	  headMasterLoginEntity.setHeadMaster(headMasterEntity);
    	  return headMasterLoginEntity;
      }
}
