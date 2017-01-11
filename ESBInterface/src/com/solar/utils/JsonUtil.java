package com.solar.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	static ObjectMapper mapper = new ObjectMapper();
	 public static <T> String bean2Json(T bean) {  
	        try {  
	        	
	            return mapper.writeValueAsString(bean);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	    }  
	      
	    public static String map2Json(Map map) {  
	        try {   
	            return mapper.writeValueAsString(map);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	    }  
	      
	    public static String list2Json(List list) {  
	        try {  
	        	 
	            return mapper.writeValueAsString(list);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	    }  
	
	public static <T> T json2Bean(String json, Class<T> beanClass) { 
		 
     try {  
         return mapper.readValue(json, beanClass);  
     } catch (Exception e) {  
         e.printStackTrace();  
     }  
     return null;  
 }  
	 public static <T> List<T> json2List(String json, Class<T> beanClass) {  
		 
	        try {  
	              
	            return (List<T>)mapper.readValue(json, getCollectionType(List.class, beanClass));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
	 
	 public static Map json2Map(String json) {  
	        try {  
	        	 
	              
	            return (Map)mapper.readValue(json, Map.class);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	 public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {  
		 
	        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);     
	    }  
}
