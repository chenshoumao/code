package com.solar.tech.bean;
/***
 * 栏目树形结构的实体
 * @author simon
 * @date 2016年7月25日
 */
public class AreaEntity {
	private String id;//节点id
	private String pid;//父节点id
	private String name;//名称
	private String libname;//web内容库的名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLibname() {
		return libname;
	}
	public void setLibname(String libname) {
		this.libname = libname;
	}
	
	
}
