package com.school.controller;

import lombok.Data;

@Data
public class Response {
	private Integer statusCode;
	private String statusText;
	private Object data;
}
