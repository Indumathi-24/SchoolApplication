package com.school.dto;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherSubject {
     private Long id;
     private Teacher teacherDetail;
     private SubjectClass subjectClassDetail;
}
