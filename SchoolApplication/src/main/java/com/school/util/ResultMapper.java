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
    	 resultEntity.setTerm1(-1l);
    	 resultEntity.setTerm2(-1l);
    	 resultEntity.setTerm3(-1l);
    	 resultEntity.setTerm1Status("NE");
    	 resultEntity.setTerm2Status("NE");
    	 resultEntity.setTerm3Status("NE");
    	 resultEntity.setResult("NE");
    	 resultEntity.setStudent(studentEntity);
    	 resultEntity.setClassEntity(classEntity);
    	 return resultEntity;
     }
}
