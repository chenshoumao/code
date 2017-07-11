package com.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	  private static String urlPath="http://127.0.0.1:8080/MyAgent/test";
	  
	  public static String strUrl(String urlPath,Parm parm){
		  
		  return "";
	  }
	/**
	 * @Description: TODO
	 * @param @param args   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-28
	 */
	public static void main(String[] args) throws IOException {
		 //ServerFactory.getServer(8080).start();
	    //列出原始数据
	    StringBuilder json = new StringBuilder();   
	    Parm parm = new Parm();
	    parm.setId("T1");
	    parm.setDepartment("teacher");
	    
	    URL oracle = new URL(Test.strUrl(urlPath,parm));
	    URLConnection yc = oracle.openConnection(); 
	    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
	    String inputLine = null; 
	    while ( (inputLine = in.readLine()) != null){ 
	      json.append(inputLine); 
	    } 
	    in.close(); 
	    String Strjson=json.toString();
	    System.out.println("原始数据:");
	    System.out.println(Strjson.toString()); 


	}

}
