package com.solar.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.workplace.wcm.api.Content;
import com.ibm.workplace.wcm.api.DocumentId;
import com.ibm.workplace.wcm.api.DocumentLibrary;
import com.ibm.workplace.wcm.api.DocumentTypes;
import com.ibm.workplace.wcm.api.SiteArea;
import com.ibm.workplace.wcm.api.WCMApiObject;
import com.ibm.workplace.wcm.api.WCM_API;
import com.ibm.workplace.wcm.api.Workspace;
import com.ibm.workplace.wcm.api.exceptions.QueryServiceException;
import com.ibm.workplace.wcm.api.query.Query;
import com.ibm.workplace.wcm.api.query.QueryDepth;
import com.ibm.workplace.wcm.api.query.QueryService;
import com.ibm.workplace.wcm.api.query.ResultIterator;
import com.ibm.workplace.wcm.api.query.Selectors;
import com.ibm.workplace.wcm.api.query.SortDirection;
import com.ibm.workplace.wcm.api.query.Sorts;
import com.wcm.rest.bean.ContentBean;

@Controller
@RequestMapping("/WcmRest")
public class WcmRest {
		
	@RequestMapping("/show")
	@ResponseBody
	public String say(){
		return "hello";
	}
	
	@RequestMapping("getContentName")
	@ResponseBody
	public Map<String, Object> getContentName() throws UnsupportedEncodingException, JsonProcessingException {
		//String url = "http://10.161.2.73:10039/WcmRest/ContentRest";
		Map<String, Object> map = new HashMap<String, Object>();
		//String result = sendGet(url,"");
		String result = getContentData();
		//String inputer   = new String( result.getBytes("GBK") , "UTF-8");\
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(result, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}	
	
	@RequestMapping("getContentById")
	@ResponseBody
	public Map<String, Object> getContentById(String contentId) throws IOException {
		String url = "http://10.161.2.73:10039/WcmRest/ParseContent";
		 
		Map<String, Object> map = new HashMap<String, Object>();
		//String result = sendGet(url,"contentId="+contentId);
		String result = getPareData(contentId);
		//String inputer   = new String( result.getBytes("GBK") , "UTF-8");\
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(result, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	 
	}
	
	 public static String sendGet(String url, String param) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	        	String urlNameString = url;
	        	if(param != "")
	        		urlNameString += "?" + param;
	        		
	            URL realUrl = new URL(urlNameString);
	            // �򿪺�URL֮�������
	            URLConnection connection = realUrl.openConnection();
	            // ����ͨ�õ���������
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            connection.setRequestProperty("Accept-Charset", "utf-8");
	            connection.setRequestProperty("contentType", "utf-8");
	            // ����ʵ�ʵ�����
	            connection.connect();
	            // ��ȡ������Ӧͷ�ֶ�
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // �������е���Ӧͷ�ֶ�
	            for (String key : map.keySet()) {
	             //   System.out.println(key + "--->" + map.get(key));
	            }
	            // ���� BufferedReader����������ȡURL����Ӧ
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("����GET��������쳣��" + e);
	            e.printStackTrace();
	        }
	        // ʹ��finally�����ر�������
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	 
	 
	 
	 private String getPareData(String contentId) throws IOException {
		 String reslut = null;
		 CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet("http://10.161.2.73:10039/wps/myportal");
			CloseableHttpResponse closeRes = httpClient.execute(httpget);
			try {
				HttpEntity entity = closeRes.getEntity();
				String html = EntityUtils.toString(entity, "UTF-8"); 	
				int actionIndex = html.indexOf("action=\"",html.indexOf("csm"));
		        String action = html.substring(actionIndex + 8,html.indexOf("\"",actionIndex + 8));
				System.out.println(action);
				
				HttpPost httppost = new HttpPost("http://10.161.2.73:10039"+action);
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("wps.portlets.userid", "wpsadmin"));
		        params.add(new BasicNameValuePair("password", "wpsadmin"));
		       
		        httppost.setEntity(new UrlEncodedFormEntity(params));
		 
		        httpClient.execute(httppost);
		        httppost.abort();
		        
		      
		       // String contentUrl = "http://10.161.2.73:10039/wps/mycontenthandler/wcmrest/Content/9e3452f1-b7b3-4bbe-a0e0-b3b435b4fa7a?mime-type=application/json";
		        String contentUrl = "http://10.161.2.73:10039/wps/mycontenthandler/wcmrest/Content/"+contentId+"?mime-type=application/json";
		        
		        HttpGet get = new HttpGet(contentUrl); 
		        get.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
//		        get.getParams().setParameter("mime-type", "application/json");
		        System.out.println("hello");
		        closeRes = httpClient.execute(get);
		        entity = closeRes.getEntity();
		        reslut = EntityUtils.toString(entity, "GBK"); 
		        System.out.println(reslut);
		       
		        get.abort();
			} finally {
				closeRes.close();
			}
		} finally {
			httpClient.close();
		}
		 
		 return reslut;
	 }
	 
	 
	 private String getContentData() throws JsonProcessingException{
		 String str = null;
		 Map<String,Object> map = new HashMap<String,Object>();
			try { 
				 
				Workspace workspace = null;
				workspace = WCM_API.getRepository().getAnonymousWorkspace();
				System.out.println(111);

				DocumentLibrary library = workspace
						.getDocumentLibrary("guanweihui");
				workspace.setCurrentDocumentLibrary(library);

				// 创建查询服务
				QueryService queryservice = workspace.getQueryService();
				// 创建查询器
				Query query = queryservice.createQuery();
				// 设置查询类型为 站点域 ，即sitearea
				query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
						.getApiType()));

				// 查找站点域
				query.addSelector(Selectors.nameEquals("extranet"));
				query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
				ResultIterator iterator;

				iterator = queryservice.execute(query);

				// 处理查询结果
				if (iterator.hasNext()) {
					// 声明相关应用类
					WCMApiObject apiobj = (WCMApiObject) iterator.next();
					if (apiobj instanceof SiteArea) {
						// 强转 成 SiteArea
						SiteArea areaobj = (SiteArea) apiobj;
						// SiteAreaContent siteAreaContent = new SiteAreaContent();
						map = printSiteArea(workspace,areaobj.getId());
					}
				}
				WCM_API.getRepository().endWorkspace();
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper(); 
			str = mapper.writeValueAsString(map);
		 
		 return str;
	 }
	 
	 
	 private Map<String,Object> printSiteArea(Workspace workspace, DocumentId id) {
			Map<String,Object> map = new HashMap<String,Object>();
			try { 
				// 创建查询服务
				QueryService queryservice = workspace.getQueryService();
				// 创建查询器
				Query query = queryservice.createQuery();
				// 设置查询类型为 站点域 ，即sitearea
				query.addSelectors(Selectors.typeIn(DocumentTypes.SiteArea
						.getApiType()));
				query.addParentId(id, QueryDepth.CHILDREN);
				// 查找站点域
				query.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
				ResultIterator iterator; 
				iterator = queryservice.execute(query);
				while (iterator.hasNext()) { 
					WCMApiObject aobj = (WCMApiObject) iterator.next();
					if (aobj instanceof SiteArea) {
						SiteArea area = (SiteArea) aobj; 
						System.out.println("sitearea title is : "
								+ area.getTitle() + "<br>");
						map.put("siteAreaTitle", area.getTitle());
						Query content = queryservice.createQuery();
						content.addSelectors(Selectors.typeIn(DocumentTypes.Content.getApiType()));
						content.addParentId(area.getId(), QueryDepth.CHILDREN);
						content.setSorts(Sorts.byPublishDate(SortDirection.DESCENDING));
						ResultIterator contentIt = queryservice.execute(content);
						int count = 0;
						ArrayList list = new ArrayList();
						while(contentIt.hasNext()){
							count ++;
							WCMApiObject object = (WCMApiObject) contentIt.next(); 
							if (object instanceof Content) {
								Content contentC = (Content) object;
								System.out.println("content title is : " + contentC.getTitle() + "<br>");
								System.out.println(" it id is : " + contentC.getId().getId());
								ContentBean bean =  new ContentBean();
								bean.setContentName( contentC.getTitle());
								bean.setContentId(contentC.getId().getId());
								list.add(bean);							
							}
							if(count == 3)
								break;
						}
						map.put(area.getTitle(), list);
						map.putAll(printSiteArea(workspace,area.getId())); 
					}
				} 
			} catch (QueryServiceException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return map;
		}

}
