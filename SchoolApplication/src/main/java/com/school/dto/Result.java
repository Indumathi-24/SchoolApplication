package com.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
	private Long resultId;
	private Long term1;
	private Long term2;
	private Long term3;
	private String term1Status;
	private String term2Status;
	private String term3Status;
	private String result;
	private Student studentDetail;
	private Class classDetail;
}
