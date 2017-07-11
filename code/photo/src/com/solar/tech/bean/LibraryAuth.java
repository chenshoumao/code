package com.solar.tech.bean;

import com.ibm.workplace.wcm.api.AuthoringTemplate;
import com.ibm.workplace.wcm.api.DocumentLibrary;

/***
 * 内容库与编写模板关系类
 * @author simon
 * @date 2016年6月28日
 *
 */
public class LibraryAuth {
    private DocumentLibrary webLibrary;//内容库
    private AuthoringTemplate authTemplate;//编写模板
	public DocumentLibrary getWebLibrary() {
		return webLibrary;
	}
	public void setWebLibrary(DocumentLibrary webLibrary) {
		this.webLibrary = webLibrary;
	}
	public AuthoringTemplate getAuthTemplate() {
		return authTemplate;
	}
	public void setAuthTemplate(AuthoringTemplate authTemplate) {
		this.authTemplate = authTemplate;
	}
	  
}
