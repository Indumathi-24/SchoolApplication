package com.school.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mark {
     private Long markId;
     @Max(100)
     private Long tamil;
     @Max(100)
     private Long english;
     @Max(100)
     private Long maths;
     @Max(100)
     private Long science;
     @Max(100)
     private Long socialScience;
     private String termType;
     private String result;
     private Student studentDetail;
}
