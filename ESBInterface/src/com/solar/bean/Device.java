package com.solar.bean;

import java.util.Date;

public class Device {
	private String deviceid;//主键
	private String devicetype;//设备类型
	private String dev_name;//设备名称
	private String dev_date;//定位时间
	private double dev_speed;//速度公里/小时
	private int dev_angle;//方向 0-360
	private double dev_x;//经度
	private double dev_y;//纬度
	private double dev_h;//高程
	private String dev_desc;//描述信息
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getDev_date() {
		return dev_date;
	}
	public void setDev_date(String dev_date) {
		this.dev_date = dev_date;
	}
	public double getDev_speed() {
		return dev_speed;
	}
	public void setDev_speed(double dev_speed) {
		this.dev_speed = dev_speed;
	}
	public int getDev_angle() {
		return dev_angle;
	}
	public void setDev_angle(int dev_angle) {
		this.dev_angle = dev_angle;
	}
	public double getDev_x() {
		return dev_x;
	}
	public void setDev_x(double dev_x) {
		this.dev_x = dev_x;
	}
	public double getDev_y() {
		return dev_y;
	}
	public void setDev_y(double dev_y) {
		this.dev_y = dev_y;
	}
	public double getDev_h() {
		return dev_h;
	}
	public void setDev_h(double dev_h) {
		this.dev_h = dev_h;
	}
	public String getDev_desc() {
		return dev_desc;
	}
	public void setDev_desc(String dev_desc) {
		this.dev_desc = dev_desc;
	}
	
	
}
