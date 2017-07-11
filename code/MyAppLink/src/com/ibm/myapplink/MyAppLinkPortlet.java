package com.ibm.myapplink;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.portlet.*;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ibm.entity.AppLink;
import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.um.exceptions.PumaAttributeException;
import com.ibm.portal.um.exceptions.PumaException;
import com.ibm.portal.um.exceptions.PumaMissingAccessRightsException;
import com.ibm.portal.um.exceptions.PumaModelException;
import com.ibm.portal.um.exceptions.PumaSystemException;
import com.ibm.portal.um.portletservice.PumaHome;
import com.ibm.portal.um.PumaProfile;
import com.ibm.portal.um.User;
import com.ibm.utils.JSONFile;

/**
 * A sample portlet based on GenericPortlet
 */
public class MyAppLinkPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_MyAppLink/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "MyAppLinkPortletView";         // JSP file name to be rendered on the view mode
	public static final String CONFIG_JSP    = "MyAppLinkPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String SESSION_BEAN  = "MyAppLinkPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "MyAppLinkPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "MyAppLinkPortletFormText";     // Parameter name for the text input
	public static final String CONFIG_SUBMIT = "MyAppLinkPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "MyAppLinkPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".MyAppLinkPortletConfigKey";   // Key for the portlet preferences
    
	public static String APPLINK_PATH;
	public static String WebServiceURL="";
	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");


	private PumaHome pumaHome =null;

	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
		ResourceBundle resource = ResourceBundle.getBundle("Path");
		String basePath = resource.getString("path");
		WebServiceURL=resource.getString("webServiceURL");
		APPLINK_PATH = basePath+"AppLink.json";
		 PortletServiceHome portletServiceHome =null;
		  try {
			   Context context = new InitialContext();
			   portletServiceHome = (PortletServiceHome)
			   context.lookup("portletservice/com.ibm.portal.um.portletservice.PumaHome");
			   if(portletServiceHome != null){
				   pumaHome =(PumaHome)portletServiceHome.getPortletService(PumaHome.class);
			   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
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
		//取得当前用户名称
		 String name = request.getUserPrincipal().getName();
		 String isopen ="";
		 String service="";
		//请求接口数据
		try{
			StringBuffer sb = new StringBuffer();
			String url = WebServiceURL+"?r="+new Date().getTime()+"&eruid="+name;
			System.out.println("Data Request URL:"+url);
			URL haveURL = new URL(url);
			URLConnection yc = haveURL.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
		    String inputLine = null; 
		    while ( (inputLine = in.readLine()) != null){ 
		    	sb.append(inputLine); 
		    } 
		    in.close();
		    String strjson="";
		    JSONObject json = JSONObject.fromObject(sb.toString());
		    System.out.println("json------"+json);
		    if(null==json.get("service")||json.get("service").toString().equals("")){
		    	System.out.println("service is null!!!!!!!!!");
		    	strjson="{\"service\":[\"TAM Service\",\"guihua\",\"beidou\",\"educate\",\"guanli\",\"ITIM Service\"]}";
			    json = JSONObject.fromObject(strjson);
			    JSONArray temparray=JSONArray.fromObject(service);
		    	if(null!=temparray&&!temparray.isEmpty()){
		    		for (int i = 0; i < temparray.size(); i++) {
						String sysid= temparray.get(i).toString();
						if(i<temparray.size()-1){
							isopen +=sysid+",";
						}else{
							isopen +=sysid;
						}
					}
		    		System.out.println("isopen:"+isopen);
		    	}
		    }else{
		    	System.out.println("service not is null!!!!!!!!!");
		    	service = json.get("service").toString();
		    	JSONArray temparray=JSONArray.fromObject(service);
		    	if(null!=temparray&&!temparray.isEmpty()){
		    		for (int i = 0; i < temparray.size(); i++) {
						String sysid= temparray.get(i).toString();
						if(i<temparray.size()-1){
							isopen +=sysid+",";
						}else{
							isopen +=sysid;
						}
					}
		    		System.out.println("isopen:"+isopen);
		    	}
		    }
		   
		}catch(Exception e){
			e.printStackTrace();
		}
		
		MyAppLinkPortletSessionBean sessionBean = getSessionBean(request);
		sessionBean.setName(isopen);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())){
			PortletMode mode = request.getPortletMode();			
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
			MyAppLinkPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
	    String logsTag=request.getParameter("logsTag");
	    System.out.println("*************************logsTag*********************");
	    System.out.println("*************************==="+logsTag+"==*********************");
	    //记录用户访问记录
	    if(null!=logsTag&&"logsTag".equals(logsTag)){
	    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	//1.取得当前用户名
	    	Principal currentUser=request.getUserPrincipal();
	    	String username=currentUser.getName();
	    	System.out.println("当前用户的username:"+username);
//	    	DEPARTMENT 
	       List attributeList = new ArrayList();
		   attributeList.add("department");
		   attributeList.add("givenName");
		   attributeList.add("uid");
		   attributeList.add("preferredLanguage");
		  // attributeList.add("staffNumber");
		   attributeList.add("mobile");
		   attributeList.add("cn");
		   
		    PumaProfile profile=pumaHome.getProfile(request);
		    System.out.println("**********************************************");
		    System.out.println("正在请求用户数据！！！！！");
		    Map userMap=null;
		    try {
		    	userMap=profile.getAttributes(profile.getCurrentUser(), attributeList);
			}catch(Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		    System.out.println("已经取得请求用户数据！！！！！userMap："+userMap);
		    System.out.println("**********************************************");
	    	//2.记录访问时间
	    	String date=df.format(new Date());
	    	//3.取得当前用户部门
	    	String datapartMent="";
	    	
	    	
	    	//4.拿到对应访问的系统名称
	    	String currentSysName=request.getParameter("requestName");
	    	System.out.println("**********************************************");
	    	System.out.println("currentSysName:"+currentSysName);
	    	System.out.println("**********************************************");
	    	String sql="";
	    	try {
	    		if(null!=userMap&&null!=userMap.get("department")){
		    		datapartMent=userMap.get("department").toString();
		    	} 
	    		Context JNDI_Context = new InitialContext(); 
		    	//JNDI查找数据源 
		    	DataSource dataSource = (DataSource) JNDI_Context.lookup("CM_JDBC"); 
		    	UUID uuid = UUID.randomUUID();
		    	sql="insert into  TAB_ANALYSIS (ID,USERNAME,CHARNAME,"
		    			+ "CHECKDATE,"
		    			+ "USERDEPARTMENT,"
		    			+ "CHARFATHERNAME ) "
		    			+ "values ('"+uuid+"',"
		    					+ "'"+username+"',"
		    					+ "'"+currentSysName+"',"
		    					+ "'"+date+"',"
		    					+ "'"+datapartMent+"',"
		    			+ "'"+datapartMent+"')";
		    	System.out.println("Insert into SQL:"+sql);
		    	
		    	Connection con=dataSource.getConnection();
		    	PreparedStatement state= con.prepareStatement(sql);
		    	int i=state.executeUpdate();
		    	if(i>0){
		    		state.close();
		    		con.close();
		    		System.out.println("插入数据成功！！！");
		    	}else{
		    		System.out.println("插入数据失败！！！");
		    	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	System.out.println("插入成功了！！！！！");
	    	
	    }else{
	    	List<AppLink> listNow=null;
			//在list中添加新元素并把list写入json文件丄1�7�1�7
			if( request.getParameter("save") != null ) {
				System.out.println("save");
				String content = JSONFile.readFile(APPLINK_PATH);
				String name=request.getParameter("appName");
				String url=request.getParameter("appURL");
				String key=request.getParameter("appKey");
				AppLink applink=new AppLink();
				applink.setAppKey(key);
				applink.setAppName(name);
				applink.setAppURL(url);
				
				listNow=new ArrayList<AppLink>();
				if(content!=null&&content!=""){
					listNow= (List<AppLink>) JSONFile.getJavaCollection(new AppLink(), content);
				}
				listNow.add(applink);
			}
			//在map中删除指定元素并把map写入json文件丄1�7�1�7
			if(request.getParameter("remove") != null){
				System.out.println("remove");
				String[] appKeys = request.getParameterValues("appLink");
				String content = JSONFile.readFile(APPLINK_PATH);
				List<AppLink> removelist = new ArrayList<AppLink>();
				//1.读取所有的内容
				listNow=new ArrayList<AppLink>();
				if(content!=null&&content!=""){
					listNow= (List<AppLink>) JSONFile.getJavaCollection(new AppLink(), content);
				}
				//2.收集所有需要删除的对象
				for(int i=0;i<listNow.size();i++){
					AppLink obj=listNow.get(i);
					for(int k=0;k<appKeys.length;k++){
						System.out.println(obj.getAppKey()+":"+appKeys[k]);
						if(obj.getAppKey().equals(appKeys[k])){
							removelist.add(obj);
						}
					}
				}
				for (int j=0;j<removelist.size();j++) {
					listNow.remove(removelist.get(j));
				}
				
			}
			//在list中修改指定元素并把list写入json文件丄1�7�1�7
			if(request.getParameter("update")!=null){
				System.out.println(request.getParameter("appName"));
				String appName = request.getParameter("appName").trim();
				String appURL = request.getParameter("appURL").trim();
				String appKey=request.getParameter("appKey").trim();
				String content = JSONFile.readFile(APPLINK_PATH);
				//1.读取所有的内容
				listNow=new ArrayList<AppLink>();
				if(content!=null&&content!=""){
					listNow= (List<AppLink>) JSONFile.getJavaCollection(new AppLink(), content);
				}
				//2.收集所有需要删除的对象
				AppLink obj=null;
				AppLink remveobj=null;
				for(int i=0;i<listNow.size();i++){
					obj=listNow.get(i);
					remveobj=listNow.get(i);
					if(obj.getAppKey().equals(appKey)){
						obj.setAppName(appName);
						obj.setAppURL(appURL);
						break;
					}
				}
				if(null!=obj){
					listNow.remove(remveobj);
					listNow.add(obj);
				}
			}
			JSONArray jsonNow = JSONArray.fromObject(listNow);
			JSONFile.writeFile(APPLINK_PATH, jsonNow.toString());
	    }
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return MyAppLinkPortletSessionBean
	 */
	private static MyAppLinkPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		MyAppLinkPortletSessionBean sessionBean = (MyAppLinkPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new MyAppLinkPortletSessionBean();
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
