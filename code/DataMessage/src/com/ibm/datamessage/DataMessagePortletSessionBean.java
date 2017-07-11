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
	//��������
	private String pend;
	//�Ѱ�����
	private String hava;
	//��������
	private String meeting;
	//�����б�
	private List<Waiting> listWaiting;
	//�����б�
	private List<Reading> listReading;
	//�Ѱ��б�
	private List<WaitingFinish> listWaitingFinish;
	//�����б�
	private List<ReadingFinish> listReadFinish;
	//�����б�
	private List<Meeting> listMeeting;
	//���Ӷ���
	private Map<String,List<Link>> MapLink = new LinkedHashMap<String,List<Link>>();
	

}
