package com.solar.tech.dao;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.solar.tech.bean.Adapter;
import com.solar.tech.bean.Count;
import com.solar.tech.bean.SysName;
import com.solar.tech.bean.ToDo;
import com.solar.tech.util.PostMethod;
import com.solar.tech.util.getMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdapterDao {
	
	 
	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ResourceBundle bundle = ResourceBundle.getBundle("Todourl");
			String url1 = bundle.getString("url");
			System.out.println(url1);

			File file = new File(url1);
			JsonNode node = mapper.readTree(file);
			System.out.println(node);
			System.out.println(mapper.writeValueAsString(node));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRootString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ResourceBundle bundle = ResourceBundle.getBundle("Todourl");
			String url1 = bundle.getString("url");
			System.out.println(url1);

			File file = new File(url1);
			JsonNode node = mapper.readTree(file);

			return mapper.writeValueAsString(node);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return "";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Map<String, Object> edit(Adapter adapter) {
		Map map = new HashMap();
		try {
			add(adapter);
			map.put("result", "success");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("result", "failed");
		}
		return map;
	}

	public Map<String, Object> remove(Adapter adapter) {
		Map map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		String json = getRootString();
		try {
			ObjectNode root = (ObjectNode) mapper.readTree(json);

			Iterator keys = root.fieldNames();
			while (keys.hasNext()) {
				String fieldName = (String) keys.next();
				if (fieldName.equals(adapter.getOldSystemName())) {
					root.remove(fieldName);
					break;
				}
			}
			ResourceBundle bundle = ResourceBundle.getBundle("Todourl");
			String url = bundle.getString("url");
			System.out.println(url);
			File jsonFile = new File(url);
			JsonFactory jsonFactory = new JsonFactory();
			JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
			mapper.writeTree(jsonGenerator, root);
			map.put("result", "success");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("result", "failed");
		}
		return map;
	}

	public Map<String, Object> add(Adapter adapter) throws JsonProcessingException {
		Map map = new HashMap();
		String json = getRootString();
		ObjectMapper mapper = new ObjectMapper();
		try {
			ObjectNode root = (ObjectNode) mapper.readTree(json);
			if (!adapter.getSystemName().equals(adapter.getOldSystemName())) {
				Iterator keys = root.fieldNames();
				while (keys.hasNext()) {
					String fieldName = (String) keys.next();
					if (fieldName.equals(adapter.getOldSystemName())) {
						root.remove(fieldName);
						break;
					}
				}
			}
			ObjectNode roota = mapper.createObjectNode();

			ObjectNode node1 = mapper.createObjectNode();
			ObjectNode node2 = mapper.createObjectNode();
			ObjectNode node3 = mapper.createObjectNode();
			node1.put("EnName", adapter.getEnName());
			node1.put("CnName", adapter.getCnName());
			node1.put("sysUrl", adapter.getSysUrl());

			node1.put("jsonroot", adapter.getJsonroot());
			node1.put("jsondata", adapter.getJsondata());
			node2.put("title", adapter.getTitle());
			node2.put("receiveTime", adapter.getReceiveTime());
			node2.put("PendingName", adapter.getPendingName());
			node2.put("todoUrl", adapter.getTodoUrl());
			node3.put("sysCount", adapter.getSysCount());
			node3.put("sysListUrl", adapter.getSysListUrl());
			node1.set("todomap", node2);
			node1.set("countmap", node3);
			root.set(adapter.getSystemName(), node1);

			ResourceBundle bundle = ResourceBundle.getBundle("Todourl");
			String url = bundle.getString("url");
			System.out.println(url);
			File jsonFile = new File(url);
			JsonFactory jsonFactory = new JsonFactory();

			JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
			mapper.writeTree(jsonGenerator, root);
			map.put("result", "success");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("result", "failed");
		}

		return map;
	}

	/*
	 * {"oa":{"EnName":"systemEnName", CnName":"systemCnName",
	 * "sysUrl":"http://localhost:8080/AppData/oa.json", "jsonroot":"obj",
	 * "jsondata":"data", "todomap":{ "receiveTime":"receiveTime",
	 * "PendingName":"pendingName", "todoUrl":"url", "title":"title"},
	 * "countmap": {"sysCount":"count", "sysListUrl":"pageUrl"}},
	 * "hr":{"EnName":"systemEnName","CnName":"systemCnName","sysUrl":
	 * "http://localhost:8080/AppData/hr.json","jsonroot":"obj","jsondata":
	 * "data","todomap":{"Title":"title","receiveTime":"receiveTime",
	 * "PendingName":"pendingName","todoUrl":"url"},"countmap":{"sysCount":
	 * "count","sysListUrl":"pageUrl"}},"pms111":{"EnName":"systemEnName",
	 * "CnName":"systemCnName","sysUrl":"http://localhost:8080/AppData/pms.json"
	 * ,"jsonroot":"obj","jsondata":"data","todomap":{"receiveTime":
	 * "pendingName","PendingName":"url","todoUrl":"title","title":"title"},
	 * "countmap":{"sysCount":"count","sysListUrl":"pageUrl"}}}
	 */
	public Map<String, Object> show() {
		ObjectMapper mapper = new ObjectMapper();
		List list = new ArrayList();

		String json = getRootString();

		Map map = null;
		try {
			map = (Map) mapper.readValue(json, HashMap.class);
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> detailInfo(String systemName) {
		Map sysmap = new HashMap();
		Map countmap = new HashMap();
		Map todomap = new HashMap();
		ObjectMapper mapper = new ObjectMapper();

		String json = getRootString();
		try {
			JsonNode root = mapper.readTree(json);
			Iterator keys = root.fieldNames();
			while (keys.hasNext()) {
				String fieldName = (String) keys.next();
				if (fieldName.equals(systemName)) {
					JsonNode chlidNode = root.path(fieldName);

					sysmap.put("EnName", chlidNode.path("EnName").asText());
					sysmap.put("CnName", chlidNode.path("CnName").asText());
					String sysUrl = chlidNode.path("sysUrl").asText();
					String jsonroot = chlidNode.path("jsonroot").asText();
					String jsondata = chlidNode.path("jsondata").asText();
					sysmap.put("sysUrl", sysUrl);
					sysmap.put("jsonroot", jsonroot);
					sysmap.put("jsondata", jsondata);
					JsonNode countNode = chlidNode.path("countmap");
					countmap.put("sysCount", countNode.path("sysCount").asText());
					countmap.put("sysListUrl", countNode.path("sysListUrl").asText());
					JsonNode todoNode = chlidNode.path("todomap");

					todomap.put("Title", todoNode.path("Title").asText());
					todomap.put("receiveTime", todoNode.path("receiveTime").asText());
					todomap.put("PendingName", todoNode.path("PendingName").asText());
					todomap.put("todoUrl", todoNode.path("todoUrl").asText());

					sysmap.put("todomap", todomap);
					sysmap.put("countmap", countmap);
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sysmap;
	}

	public Map<String, Object> daibandaiyue(String sysName, String sysUrl) {
		Map result = new HashMap();

		Map map = detailInfo(sysName);

		List list = new ArrayList();
		Map countmap = (Map) map.get("countmap");
		Map todomap = (Map) map.get("todomap");
		try {
			ObjectMapper mapper = new ObjectMapper();

			String sysjson = IOUtils.toString(new URL(sysUrl).openStream(), "UTF-8");

			JsonNode sysroot = mapper.readTree(sysjson);

			JsonNode jsonrootNode = sysroot.path(map.get("jsonroot").toString());
			SysName sn = new SysName();
			sn.setEnName((String) map.get("EnName"));
			sn.setCnName((String) map.get("CnName"));

			Count count = new Count();
			count.setCnName(sn.getCnName());
			count.setEnName(sn.getEnName());
			count.setSysCount(jsonrootNode.path(((String) countmap.get("sysCount")).toString()).asText());
			count.setSysListUrl(jsonrootNode.path(((String) countmap.get("sysListUrl")).toString()).asText());

			ToDo todo = new ToDo();
			JsonNode jsonNode = getChildTree(jsonrootNode, map.get("jsondata").toString());

			getMapToDo(jsonNode, todomap, sn, list);

			listSort(list);

			result.put("todo", list);
			result.put("count", count);
			result.put("total", Integer.valueOf(list.size()));
			result.put("state", "success");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			result.put("state", "failed");
		} catch (JsonMappingException e) {
			e.printStackTrace();
			result.put("state", "failed");
		} catch (IOException e) {
			e.printStackTrace();
			result.put("state", "failed");
		}
		return result;
	}

	public void listSort(List list) {
		Collections.sort(list, new Comparator<ToDo>() {
			public int compare(ToDo arg0, ToDo arg1) {

				int i = arg0.getReceiveTime().compareTo(arg1.getReceiveTime());
				if (i == 0) {
					int j = arg0.getPendingName().compareTo(arg1.getPendingName());
					return j;
				}
				return i;
			}
		});
	}

	public List<ToDo> getMapToDo(JsonNode jn, Map map, SysName sn, List<ToDo> td) {
		if (jn.isArray()) {
			for (JsonNode node : jn) {
				ToDo todo = new ToDo();
				todo.setEnName(sn.getEnName());
				todo.setCnName(sn.getCnName());
				todo.setTitle(node.path((String) map.get("Title")).asText());
				todo.setPendingName(node.path((String) map.get("receiveTime")).asText());
				todo.setReceiveTime(node.path((String) map.get("PendingName")).asText());
				todo.setTodoUrl(node.path((String) map.get("todoUrl")).asText());
				td.add(todo);
			}
		}
		return td;
	}

	public JsonNode getChildTree(JsonNode jn, String data) {
		if (jn == null)
			return null;
		JsonNode chlidNode = null;
		Iterator keys = jn.fieldNames();
		while (keys.hasNext()) {
			String fieldName = (String) keys.next();
			chlidNode = jn.path(fieldName);
			if ((fieldName.equals(data)) || (chlidNode.isArray()))
				return chlidNode;
		}
		return getChildTree(chlidNode, data);
	}

	public List<Object> openTodo2(String url) {
		List<Object> resultList = null;
		getMethod gett = new getMethod();
		String str = gett.getget(url);
		char firstChar =  str.charAt(0);
		if (firstChar == '(') {
			 str =  str.substring(1,  str.length() - 1);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			resultList = mapper.readValue(str, ArrayList.class); 
			// System.out.println(map2.get("title"));
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
		return resultList;
	}
	
	public List<Object> openTodo(String url) {
		// TODO Auto-generated method stub
		
		List<Object> resultList = null;
		
		Map<String, Object> map = new HashMap<String, Object>();

		int index = url.indexOf("?");
		String urlParam = url.substring(0, index);
		String mapParam = url.substring(index + 1, url.length());
		String[] strParam = mapParam.split("&");
		for (int i = 0; i < strParam.length; i++) {
			String param = strParam[i];
			String[] listParam = param.split("=");
			map.put(listParam[0], listParam[1]);
		}

	 

		// map.put("sql", "insert into Accounts
		// values('HRID','LOGIN','PASSWORD','FIRST_NAME','MIDDLE_NAME','LAST_NAME','TITLE','ROLE','EMAIL','STATUS')");
		// //map.put("pageSize", 10);
		PostMethod postMethod = new PostMethod();
		String result = postMethod.httpClientPost(urlParam, map, "utf-8");
		//String result = this.httpClientPost(urlParam, map, "utf-8");
		char firstChar = result.charAt(0);
		if (firstChar == '(') {
			result = result.substring(1, result.length() - 1);
		}
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "[{\"url\":\"http://pm.cmzd.cmhk.com/flowcontrol/detail_my1.asp?yhm=lijuan3&enc=&audi_sid=5994\",\"title\":\"测试平板送审[待处理]\"}, { \"url\":\"http://pm.cmzd.cmhk.com/flowcontrol/detail_my1.asp?yhm=lijuan3&enc=&audi_sid=5997\",\"title\":\"平板测试2送审[待处理]\"}]";
		
		try {
			resultList = mapper.readValue(json, ArrayList.class); 
			// System.out.println(map2.get("title"));
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
		return resultList;
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
