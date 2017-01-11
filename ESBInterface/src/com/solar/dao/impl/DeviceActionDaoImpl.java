package com.solar.dao.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solar.dao.IDeviceActionDao;
import com.solar.utils.PostMethod;
@Repository
public class DeviceActionDaoImpl implements IDeviceActionDao{

	@Override
	public Map<String, Object> show(String uid, String devicetype, String deviceid) {
		// TODO Auto-generated method stub
		ResourceBundle resouce = ResourceBundle.getBundle("url"); 
		String success = "true";
		String reason = "";
		String soap = "";
		Map<String, Object> map = null;
		//����·��
		String url = resouce.getString("url");
		//����esb���������
		if(uid.equals(null) && !uid.equals("")){
			success = "false";
			reason = "uid is null or empty!";
			map.put("success", success);
			map.put("reason", reason);
		}
		else{
			soap = "{"
					+"\"DeviceAction\": {"
					+"\"uid\":\""+uid+"\"";
				
		
//		if(!devicetype.equals(null) && !devicetype.equals(""))
//			soap += ",\"devicetype\":\"" + devicetype+ "\"";
//		if(!deviceid.equals(null)&& !deviceid.equals(""))
//			soap += ",\"deviceid\":\"" + deviceid+ "\"";
		soap +="}"	+"}";
		 
			map = PostMethod.postSOAP(url, soap);
			map.put("title", getPropertes());
			
		 
		}
		return map;
	}
	
	public Map<String, Object> getPropertes(){
		ResourceBundle resouce = ResourceBundle.getBundle("table");  
		Map<String, Object> map =  new HashMap<String,Object>();
		String chi;
		String eng;
		try {
			chi = new String(resouce.getString("DeviceActionChinese").getBytes("ISO-8859-1"),"UTF-8");
			eng = new String(resouce.getString("DeviceActionEnglish").getBytes("ISO-8859-1"),"UTF-8");
			String[] chiStr = chi.split(",");
			String[] engStr = eng.split(",");
			
			for(int i = 0; i < chiStr.length;i++){
				map.put(engStr[i], chiStr[i]);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
		ResourceBundle resouce = ResourceBundle.getBundle("table"); 
		ObjectMapper mapper = new ObjectMapper();
		 
		Map<String, Object> map =  new HashMap<String,Object>();
		String chi;
		String eng;
		try {
			chi = new String(resouce.getString("DeviceActionChinese").getBytes("ISO-8859-1"),"UTF-8");
			eng = new String(resouce.getString("DeviceActionEnglish").getBytes("ISO-8859-1"),"UTF-8");
			String[] chiStr = chi.split(",");
			String[] engStr = eng.split(",");
			
			for(int i = 0; i < chiStr.length;i++){
				map.put(engStr[i], chiStr[i]);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		//list = mapper.convertValue(chi, ArrayList.class);
	 
		
		
		
	}
	
	
	
	
	
	
	
	
}















