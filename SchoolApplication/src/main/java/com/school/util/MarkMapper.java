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
    	 markEntity.setTamil(mark.getTamil());
    	 markEntity.setEnglish(mark.getEnglish());
    	 markEntity.setMaths(mark.getMaths());
    	 markEntity.setScience(mark.getScience());
    	 markEntity.setSocialScience(mark.getSocialScience());
    	 markEntity.setTermType(mark.getTermType());
    	 markEntity.setStudentEntity(studentEntity);
    	 return markEntity;
     }
}
