package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import servlet.Test;

public class ReadFile {
	/**
	 * 
	 * @Description: 读取文件
	 * @param @return   
	 * @return Properties  
	 * @throws
	 * @author houhuayu
	 * @date 2016-8-18
	 */
	public Properties get(){
		 Properties prop=new Properties();
	        try {
//	          FileInputStream is=new FileInputStream("config.properties");
	            InputStream is=this.getClass().getResourceAsStream("/name.properties");
	            prop.load(is);
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return prop;
	}
	
	/**
	 * 
	 * @Description: 返回用户
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author houhuayu
	 * @date 2016-8-18
	 */
	public static List<String> t2(){
		List<String> list = new ArrayList<String>();
		Test t = new Test();
		String jsonStr = t.get().toString();
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONObject json2= jsonObj.getJSONObject("name");
		JSONArray ja = json2.getJSONArray("portal1");
		JSONArray ja2 = json2.getJSONArray("portal2");
		for(int i=0;i<ja.size();i++){
			String p = ja.getJSONObject(i).getString("name");
			list.add(p);
		}
		for(int i=0;i<ja2.size();i++){
			String p = ja2.getJSONObject(i).getString("name");
			list.add(p);
		}
	return list;
	}

}
