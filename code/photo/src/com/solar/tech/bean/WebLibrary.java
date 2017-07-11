package com.solar.tech.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Web内容库实体
 * @author simon
 * @date 2016年6月27日
 */
public class WebLibrary {
	private String unqiueName;//唯一名称
	private String name;//名称
	private String describe;//描述
	private String workFlowId;//工作流程ID
	private List<AuthArea> authAreaList=new ArrayList<AuthArea>();//所有可以发布内容的配置
	private Map<String,String> mapstr=new HashMap<String,String>();
	public String getUnqiueName() {
		return unqiueName;
	}
	public void setUnqiueName(String unqiueName) {
		this.unqiueName = unqiueName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public List<AuthArea> getAuthAreaList() {
		return authAreaList;
	}
	public void setAuthAreaList(List<AuthArea> authAreaList) {
		this.authAreaList = authAreaList;
	}
	
	
	

}
