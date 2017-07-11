package com.ibm.personalspacecsm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.DefaultHttpClient;  
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.ibm.json.java.JSONObject;




/**
 * A sample portlet based on GenericPortlet
 */
public class PersonalSpaceCsmPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_PersonalSpaceCsm/jsp/";    // JSP folder name

	public static final String SESSION_BEAN  = "DispatchPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "DispatchPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "DispatchPortletFormText";     // Parameter name for the text input
	public static final String VIEW_JSP = "PersonalSpaceCsmPortletView";	
	public static final String SEARCH_JSP = "SearchPeople";
	//个人信息页面						
	public static final String NEWFILE = "SearchPeople";  //人员查找页面
	private static boolean IsSearchPage=false;
 
	private Integer totalPage=0;

	public void init() throws PortletException{
		super.init();
		
	}

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		 

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
			  
	}

	 
	 
	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, java.io.IOException {
		String resourceID = request.getResourceID();
		if (resourceID.equals("resourceID")) {
			// Insert code for serving the resource 
		}
	}
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}
	private static String getJspExtension(String markupName) {
		return "jsp";
	}
	
	
	
	/**
	 * 
	 * @Description: 查询用户信息
	 * @param @param username
	 * @param @param mail
	 * @param @param mobile
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author houhuayu
	 * @date 2016年8月23日
	 */
	public String getInfo() {
		String dataurl = "http://10.161.2.72:10039/AppData/PersonalSpace/show.action";
		Map<String, Object> map = new HashMap<String, Object>();
		 
		System.out.println("参数map是……………………………………………………^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+map);
		try {
			String result = this.httpClientPost(dataurl, map, "UTF-8");
			return result;
		} catch (Exception e) {
			return "error";
		}
	}
	
	/**
	 * 
	 * @Description: 更新用户信息
	 * @param @param map
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author houhuayu
	 * @date 2016年8月23日
	 */
	public String upInfo(Map<String,Object> map) {
		String dataurl = "http://10.161.2.68:9080/Login/UserInfo/updateUser.action";
		try {
			String result = this.httpClientPost(dataurl, map, "UTF-8");
			System.out.println("更新状态………………………………………………………………………………………………………………………………………………………………………………………………………………………………"+result);
			return result;
		} catch (Exception e) {
			return "error";
		}
	}
	
	public String getInfos(Map<String,Object> map){
		String dataurl = "http://10.161.2.68:9080/Login/UserInfo/search.action";
		try {
			String result = this.httpClientPost(dataurl, map, "UTF-8");
			System.out.println("模糊查询结果………………………………………………………………………………………………………………………………………………………………………………………………………………………………"+result);
			return result;
		} catch (Exception e) {
			return "error";
		}
	}
	
	
	public   String httpClientPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        DefaultHttpClient client = null; 
        HttpPost httpPost = new HttpPost(urlParam);  
        // 构建请求参数  
        List<NameValuePair> list = new ArrayList<NameValuePair>();  
        Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();  
        while (iterator.hasNext()) {  
            Entry<String, Object> elem = iterator.next();  
            list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));  
        }  
        BufferedReader br = null;  
        try {  
            if (list.size() > 0) {  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);  
                httpPost.setEntity(entity);  
            } 
            System.out.println("client 初始化前………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            client = new DefaultHttpClient();
            System.out.println("client 初始化后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            HttpResponse response = client.execute(httpPost); 
            System.out.println("响应后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            // 读取服务器响应数据  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }
            System.out.println("读取数据后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
	public static void main(String[] args) {
		System.out.println(123);
	}

}
