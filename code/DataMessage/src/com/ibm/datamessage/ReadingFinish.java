package com.ibm.datamessage;
/**
 * 
 * ClassName: ReadingFinish 
 * @Description: 已阅
 * @author houhuayu
 * @date 2016年7月4日
 */
public class ReadingFinish {
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginator() {
		return originator;
	}
	public void setOriginator(String originator) {
		this.originator = originator;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public String getPendingid() {
		return pendingid;
	}
	public void setPendingid(String pendingid) {
		this.pendingid = pendingid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	//来源（系统名）
	private String from;
	//标题
	private String title;
	//发起人
	private String originator;
	//时间
	private String datatime;
	//用户id
	private String  pendingid;
	//跳转地址url
	private String url;
		

}
