package com.ibm.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.ibm.entity.Collect;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


/**
 * 
 * 功能描述：此类为处理JSON文件、JSON类型字符串以及java集合与json数组类型之间的转捄1�7
 *
 * 类名：JSONFile
 *
 * Version info 版本号：V1.0 
 * © Copyright 续日科技 2016幄1�7�1�7�1�7 版权扄1�7朄1�7
 */
public class JSONFile {
	
	/**
	 * 
	 * 功能描述：此方法将字符串 fileContent 写入到路径为 filePathAndName 的文件中
	 * 
	 * @param filePathAndName
	 * @param fileContent
	 *
	 */
	public static void writeFile(String filePathAndName, String fileContent) { 
		try { 
		   File f = new File(filePathAndName); 
		   if (!f.exists()) { 
			   f.createNewFile(); 
		   } 
		   OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8"); 
		   BufferedWriter writer=new BufferedWriter(write);   
		   //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePathAndName))); 
		   //PrintWriter writer = new PrintWriter(new FileWriter(filePathAndName)); 
		   writer.write(fileContent); 
		   writer.close(); 
		} catch (Exception e) { 
		   System.out.println("写文件内容操作出锄1�7"); 
		   e.printStackTrace(); 
		} 
	} 
	
	/**
	 * 
	 * 功能描述：此方法读取路径丄1�7 path 的文件，并返囄1�7 字符丄1�7
	 * 
	 * @param path
	 * @return String
	 * @throws IOException
	 *
	 */
	public static String readFile(String path) throws IOException {  
		File file = new File(path);  
        InputStreamReader read = null;  
        String laststr = "";
        if(!file.exists()){
	        	file.createNewFile();
        }
        try {  
            read = new InputStreamReader(new FileInputStream(file),"UTF-8"); 
            BufferedReader reader =new BufferedReader(read); 
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr = laststr + tempString;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (read != null) {  
                try {  
                	read.close();  
                } catch (IOException e1) {  
                	e1.printStackTrace();
                }  
            }  
        }  
      
        return laststr;
	}
	
	  /**
	   *  
	   * 功能描述：此方法将字符串jsons转换成存放clazz类型对象的集合中，并返回该集各1�7
	   * 
	   * @param clazz
	   * @param jsons
	   * @return List<T>
	   */ 
	  public static <T> List<T> getJavaCollection(T clazz, String jsons) {
	        List<T> objs=null;
	        JSONArray jsonArray;
	        try {
		        jsonArray=JSONArray.fromObject(jsons);
		        if(jsonArray!=null){
		            objs=new ArrayList<T>();
		            List list=(List)JSONSerializer.toJava(jsonArray);
		            for(Object o:list){
		                JSONObject jsonObject=JSONObject.fromObject(o);
						T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
		                objs.add(obj);
		            }
		        }
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return objs;
	    }
	
	/**
	 * 
	 * 功能描述：此方法生成丄1�7个字符串类型的ID，并返回此ID
	 * 
	 * @return String
	 */
	public static String generateID(){
		Date current = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
		"yyMMddHHmmss");
		String time = sdf.format(current);
		return time;
	}
	
}	  
  
