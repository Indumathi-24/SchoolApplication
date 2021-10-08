package com.school.util;

import com.school.dto.Mark;
import com.school.entity.MarkEntity;
import com.school.entity.StudentEntity;

public class MarkMapper {
     public static MarkEntity mapMark(Long rollNo,Mark mark)
     {
    	 MarkEntity markEntity = new MarkEntity();
    	 StudentEntity studentEntity = new StudentEntity();
    	 studentEntity.setRollNo(rollNo);
    	 markEntity.setTamil(-1l);
    	 markEntity.setEnglish(-1l);
    	 markEntity.setMaths(-1l);
    	 markEntity.setScience(-1l);
    	 markEntity.setSocialScience(-1l);
    	 markEntity.setResult("NE");
    	 markEntity.setTermType(mark.getTermType());
    	 markEntity.setStudentEntity(studentEntity);
    	 return markEntity;
     }
}
