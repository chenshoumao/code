//package com.solar.esb;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.StringWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
// 
//import com.solar.bean.Device;
//import com.solar.utils.JsonUtil;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class Test {
//	public static void main(String[] args) {
//		ResourceBundle resouce = ResourceBundle.getBundle("url"); 
//		
//		//请求路径
//		String url = resouce.getString("url");
//		String soap = "{"
//						+"\"DeviceAction\": {"
//						+"\"uid\":\"66\""
//						+"}"
//						+"}";
//		String xml = postSOAP(url, soap);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = mapper.readValue(xml, HashMap.class);
//			List list = (List) map.get("devices");
//			System.out.println(list.get(0));
//			Map<String , Object> dev = (Map<String, Object>) list.get(0);
//			System.out.println(dev.get("dev_date"));
////			String json = (String) list.get(0);
////			Device dev = mapper.readValue(json, Device.class);
////			System.out.println(dev.getDev_date());
//			String json = mapper.writeValueAsString(dev);  
//			System.out.println(json);
//			Device devi = JsonUtil.json2Bean(json, Device.class);
//			System.out.println(devi.getDev_date());
//			System.out.println(devi.getDev_y());
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 
//		
//		System.out.println(xml);
//	}
//	
//	
//	
//	 
//}
