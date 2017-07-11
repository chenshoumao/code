package com.ibm.contentpublishportlet;

import java.io.*;
import javax.portlet.*;

/**
 * A sample portlet based on GenericPortlet
 */
public class ContentPublishPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_ContentPublishPortlet/jsp/";    // JSP folder name
	public static String MySelfJspName = null;
	 
	public static final String VIEW_JSP      = "ContentPublishPortletView";         // JSP file name to be rendered on the view mode
	public static final String SESSION_BEAN  = "ContentPublishPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "ContentPublishPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "contentName";     // Parameter name for the text input
	public static final String FOLDER_ID     = "folderId";     // 图片文件夹，即相册的id
	public static final String STAGE     = "stage";     // 图片状态


	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		ContentPublishPortletSessionBean sessionBean = getSessionBean(request);
		if(sessionBean == null){ 
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		PortletRequestDispatcher rd = null;
		System.out.println(MySelfJspName == null);
		if(MySelfJspName == null){ 
			rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		}else {
			rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, MySelfJspName));
		} 
		MySelfJspName = null; 
		// Invoke the JSP to render 
		rd.include(request,response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
			ContentPublishPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null ){
				String formName = request.getParameter("formId"); 
				String contentName = request.getParameter(FORM_TEXT);
				String folderId = request.getParameter(FOLDER_ID);
				String stage = request.getParameter(STAGE);
				System.out.println(contentName + ",  " + folderId);
				if(contentName != null && !contentName.equals("")){
					//保存内容项的名字
					sessionBean.setFormText(contentName);
				}else if(folderId != null && !folderId.equals("")){
					//保存图片的id
					sessionBean.setFormText(folderId);
					System.out.println("folderId " + folderId);
				}  
				//保存图片状态
					sessionBean.setStage(stage);
					System.out.println("stage " + stage);
				//获取页面名字的信息
				MySelfJspName = request.getParameter("pageInfo");  		
				System.out.println("MySelfJspName " + MySelfJspName);
			}
		}
		
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return ContentPublishPortletSessionBean
	 */
	private static ContentPublishPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		ContentPublishPortletSessionBean sessionBean = (ContentPublishPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new ContentPublishPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Process a serve Resource request.
	 * 
	 * @see javax.portlet.Portlet#serveResource(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
	 */
	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, java.io.IOException {
		String resourceID = request.getResourceID();
		if (resourceID.equals("resourceID")) {
			// Insert code for serving the resource 
		}
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

}
