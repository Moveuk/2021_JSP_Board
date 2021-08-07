package com.ldu.exam.exam1.dto;

import java.util.Map;

import com.ldu.exam.exam1.util.Ut;

import lombok.Getter;

@Getter
public class ResultData {
	private String msg;
	private String resultCode;
	private Map<String, Object> body;
	
	private ResultData() {
		
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-1");
	}

	public boolean isFail() {
		return !isSuccess();
	}
	
	public static ResultData from(String resultCode, String msg, Object... bodyArgs) {
		ResultData rd = new ResultData();
		
		rd.msg = msg;
		rd.resultCode = resultCode;
		rd.body = Ut.mapOf(bodyArgs);
		
		
		return rd;
	}

}
