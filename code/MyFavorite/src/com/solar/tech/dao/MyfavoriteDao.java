package com.solar.tech.dao;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.xerces.dom.ChildNode;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ibm.portal.um.PumaHome;
import com.ibm.portal.um.PumaProfile;
import com.ibm.portal.um.User;
import com.ibm.portal.um.exceptions.PumaAttributeException;
import com.ibm.portal.um.exceptions.PumaException;
import com.ibm.portal.um.exceptions.PumaMissingAccessRightsException;
import com.ibm.portal.um.exceptions.PumaModelException;
import com.ibm.portal.um.exceptions.PumaSystemException;
import com.solar.util.UserInfo;
 

@Repository
public class MyfavoriteDao {

	public Map<String, Object> add(String imageName, String myName, String url) {
		// TODO Auto-generated method stub
		System.out.println(imageName + "," + myName + "," + url);
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		String username = userInfo.getLoginName();
		ResourceBundle resource = ResourceBundle.getBundle("MyFavorite");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(resource.getString("url"));
		try {
			File jsonFile = new File(resource.getString("url"), username + ".json");
			ObjectNode rootNode = null;
			if (jsonFile.exists()) {
				rootNode = (ObjectNode) mapper.readTree(jsonFile);
			} else {
				rootNode = mapper.createObjectNode();
			}

			// rootNode.put("ANumberField1Key2221", 2222);

			ObjectNode node = mapper.createObjectNode();

			node.put("imageName", imageName);
			node.put("url", url); 
			rootNode.set(myName, node); 
			JsonFactory jsonFactory = new JsonFactory(); 
			JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8); 
			map.put("result", "success");

			mapper.writeTree(jsonGenerator, rootNode);
		} catch (JsonProcessingException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "failed");
		} catch (IOException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "failed");
		}

		return map;
	}

	public Map<String, Object> remove(String myName) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		String username = userInfo.getLoginName();
		try {
			ResourceBundle resource = ResourceBundle.getBundle("MyFavorite");
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(resource.getString("url"));
			File jsonFile = new File(resource.getString("url"), username + ".json");
			ObjectNode rootNode = null;
			if (jsonFile.exists()) {
				rootNode = (ObjectNode) mapper.readTree(jsonFile);
			} else {
				rootNode = mapper.createObjectNode();
			}

			rootNode.remove(myName);

			JsonFactory jsonFactory = new JsonFactory();
			JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);

			mapper.writeTree(jsonGenerator, rootNode);
			map.put("result", "success");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "failed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "failed");
		}

		return map;
	}

	
	 
		
	 

	public Map<String, Object> edit(String oldMyName, String myName, String url) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		String username = userInfo.getLoginName();
		ResourceBundle resource = ResourceBundle.getBundle("MyFavorite");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(resource.getString("url"));
			File jsonFile = new File(resource.getString("url"), username + ".json");
			ObjectNode rootNode = null;
			if (jsonFile.exists()) {
				rootNode = (ObjectNode) mapper.readTree(jsonFile);
			} else {
				rootNode = mapper.createObjectNode();
			}
 
			
			Iterator<String> keys = rootNode.fieldNames();
			 while(keys.hasNext()){  
		            String fieldName = keys.next(); 
		            System.out.println(fieldName + "," + oldMyName);
		            System.out.println(fieldName == oldMyName);
		            if(fieldName == oldMyName || fieldName.equals(oldMyName) ){
		            	System.out.println("come in!!!!");
		            	 JsonNode chlidNode = rootNode.path(fieldName);
		            	 String imageName = chlidNode.path("imageName").asText();
		            	 System.out.println(imageName);
		            	 ObjectNode node = mapper.createObjectNode();
		            	 System.out.println(1);
		            	 node.put("imageName",imageName);
		            	 System.out.println(2);
		            	 node.put("url", url);
		            	 System.out.println(3);
		            	 rootNode.set(myName, node);
		            	 System.out.println(rootNode);
		            	 if(!fieldName.equals(myName)){
		            		 rootNode.remove(fieldName);
		            		 System.out.println(rootNode);
		            	 }
		            	 break;
		            		
		            }
		           
			 }
			System.out.println(rootNode);
			JsonFactory jsonFactory = new JsonFactory();
			JsonGenerator jsonGenerator;

			jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
			mapper.writeTree(jsonGenerator, rootNode);
			map.put("result", "success");
		} catch (IOException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "failed");
		}

		return map;
	}
	
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		File jsonFile = new File("/root/Desktop/", "lea" + ".json");
	//	String json = IOUtils.toString(new URL("/root/Desktop/wpsadmins.json").openStream(),"utf-8"); 
		ObjectMapper mapper = new ObjectMapper();
	//	JsonNode root = mapper.readTree(json);
		
		ObjectNode rootNode = null;
		if (!jsonFile.exists()) {
			rootNode = mapper.createObjectNode();
			jsonFile.createNewFile();
			JsonFactory jsonFactory = new JsonFactory();
			JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
			mapper.writeTree(jsonGenerator, rootNode);
		} 
		
		
		 
	}
	public List<Map<String, Object>> read() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		UserInfo userInfo = new UserInfo();
		String username = userInfo.getLoginName();
		System.out.println("now user is : " + username);
		ResourceBundle resource = ResourceBundle.getBundle("MyFavorite");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(username);
			System.out.println(resource.getString("url") + ",csm");
			File jsonFile = new File(resource.getString("url"), username + ".json");
			JsonNode rootNode = null;
			if (!jsonFile.exists()) {
				rootNode = mapper.createObjectNode();
				jsonFile.createNewFile();
				JsonFactory jsonFactory = new JsonFactory();
				JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
				mapper.writeTree(jsonGenerator, rootNode);
			}
			
			 
			rootNode =  mapper.readTree(jsonFile);
			 

			Iterator<String> keys = rootNode.fieldNames();       
	        while(keys.hasNext()){  
	          String fieldName = keys.next();  
	          JsonNode chlidNode = rootNode.path(fieldName); 
	          Map<String, Object> map = new HashMap<String, Object>();
	         // Map<String, Object> data = new HashMap<String, Object>();
	          map.put("myName", fieldName);
	          map.put("imageName", chlidNode.path("imageName").asText());
	          map.put("url", chlidNode.path("url").asText());
	          list.add(map); 
	        }  
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}

		return list;
		 
	}

}
