package com.solar.tech.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solar.tech.bean.ToDo;

@Repository
public class ToDoDao {
	static String ENNAME = null;
	static String CNNAME = null;
	static String ROOTNODE = null;
	static String DATANODE = null;
	static String TITLE = null;
	static String RECEIVETIME = null;
	static String PENGDINGTIME = null;
	static String TODOURL = null;
	static String COUNT = null;
	 
	public List show(int perPage ,int curPage) { 
		List returnList = new ArrayList();
		List list = new ArrayList(); 
		ObjectMapper mapper = new ObjectMapper(); 
		String json = getRootString(); 
		Map map = null;
		try {
			map = (Map) mapper.readValue(json, HashMap.class);
			for (Object value : map.values()) {
				System.out.println("Value = " + value);
				Map<String, Object> leftMap = (Map<String, Object>) value;
				ROOTNODE = leftMap.get("jsonroot").toString();
				DATANODE = leftMap.get("jsondata").toString();
				ENNAME = leftMap.get("EnName").toString();
				CNNAME = leftMap.get("CnName").toString();
				COUNT = ((Map)leftMap.get("countmap")).get("sysCount").toString();
				ToDo todo = mapper.convertValue(leftMap.get("todomap"), ToDo.class);

				TITLE = todo.getTitle();
				RECEIVETIME = todo.getReceiveTime();
				PENGDINGTIME = todo.getPendingName();
				TODOURL = todo.getTodoUrl();
				
				String sysUrl = (String) leftMap.get("sysUrl");
				list.addAll(parseData(sysUrl, mapper));
			}
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		System.out.println(list.size());
		Collections.sort(list);  //降序 
		returnList = ListSplitPage(list,curPage,perPage);//根据分页获取对应区间的数据
		return returnList;
	}
	
	//根据分页获取对应区间的数据
	public List ListSplitPage(List list,int curPage,int perPage){
		List returnList = new ArrayList();
		for(int i = 0;i < perPage;i++){
			returnList.add(list.get((curPage-1)* perPage + i));
		}
		return returnList;
	} 
	 
	public List parseData(String sysUrl, ObjectMapper mapper) {
		List list = new ArrayList();
		URL u;
		String result = "";
		try {
			u = new URL(sysUrl);

			URLConnection urlconn;

			urlconn = u.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String line = null;

			while (null != (line = br.readLine())) {
				result += line;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> rightMap = null;
		try {
			rightMap = (Map) mapper.readValue(result, HashMap.class);
			if (ROOTNODE != null) {
				if (DATANODE != null) {
					Map<String, Object> rightData = (Map<String, Object>) rightMap.get(ROOTNODE);
					List todoData = (ArrayList) rightData.get(DATANODE);
					int count = Integer.valueOf(rightData.get(COUNT).toString());
					for (int i = 0; i < count; i++) {
						ToDo todoBean = new ToDo();
						todoBean.setEnName(rightData.get(ENNAME).toString());
						todoBean.setCnName(rightData.get(CNNAME).toString());
						todoBean.setTitle(((Map<String, Object>) todoData.get(i)).get(TITLE).toString());
						todoBean.setTodoUrl(((Map<String, Object>) todoData.get(i)).get(TODOURL).toString());
						todoBean.setReceiveTime(((Map<String, Object>) todoData.get(i)).get(RECEIVETIME).toString());
						todoBean.setPendingName(((Map<String, Object>) todoData.get(i)).get(PENGDINGTIME).toString());
						list.add(todoBean);
					}

				}
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	public Map<String, Object> download(String fileUrl,String filename,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		 response.setContentType("application/x-msdownload");
		 File file=new File(fileUrl);
		 response.setContentLength((int)file.length()); 
	     response.setCharacterEncoding("UTF-8");
	     
	     
	     String fileName = new String(filename.getBytes(), "ISO-8859-1"); 
	     //设置文件拓展名
	     response.setHeader("Content-Disposition","attachment;filename="+fileName) ;
	     response.setHeader("Content-Type","application/octet-stream");
	     FileInputStream fis; 	
			try {
				fis = new FileInputStream(file);
			
		        BufferedInputStream buff=new BufferedInputStream(fis);

		        byte [] b=new byte[1024];//相当于我们的缓存

		        long k=0;//该值用于计算当前实际下载了多少字节
		       
				OutputStream myout=response.getOutputStream();
				 
				 //开始循环下载

		        while(k<file.length()){

		            int j=buff.read(b,0,1024);
		            k+=j;

		            //将b中的数据写到客户端的内存
		            myout.write(b,0,j);
		            myout.flush();

		        }

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			map.put("state", true);
		return map;
	}

	 

	public static String getRootString() {
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
}
