package com.ibm.datamessage;
/**
 * 
 * ClassName: ReadingFinish 
 * @Description: ����
 * @author houhuayu
 * @date 2016��7��4��
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
	//��Դ��ϵͳ����
	private String from;
	//����
	private String title;
	//������
	private String originator;
	//ʱ��
	private String datatime;
	//�û�id
	private String  pendingid;
	//��ת��ַurl
	private String url;
		

}
