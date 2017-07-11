package com.solar.tech.bean;

import java.util.ArrayList;
import java.util.List;
 
import com.ibm.workplace.wcm.api.AuthoringTemplate;
import com.ibm.workplace.wcm.api.ContentPrototype;
import com.ibm.workplace.wcm.api.DocumentLibrary;

/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
/****
 * 会话bean
 * @author simon
 * @date 2016年6月29日
 *
 */
public class SolarNewsPortletSessionBean {
	
	/**
	 * Last text for the text form
	 */
	private String formText = "";
	private String selectLibraryName;//选中内容库的名称
	private String selectAuthTemplateName;//选中的模板名称
	private String selectWorkFlowId;//选中内容库的工作流程id
	private String selectAreaId;//选中模板对应的站点区域的id
	
	private ContentPrototype contentElement;//模板的字段对象
	private List<String> listComponent=new ArrayList<String>();//模板的字段名称集合
	private List<WebLibrary> listarea=new ArrayList<WebLibrary>();//拿到所有配置文件中的数据
	private String imagePath="";
	private DocumentLibrary currentLibrary=null;//当前存储库
	
	private boolean isUpload=false;
	private boolean saveed=false;//是否已经经常保存的操作过来的
	private String approvalPageId;//审核页面的id
	private String approvalId;//审核内容的Id
	private String browsePageId;//浏览页面的id
	private String browseId;//查看内容的id
	private String isApprovalPage="";//
	private String selectedSiteId="";
	private String selectedSitePath="";
	
	
	
	
	

	public DocumentLibrary getCurrentLibrary() {
		return currentLibrary;
	}

	public void setCurrentLibrary(DocumentLibrary currentLibrary) {
		this.currentLibrary = currentLibrary;
	}

	public String getSelectedSiteId() {
		return selectedSiteId;
	}

	public void setSelectedSiteId(String selectedSiteId) {
		this.selectedSiteId = selectedSiteId;
	}

	public String getSelectedSitePath() {
		return selectedSitePath;
	}

	public void setSelectedSitePath(String selectedSitePath) {
		this.selectedSitePath = selectedSitePath;
	}

	public String getIsApprovalPage() {
		return isApprovalPage;
	}

	public void setIsApprovalPage(String isApprovalPage) {
		this.isApprovalPage = isApprovalPage;
	}

	/**
	 * Set last text for the text form.
	 * 
	 * @param formText last text for the text form.
	 */
	public void setFormText(String formText) {
		this.formText = formText;
	}

	/**
	 * Get last text for the text form.
	 * 
	 * @return last text for the text form
	 */
	public String getFormText() {
		return this.formText;
	}

	public String getSelectLibraryName() {
		return selectLibraryName;
	}

	public void setSelectLibraryName(String selectLibraryName) {
		this.selectLibraryName = selectLibraryName;
	}

	public String getSelectAuthTemplateName() {
		return selectAuthTemplateName;
	}

	public void setSelectAuthTemplateName(String selectAuthTemplateName) {
		this.selectAuthTemplateName = selectAuthTemplateName;
	}

	public ContentPrototype getContentElement() {
		return contentElement;
	}

	public void setContentElement(ContentPrototype contentElement) {
		this.contentElement = contentElement;
	}

	public List<String> getListComponent() {
		return listComponent;
	}

	public void setListComponent(List<String> listComponent) {
		this.listComponent = listComponent;
	}

	public String getSelectWorkFlowId() {
		return selectWorkFlowId;
	}

	public void setSelectWorkFlowId(String selectWorkFlowId) {
		this.selectWorkFlowId = selectWorkFlowId;
	}

	public String getSelectAreaId() {
		return selectAreaId;
	}

	public void setSelectAreaId(String selectAreaId) {
		this.selectAreaId = selectAreaId;
	}

	public List<WebLibrary> getListarea() {
		return listarea;
	}

	public void setListarea(List<WebLibrary> listarea) {
		this.listarea = listarea;
	}

	public boolean isUpload() {
		return isUpload;
	}

	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isSaveed() {
		return saveed;
	}

	public void setSaveed(boolean saveed) {
		this.saveed = saveed;
	}

	public String getApprovalPageId() {
		return approvalPageId;
	}

	public void setApprovalPageId(String approvalPageId) {
		this.approvalPageId = approvalPageId;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getBrowsePageId() {
		return browsePageId;
	}

	public void setBrowsePageId(String browsePageId) {
		this.browsePageId = browsePageId;
	}

	public String getBrowseId() {
		return browseId;
	}

	public void setBrowseId(String browseId) {
		this.browseId = browseId;
	}
    
	
	
	 
    

	 
	

}
