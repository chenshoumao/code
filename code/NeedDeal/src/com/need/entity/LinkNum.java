package com.need.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkNum {
	private String result;//”:”0”,
	private String timeStamp;//”:” 2013-07-19 15:25:00”
	private String errorDescription;//错误信息
	private List data = new ArrayList();
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	

}
