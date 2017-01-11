package com.solar.dao.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solar.dao.IAlarmActionDao;
import com.solar.utils.PostMethod;
@Repository
public class AlarmActionDaoImpl implements IAlarmActionDao{

	@Override
	public Map<String, Object> show(String uid) {
		ResourceBundle resouce = ResourceBundle.getBundle("url"); 
		String success = "true";
		String reason = "";
		String soap = "";
		Map<String, Object> map = null;
		//����·��
		String url = resouce.getString("url");
		//����esb���������
		 
		 
			soap = "{"
				+"\"AlarmAction\": {"
				+"\"uid\": \""+uid+"\""
				+"}}"; 
		 
		 
		 
			map = PostMethod.postSOAP(url, soap);
			map.put("title", getPropertes());
			return map;
		 
	}
	  
	public Map<String, Object> getPropertes(){
		ResourceBundle resouce = ResourceBundle.getBundle("table");  
		Map<String, Object> map =  new HashMap<String,Object>();
		String chi;
		String eng;
		try {
			chi = new String(resouce.getString("AlarmActionChinese").getBytes("ISO-8859-1"),"UTF-8");
			eng = new String(resouce.getString("AlarmActionEnglish").getBytes("ISO-8859-1"),"UTF-8");
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
}
