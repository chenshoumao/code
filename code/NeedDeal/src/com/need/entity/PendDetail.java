package com.need.entity;

import java.util.Date;
/***
 * ����������ϸ��ʵ��
 * @author admin
 * @date 2016-05-12
 */
public class PendDetail {
	private String pendingid;//��,��9999iiikkkllnjhfasdfandnfdnnn3��,
	private String title;//��,������2014��Ԫ���ż�֪ͨ��,
	private String url;//��,��http://XXX.XXX.XXXX/XXX.html��,
	private String module;//��,��֪ͨ���桱,
	private String receiveTime;//��,�� 2013-12-19 15:25:00��,
	private String currNode;//��,��������,
	public String getPendingid() {
		return pendingid;
	}
	public void setPendingid(String pendingid) {
		this.pendingid = pendingid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
 
	public String getCurrNode() {
		return currNode;
	}
	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	

}
