package com.ibm.myfavorite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.*;

import net.sf.json.JSONArray;

import com.ibm.entity.Collect;
import com.ibm.utils.JSONFile;

/**
 * A sample portlet based on GenericPortlet
 */
public class MyfavoritePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_Myfavorite/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "MyfavoritePortletView";         // JSP file name to be rendered on the view mode
	public static final String SESSION_BEAN  = "MyfavoritePortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "MyfavoritePortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "MyfavoritePortletFormText";     // Parameter name for the text input
	public static String FAVORITE_PATH;


	 
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
//		// Set the MIME type for the render response
//		response.setContentType(request.getResponseContentType());
//
//		// Check if portlet session exists
//		MyfavoritePortletSessionBean sessionBean = getSessionBean(request);
//		if( sessionBean==null ) {
//			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
//			return;
//		}
//
//		// Invoke the JSP to render
		
		response.setContentType(request.getResponseContentType());
		ResourceBundle resource = ResourceBundle.getBundle("Path");
		String basePath = resource.getString("path");
//		String basePath = "//opt//IBM//WebSphere//wp_profile//installedApps//portal01Cell//PA_MyFavorite.ear//MyFavorite.war//json//";
		FAVORITE_PATH = basePath+request.getUserPrincipal().getName()+".json";
		System.out.println("收藏夹路径是^^^^^^^^^^^^^^^^^^^^^^^…………………………………………………………………………………………………………………………"+FAVORITE_PATH);
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
//		String FAVORITE_PATH = request.getContextPath()+"/json/data.json";
//		String FAVORITE_PATH = "E:\\worksProtal\\Myfavorite\\WebContent\\json";
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
		}
		//鍦╨ist涓坊鍔犳柊鍏冪礌骞舵妸list鍐欏叆json鏂囦欢涓�
		if(request.getParameter("requestType").equals("add")){
			Collect newCollect = new Collect();
			newCollect.setImage(request.getParameter("image"));
			newCollect.setName(request.getParameter("name"));
			newCollect.setUrl(request.getParameter("url"));
			newCollect.setId(JSONFile.generateID());
			System.out.println("***********************************************");
			System.out.println(FAVORITE_PATH+"路径如下…………………………………………………………^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			String content = JSONFile.readFile(FAVORITE_PATH);
			List<Collect> listNow = new ArrayList<Collect>();
			if(content!=null&&content!=""){
				listNow = JSONFile.getJavaCollection(new Collect(),content);
			}
			listNow.add(newCollect);
			JSONArray jsonNow = JSONArray.fromObject(listNow);
			JSONFile.writeFile(FAVORITE_PATH, jsonNow.toString());
		}
		//鍦╨ist涓垹闄ゆ寚瀹氬厓绱犲苟鎶妉ist鍐欏叆json鏂囦欢涓�
		if(request.getParameter("requestType").equals("remove")){
			String[] collectID = request.getParameterValues("collect");
			String content = JSONFile.readFile(FAVORITE_PATH);
			List<Collect> listNow = JSONFile.getJavaCollection(new Collect(),content);
			for(int i=0;i<listNow.size();i++){
				for(int k=0;k<collectID.length;k++){
					String id = listNow.get(i).getId();
					if(id.equals(collectID[k])){
						listNow.remove(i);
					}
				}
			}
			JSONArray jsonNow = JSONArray.fromObject(listNow);
			JSONFile.writeFile(FAVORITE_PATH, jsonNow.toString());
		}
		//鍦╨ist涓慨鏀规寚瀹氬厓绱犲苟鎶妉ist鍐欏叆json鏂囦欢涓�
		if(request.getParameter("requestType").equals("update")){
			String id = request.getParameter("id");
			String content = JSONFile.readFile(FAVORITE_PATH);
			List<Collect> listNow = JSONFile.getJavaCollection(new Collect(),content);
			for (Collect collect : listNow) {
				if(collect.getId().equals(id)){
//					collect.setImage(request.getParameter("image"));
					collect.setName(request.getParameter("name"));
					collect.setUrl(request.getParameter("url"));
				}
			}
			JSONArray jsonNow = JSONArray.fromObject(listNow);
			JSONFile.writeFile(FAVORITE_PATH, jsonNow.toString());
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return MyfavoritePortletSessionBean
	 */
	private static MyfavoritePortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		MyfavoritePortletSessionBean sessionBean = (MyfavoritePortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new MyfavoritePortletSessionBean();
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
