package com.need.entity;

import java.util.Date;
/***
 * 待办内容详细的实体
 * @author admin
 * @date 2016-05-12
 */
public class PendDetail {
	private String pendingid;//”,”9999iiikkkllnjhfasdfandnfdnnn3”,
	private String title;//”,”关于2014年元旦放假通知”,
	private String url;//”,”http://XXX.XXX.XXXX/XXX.html”,
	private String module;//”,”通知公告”,
	private String receiveTime;//”,” 2013-12-19 15:25:00”,
	private String currNode;//”,”发布”,
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
