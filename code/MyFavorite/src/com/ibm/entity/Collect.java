package com.ibm.entity;

/**
 * 
 * 功能描述：此类是描述收藏夹的一个实体类
 *
 * 类名：Collect
 *
 * Version info 版本号：V1.0 
 * © Copyright 续日科技 2016年7月8日 版权所有
 */
public class Collect {
	//收藏内容的图标
	private String image;
	//收藏内容的名称
	private String name;
	//收藏内容的链接
	private String url;
	//收藏内容的ID
	private String id;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Collect [image=" + image + ", name=" + name + ", url=" + url
				+ ", id=" + id + "]";
	}
	
}
