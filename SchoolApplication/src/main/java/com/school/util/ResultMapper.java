package com.school.util;

import com.school.dto.Result;
import com.school.entity.ClassEntity;
import com.school.entity.ResultEntity;
import com.school.entity.StudentEntity;

public class ResultMapper {
     public static ResultEntity mapResult(Result result,Long rollNo,Long roomNo)
     {
    	 ResultEntity resultEntity = new ResultEntity();
    	 StudentEntity studentEntity = new StudentEntity();
    	 ClassEntity classEntity = new ClassEntity();
    	 classEntity.setRoomNo(roomNo);
    	 studentEntity.setRollNo(rollNo);
    	 resultEntity.setTerm1(0l);
    	 resultEntity.setTerm2(0l);
    	 resultEntity.setTerm3(0l);
    	 resultEntity.setTerm1Status("-");
    	 resultEntity.setTerm2Status("-");
    	 resultEntity.setTerm3Status("-");
    	 resultEntity.setResult("-");
    	 resultEntity.setStudent(studentEntity);
    	 resultEntity.setClassEntity(classEntity);
    	 return resultEntity;
     }
}
