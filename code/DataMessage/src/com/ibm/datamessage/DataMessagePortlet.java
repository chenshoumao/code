package com.ibm.datamessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ibm.utils.DateUtil;
import com.ibm.utils.HttpUrl;

/**
 * A sample portlet based on GenericPortlet
 */
public class DataMessagePortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_DataMessage/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "DataMessagePortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "DataMessagePortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String SESSION_BEAN  = "DataMessagePortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "DataMessagePortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "DataMessagePortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "DataMessagePortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "DataMessagePortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".DataMessagePortletEditKey";     // Key for the portlet preferences



	 
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
		DataMessagePortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		DataMessagePortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}


		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
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
			DataMessagePortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return DataMessagePortletSessionBean
	 */
	private static DataMessagePortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		DataMessagePortletSessionBean sessionBean = (DataMessagePortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new DataMessagePortletSessionBean();
			
//			String URL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
			String URL = "http://10.161.2.72:10039/";
			//数量接口
			String pendingUrl =  URL+HttpUrl.PENDINGURL;
			//待办列表接口
			String pendListUrl =   URL+HttpUrl.PENDLISTURL;
			//已办列表接口
			String haveListUrl =   URL+HttpUrl.HAVELISTURL;
			//会议列表接口
			String meetingListUrl = URL+HttpUrl.MEETINGLISTURL;
			
			//友情链接接口
			String linksUrl =        URL+HttpUrl.LINKSURL;
			
			try{
				StringBuffer sb = new StringBuffer();
				String pendUrl ="http://10.160.1.2:81/getjson.asp?yhm=lijuan3";
				URL pendURL = new URL(pendUrl);
				URLConnection yc = pendURL.openConnection();
			    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
			    String inputLine = null; 
			    while ( (inputLine = in.readLine()) != null){ 
			    	sb.append(inputLine); 
			    } 
			    in.close();
			    String str = sb.toString().substring(1);
			    String result = str.substring(0, str.length()-1);
			    System.out.println("json结果集是……………………………………………………………………&&&&&&&&&&&&&&&&&&&&&&&&&^^^^^^^^^^^^^^^^^^^"+result);
			    JSONArray ja = JSONArray.fromObject(result);
			       Iterator<Object> it = ja.iterator();
			       List<Waiting> list=new ArrayList<Waiting>();
			       String count="";
			       while (it.hasNext()) {
			    	   Waiting waiting = new Waiting();
			    	   JSONObject ob =  (JSONObject) it.next();
			    	   Object title = ob.get("title");
			    	   Object systemCnName = ob.get("systemCnName");
			    	   Object receivetime = ob.get("receivetime");
			    	   Object currentUrl = ob.get("currentUrl");
			    	   Object ocount = ob.get("count");
			    	  if(null==title){
			    		  waiting.setTitle("");
			    	  }else{
			    		  waiting.setTitle(title.toString());
			    	  }
			    	  if(null==systemCnName){
			    		  waiting.setFrom("");  
			    	  }else{
			    		  waiting.setFrom(systemCnName.toString()); 
			    	  }
			    	  if(null==receivetime){
			    		  waiting.setDatatime("");
			    	  }else{
			    		  waiting.setDatatime(receivetime.toString());  
			    	  }
			    	  if(null==currentUrl){
			    		  waiting.setUrl(""); 
			    	  }else{
			    		  waiting.setUrl(currentUrl.toString()); 
			    	  }
			    	  if(null==ocount){
			    		  count = "";
			    	  }else{
			    		  count = ocount.toString();  
			    	  }
				    	waiting.setOriginator("");
//				    	waiting.setPendingid(ob.get("pendingid").toString());
				    	list.add(waiting);
			       }
			       System.out.println("sessionBean list &^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+list);
			       System.out.println("sessionBean list &^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+count);
			       sessionBean.setListWaiting(list);
			       sessionBean.setPend(count);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
//			//请求待办数量接口
//			try {
//				StringBuffer sbPend = new StringBuffer();
//				TreeMap<String, String> params = new TreeMap<String,String>();
////				params.put("appID", "OA,HR,ME");
//				params.put("accountName", "houhuayu123");
//				params.put("type", "pend");
//				params.put("timeStamp", DateUtil.getSystemTime());
//				String appID = "OA,HR,ME";
//				pendingUrl +="?appID="+appID;
//				String pendUrl  = getMethod(pendingUrl, params);
//				URL pendURL = new URL(pendUrl);
//				URLConnection yc = pendURL.openConnection();
//			    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
//			    String inputLine = null; 
//			    while ( (inputLine = in.readLine()) != null){ 
//			    	sbPend.append(inputLine); 
//			    } 
//			    in.close();
//			    JSONObject json = JSONObject.fromObject(sbPend.toString());
//			    String pendNum = json.get("num").toString();
//			    sessionBean.setPend(pendNum);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
	      //请求已办数量接口
			try{
				StringBuffer sbHave = new StringBuffer();
				TreeMap<String, String> params = new TreeMap<String,String>();
//				params.put("appID", "OA,HR,ME");
				params.put("accountName", "houhuayu123");
				params.put("type", "have");
				params.put("timeStamp", DateUtil.getSystemTime());
				String appID = "OA,HR,ME";
				pendingUrl +="?appID="+appID;
				String haveUrl  = getMethod(pendingUrl, params);
				URL haveURL = new URL(haveUrl);
				URLConnection yc = haveURL.openConnection();
			    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
			    String inputLine = null; 
			    while ( (inputLine = in.readLine()) != null){ 
			    	sbHave.append(inputLine); 
			    } 
			    in.close();
			    JSONObject json = JSONObject.fromObject(sbHave.toString());
			    String haveNum = json.get("num").toString();
			    sessionBean.setHava(haveNum);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//请求会议数量接口
			try{
				StringBuffer sbMeeting = new StringBuffer();
				TreeMap<String, String> params = new TreeMap<String,String>();
//				params.put("appID", "OA,HR,ME");
				params.put("accountName", "houhuayu123");
				params.put("type", "meeting");
				params.put("timeStamp", DateUtil.getSystemTime());
				String appID = "OA,HR,ME";
				pendingUrl +="?appID="+appID;
				String meetingUrl  = getMethod(pendingUrl, params);
				URL haveURL = new URL(meetingUrl);
				URLConnection yc = haveURL.openConnection();
			    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
			    String inputLine = null; 
			    while ( (inputLine = in.readLine()) != null){ 
			    	sbMeeting.append(inputLine); 
			    } 
			    in.close();
			    JSONObject json = JSONObject.fromObject(sbMeeting.toString());
			    String meetNum = json.get("num").toString();
			    sessionBean.setMeeting(meetNum);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//请求待办列表接口
			
//			try {
//		     List<Waiting> waitingList = new ArrayList<Waiting>();
//			 StringBuilder sb = new StringBuilder();   
//		     URL oracle = new URL(pendListUrl);
//			    URLConnection yc = oracle.openConnection();
//			    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
//			    String inputLine = null; 
//			    while ( (inputLine = in.readLine()) != null){ 
//			      sb.append(inputLine); 
//			    } 
//			    in.close();
//			    JSONObject js = JSONObject.fromObject(sb.toString());
//			    JSONObject jsobj = (JSONObject) js.get("obj");
//			    List list = (List) jsobj.get("data");
//			    for(int i=0;i<list.size();i++){
//			    	 Waiting waiting = new Waiting();
//			    	JSONObject jsonWait  = (JSONObject) list.get(i);
//			    	waiting.setTitle(jsonWait.get("title").toString());
//			    	waiting.setFrom(jsonWait.get("module").toString());
//			    	waiting.setOriginator(jsonWait.get("currNode").toString());
//			    	waiting.setDatatime(jsonWait.get("receiveTime").toString());
//			    	waiting.setPendingid(jsonWait.get("pendingid").toString());
//			    	waiting.setUrl(jsonWait.getString("url"));
//			    	waitingList.add(waiting);
//			    }
//			    sessionBean.setListWaiting(waitingList);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}	
			
			//请求已办列表接口
			try {
				List<WaitingFinish> listWaitingFinish = new ArrayList<WaitingFinish>(); 
				 StringBuilder sb = new StringBuilder();   
			     URL oracle = new URL(haveListUrl);
				    URLConnection yc = oracle.openConnection();
				    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
				    String inputLine = null; 
				    while ( (inputLine = in.readLine()) != null){ 
				      sb.append(inputLine); 
				    } 
				    in.close();
				    JSONObject js = JSONObject.fromObject(sb.toString());
				    JSONObject jsonObj = (JSONObject) js.get("obj");
				    List list = (List) jsonObj.get("data");
				    System.out.println(js);
				    for(int i=0;i<list.size();i++){
				    	WaitingFinish waitingFinish = new WaitingFinish();
				    	JSONObject jsonWait  = (JSONObject) list.get(i);
				    	waitingFinish.setTitle(jsonWait.get("title").toString());
				    	waitingFinish.setFrom(jsonWait.get("module").toString());
				    	waitingFinish.setOriginator(jsonWait.get("currNode").toString());
				    	waitingFinish.setDatatime(jsonWait.get("receiveTime").toString());
				    	waitingFinish.setPendingid(jsonWait.get("pendingid").toString());
				    	waitingFinish.setUrl(jsonWait.getString("url"));
				    	listWaitingFinish.add(waitingFinish);
				    }
				    sessionBean.setListWaitingFinish(listWaitingFinish);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
			  //请求会议列表接口
			  try{
				  List<Meeting> listMeeting = new ArrayList<Meeting>(); 
					 StringBuilder sb = new StringBuilder();   
				     URL oracle = new URL(meetingListUrl);
					    URLConnection yc = oracle.openConnection();
					    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
					    String inputLine = null; 
					    while ( (inputLine = in.readLine()) != null){ 
					      sb.append(inputLine); 
					    } 
					    in.close();
					    JSONObject js = JSONObject.fromObject(sb.toString());
					    JSONObject jsonObj = (JSONObject) js.get("obj");
					    List list = (List) jsonObj.get("data");
					    for(int i=0;i<list.size();i++){
					    	Meeting meeting = new Meeting();
					    	JSONObject jsonWait  = (JSONObject) list.get(i);
					    	meeting.setTitle(jsonWait.get("title").toString());
					    	meeting.setFrom(jsonWait.get("module").toString());
					    	meeting.setOriginator(jsonWait.get("currNode").toString());
					    	meeting.setDatatime(jsonWait.get("receiveTime").toString());
					    	meeting.setPendingid(jsonWait.get("pendingid").toString());
					    	meeting.setUrl(jsonWait.getString("url"));
					    	listMeeting.add(meeting);
					    }
					    sessionBean.setListMeeting(listMeeting);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			
			  
			//请求友情链接接口
			try{
				 StringBuilder sb = new StringBuilder();   
			     URL oracle = new URL(linksUrl);
				    URLConnection yc = oracle.openConnection();
				    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
				    String inputLine = null; 
				    while ( (inputLine = in.readLine()) != null){ 
				      sb.append(inputLine); 
				    } 
				    in.close();
				    JSONObject js = JSONObject.fromObject(sb.toString());
				    JSONObject jsobj = (JSONObject) js.get("obj");
				    JSONArray ja = (JSONArray) jsobj.get("data");
				    System.out.println("ja is "+ja);
				    Map<String,List<Link>> map = new LinkedHashMap<String,List<Link>>();
				    for(int i=0;i<ja.size();i++){
				    	List<Link> list = new ArrayList<Link>();
				    	 JSONObject jo = (JSONObject) ja.get(i);
				    	 String title = jo.getString("title");
				    	
				    	 JSONArray jLink = (JSONArray)jo.get("links");
				    	 for(int j=0;j<jLink.size();j++){
				    		 Link link = new Link();
				    		 JSONObject json = (JSONObject) jLink.get(j);
				    		 String link_name = json.getString("name");
				    		 String link_url = json.getString("url");
				    		 link.setName(link_name);
				    		 link.setUrl(link_url);
				    		 list.add(link);
				    	 }
				    	 map.put(title, list);
				    }
				    System.out.println("map is "+map);
					   for (String key : map.keySet()) {
						   System.out.println("key3:"+key+"   value3 :"+map.get(key));
					   }
				    sessionBean.setMapLink(map);
			}catch(Exception e){
				e.printStackTrace();
			}
			
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
	
	/**
	 * 
	 * @Description: get请求url拼接
	 * @param @param url 请求url
	 * @param @param params 参数列表
	 * @param @return
	 * @param @throws Exception   
	 * @return String
	 * @throws
	 * @author houhuayu
	 * @date 2016年7月7日
	 */
	private static String getMethod(String url, TreeMap<String, String> params)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String value = entry.getValue();
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				value = URLEncoder.encode(value, "utf-8");

				sb.append("&");

				sb.append(entry.getKey()).append("=").append(value);
			}
		}
		url = sb.toString();
//		return new GetMethod(url);
		return url;
	}

}
