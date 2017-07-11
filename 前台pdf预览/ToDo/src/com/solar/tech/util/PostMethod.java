package com.solar.tech.util;

import java.io.IOException; 
import java.io.BufferedReader; 
 
import java.io.InputStreamReader;   
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;   

import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;   
import org.apache.http.client.methods.HttpPost;    
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;   






public class PostMethod {
//	public static void main(String[] args) {
//		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ChallengeResponseSet><ChallengeResponse Challenge=\"who you are\" Response=\"SHA-256:am5sMmkzM3lzbDR6:JTjTzHfZAcp0I33Vd6d22tNoYWXnc+oFIubSBABjFtgF8=\"/><ChallengeResponse Challenge=\"who you are\" Response=\"1111SHA-256:am5sMmkzM3lzbDR6:JTjTzHfZAcp0IVd6d22tNoYWXnc+oFIubSBABjFtgF8=\"/></ChallengeResponseSet>";
//		
//		
//		int index = str.indexOf("Response=");
//		while(index > 0){
//			int firstIndex = str.indexOf("\"",index + 1);
//			int secondIndex = str.indexOf("\"", firstIndex + 1);
//			String result = str.substring(firstIndex+1, secondIndex);
//			System.out.println(result);
//			str = str.replace(result,"********");
//			System.out.println(str);
//			index = str.indexOf("Response=",index+1);
//		}
//		
//	}
	public static void main(String[] args) {
		getString();
	}
	
	public static String getString() {
	    String url = "http://10.160.1.2:81/getjson.asp?yhm=lijuan3";
	    Map<String, Object> map = new HashMap<String, Object>();
	    System.out.println(url.indexOf(":"));
	    int index = url.indexOf("?");
	    String urlParam = url.substring(0,index);
	    String mapParam = url.substring(index+1,url.length());
	    String[] strParam = mapParam.split("&");
	    for(int i = 0;i < strParam.length;i++){
	    	String param = strParam[i];
	    	String[] listParam = param.split("=");
	    	map.put(listParam[0], listParam[1]);
	    	
	    }
	    return httpClientPost(urlParam,map,"utf-8");
	}
	
	public static String httpClientPost(String urlParam, Map<String, Object> params, String charset) {  
       StringBuffer resultBuffer = null;  
       HttpClient client = new DefaultHttpClient();  
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
           HttpResponse response = client.execute(httpPost);  
           // 读取服务器响应数据  
           resultBuffer = new StringBuffer();  
           br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
           String temp;  
           while ((temp = br.readLine()) != null) {  
               resultBuffer.append(temp);  
           }  
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
       System.out.println(resultBuffer.toString());
       return resultBuffer.toString();  
   }  
 
 
}
