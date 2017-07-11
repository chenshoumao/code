package com.ibm.contentpublishportlet;

/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
public class ContentPublishPortletSessionBean {
	
	/**
	 * Last text for the text form
	 */
	private String formText = "";
	private String stage = "";

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
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

}
