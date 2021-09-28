package com.school.dto;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class Result {
	private Long resultId;
	private Long term1;
	private Long term2;
	private Long term3;
	private String result;
	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public Long getTerm1() {
		return term1;
	}

	public void setTerm1(Long term1) {
		this.term1 = term1;
	}

	public Long getTerm2() {
		return term2;
	}

	public void setTerm2(Long term2) {
		this.term2 = term2;
	}

	public Long getTerm3() {
		return term3;
	}

	public void setTerm3(Long term3) {
		this.term3 = term3;
	}

	public String getResult() {
//		if(term1+term2+term3 >=525)
//		{
//			result="PASS";
//		}
//		else
//		{
//			result="FAIL";
//		}
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
