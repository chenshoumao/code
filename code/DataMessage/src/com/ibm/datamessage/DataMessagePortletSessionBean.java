package com.ibm.datamessage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
public class DataMessagePortletSessionBean {
	
	public String getFormText() {
		return formText;
	}
	public void setFormText(String formText) {
		this.formText = formText;
	}
	public String getPend() {
		return pend;
	}
	public void setPend(String pend) {
		this.pend = pend;
	}
	public String getHava() {
		return hava;
	}
	public void setHava(String hava) {
		this.hava = hava;
	}
	public String getMeeting() {
		return meeting;
	}
	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
	public List<Waiting> getListWaiting() {
		return listWaiting;
	}
	public void setListWaiting(List<Waiting> listWaiting) {
		this.listWaiting = listWaiting;
	}
	public List<Reading> getListReading() {
		return listReading;
	}
	public void setListReading(List<Reading> listReading) {
		this.listReading = listReading;
	}
	public List<WaitingFinish> getListWaitingFinish() {
		return listWaitingFinish;
	}
	public void setListWaitingFinish(List<WaitingFinish> listWaitingFinish) {
		this.listWaitingFinish = listWaitingFinish;
	}
	public List<ReadingFinish> getListReadFinish() {
		return listReadFinish;
	}
	public void setListReadFinish(List<ReadingFinish> listReadFinish) {
		this.listReadFinish = listReadFinish;
	}
	public List<Meeting> getListMeeting() {
		return listMeeting;
	}
	public void setListMeeting(List<Meeting> listMeeting) {
		this.listMeeting = listMeeting;
	}
	public Map<String, List<Link>> getMapLink() {
		return MapLink;
	}
	public void setMapLink(Map<String, List<Link>> mapLink) {
		MapLink = mapLink;
	}
	private String formText = "";
	//待办数量
	private String pend;
	//已办数量
	private String hava;
	//会议数量
	private String meeting;
	//待办列表
	private List<Waiting> listWaiting;
	//待阅列表
	private List<Reading> listReading;
	//已办列表
	private List<WaitingFinish> listWaitingFinish;
	//已阅列表
	private List<ReadingFinish> listReadFinish;
	//会议列表
	private List<Meeting> listMeeting;
	//链接对象
	private Map<String,List<Link>> MapLink = new LinkedHashMap<String,List<Link>>();
	

}
