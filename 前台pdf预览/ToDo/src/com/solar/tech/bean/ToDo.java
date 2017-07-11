package com.solar.tech.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDo implements Comparable{
	
	 String enName;
	private String cnName;	
	private String title;
	private String receiveTime;	
	private String pendingName;
	private String todoUrl;
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getPendingName() {
		return pendingName;
	}
	public void setPendingName(String pendingName) {
		this.pendingName = pendingName;
	}
	public String getTodoUrl() {
		return todoUrl;
	}
	public void setTodoUrl(String todoUrl) {
		this.todoUrl = todoUrl;
	}
	
	public static void main(String[] args) throws ParseException {
		String data1 = "2016-08-02 16:01:59";
		String data2 = "2016-08-03 16:01:59";
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(data1);
		Date date2 = sdf.parse(data2);
		   if (date.getTime() > date2.getTime()) {
			   System.out.println(1);
		   }
		   else
			   System.out.println(0);
			   
			   
	 
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub 
		ToDo todo = (ToDo)o; 
	    String receiveTime = todo.getReceiveTime();
	    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
			Date date = sdf.parse(receiveTime);
			Date date2 = sdf.parse(this.receiveTime);
			   if (date.getTime() > date2.getTime()) {
				   return 1;
			   }
			   else if (date.getTime() == date2.getTime()) {
				   return 0;
			   }
			   else
				   return -1;
				   
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	 //   System.out.println(this.receiveTime.compareTo(receiveTime));
	   // return this.receiveTime.compareTo(receiveTime);
	}
	
	 
}
