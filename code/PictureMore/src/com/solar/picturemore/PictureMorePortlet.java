package com.solar.picturemore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * A sample portlet based on GenericPortlet
 */
public class PictureMorePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_PictureMore/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "PictureMorePortletView";         // JSP file name to be rendered on the view mode
	public static final String SESSION_BEAN  = "PictureMorePortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "PictureMorePortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "PictureMorePortletFormText";     // Parameter name for the text input



	 
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
		PictureMorePortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		String name = request.getUserPrincipal().getName();
		response.setContentType(request.getResponseContentType());
		ResourceBundle resource = ResourceBundle.getBundle("admin");
		String admin = resource.getString("admin");
		List<String> list = new ArrayList<String>();
//		JSONObject jsonObj = JSONObject.fromObject(admin);
		JSONObject json2= JSONObject.fromObject(admin);
		JSONArray ja = json2.getJSONArray("portal1");
		JSONArray ja2 = json2.getJSONArray("portal2");
		for(int i=0;i<ja.size();i++){
			String p = ja.getJSONObject(i).getString("name");
			list.add(p);
		}
		for(int i=0;i<ja2.size();i++){
			String p = ja2.getJSONObject(i).getString("name");
			list.add(p);
		}
		for(String str:list){
			if(str.contains(name)){
				sessionBean.setAdmin("1");
			}
		}
		if(!sessionBean.getAdmin().equals("1")){
			sessionBean.setAdmin("0");
		}
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
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
			PictureMorePortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return PictureMorePortletSessionBean
	 */
	private static PictureMorePortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		PictureMorePortletSessionBean sessionBean = (PictureMorePortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new PictureMorePortletSessionBean();
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
