package com.solar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostMethod {
	
	 void testPost(String urlStr) {
	        try {
	            URL url = new URL(urlStr);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/xml");

	            OutputStreamWriter out = new OutputStreamWriter(con
	                    .getOutputStream());    
	            String xmlInfo = getXmlInfo();
	            System.out.println("urlStr=" + urlStr);
	            System.out.println("xmlInfo=" + xmlInfo);
	            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
	            out.flush();
	            out.close();
	            BufferedReader br = new BufferedReader(new InputStreamReader(con
	                    .getInputStream()));
	            String line = "";
	            ObjectMapper mapper = new ObjectMapper();
	            for (line = br.readLine(); line != null; line = br.readLine()) {
	                System.out.println(line);
	                ResourceBundle resource = ResourceBundle.getBundle("table");
					String[] str = resource.getString("key").split(",");
					Map<String, Object> map = mapper.readValue(line, HashMap.class);
					System.out.println(line.indexOf("\"e\":"));
//					for(int i = 0; i < str.length;i++){
//						System.out.println(map.get(str[i]));
//						String txt = (String) map.get(str[i]);
//						if(txt != "null" && txt != null){
//							Ma
//						}
//					}
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	  private String getXmlInfo() {
	        String sb ;
	        sb = "{"
					+"\"DeviceOfflineAction\": {"
					+"\"uid\": \"66\""
					+"}}"; 
	        return sb.toString();
	    }

	    public static void main(String[] args) {
	        String url = "http://10.161.2.42:7080/ESBService/BDAD";
	        new PostMethod().testPost(url);
	    }

	
	
	/*
	 * ����xml���
	 */
	public static Map<String, Object> postSOAP(String url, String soapContent) {

		Map<String, Object> map = new HashMap<String,Object>();
		
		HttpClient httpclient = null;
		HttpPost httpPost = null;
		BufferedReader reader = null;
		ObjectMapper mapper = new ObjectMapper();
		int i = 0;

		while (i < 4) {
			try {
				httpclient = new DefaultHttpClient();
				httpPost = new HttpPost(url);
				StringEntity myEntity = new StringEntity(soapContent, "UTF-8");
				httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
				httpPost.setEntity(myEntity);
				HttpResponse response = httpclient.execute(httpPost);
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {

					reader = new BufferedReader(new InputStreamReader(
							resEntity.getContent(), "UTF-8"));
					StringBuffer sb = new StringBuffer();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
						sb.append("\r\n");
					}
					System.out.println(sb.toString());
					String json = sb.toString();
					int index = json.indexOf("\"e\":");
					if(index > 0){
						json = json.replace("}}", "}]");
						json = json.replace("{\"e\":", "["); 
					//	sb.replace("{\"e\":", "[");
					//	index = json.indexOf(index, "}");
						
					}
					
					
					
//					 Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();  
//				       while (iterator.hasNext()) {  {"listdevices":{"e":{"devid":"3109888","devtype":"3"}}
//				           Entry<String, Object> elem = iterator.next();  
//				           list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));  
//				       }  
					System.out.println(json);
					try {
						map = mapper.readValue(json, HashMap.class);
					} catch (Exception e) {
						// TODO: handle exception
						map.put("error", "解析错误");
					}
					return map;
					
					
				}

			} catch (Exception e) {
				i++;
				if (i == 4) {
				//	logger.error("not connect:" + url + "\n" + e.getMessage());
					System.out.println("not connect:" + url + "\n" + e.getMessage());
					map.put("error", "not connect:" + url + "\n" + e.getMessage());
				}
			} finally {
				if (httpPost != null) {
					httpPost.abort();
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (httpclient != null) {
					httpclient.getConnectionManager().shutdown();
				}
			}
		}
		return map;
	}
}
