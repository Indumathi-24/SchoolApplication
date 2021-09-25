package com.school.util;

import com.school.dto.Parent;
import com.school.entity.ParentEntity;

public class ParentMapper {
      public static ParentEntity mapParent(Parent parent)
      {
    	  ParentEntity parentEntity = new ParentEntity();
    	  parentEntity.setId(parent.getId());
    	  parentEntity.setFatherName(parent.getFatherName());
    	  parentEntity.setMotherName(parent.getMotherName());
    	  parentEntity.setContactNo(parent.getContactNo());
    	  return parentEntity;  
      }
}
