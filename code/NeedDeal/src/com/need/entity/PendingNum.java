package com.need.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/****
 * ´ý°ìÊµÌå
 * @author admin
 * @date 2016-05-12
 */
public class PendingNum {
	private String appID;//":"X0001"," " +
	private String authenticator;//":"X0dfa0309tyi99dd11abckdddeetqe",
	private String errorDescription;//¡±:¡±¡±,
	private String result;//¡±:¡±0¡±,
	private String timeStamp;//¡±:¡± 2013-07-19 15:25:00¡±
    private List<PendDetail> data=new ArrayList<PendDetail>();
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAuthenticator() {
		return authenticator;
	}
	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	 
	public List<PendDetail> getData() {
		return data;
	}
	public void setData(List<PendDetail> data) {
		this.data = data;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
    
    

}
