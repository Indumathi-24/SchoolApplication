package com.school.util;

import com.school.entity.ClassEntity;
import com.school.dto.Class;

public class ClassMapper {
	public static ClassEntity mapClass(Class classDetail) 
	{
		ClassEntity classEntity = new ClassEntity();
		classEntity.setRoomNo(classDetail.getRoomNo());
		classEntity.setStandard(classDetail.getStandard());
		classEntity.setSection(classDetail.getSection());
		return classEntity;
	}
}
