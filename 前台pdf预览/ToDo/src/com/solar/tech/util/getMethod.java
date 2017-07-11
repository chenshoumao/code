package com.solar.tech.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class getMethod {
	public static void main(String[] args) {
		String updateUrl = "yhm=lijuan3";
	//	updateUrl = updateUrl.replace(" ", "%20");
		getget("http://10.160.1.2:81/getjson.asp?yhm=lijuan3");
	}
	
	public static String getget(String str){
		String updateUrl = "http://10.160.1.2:81/getjson.asp?yhm=lijuan3";
	//	updateUrl = updateUrl.replace(" ", "%20");
		System.out.println(updateUrl);
		URLConnection con;
		
		String result = "";
		BufferedReader in = null;
		
		try {
			con = new URL(updateUrl).openConnection();

			System.out.println("dataurl-----:" + updateUrl);

			 con.setRequestProperty("accept", "*/*");  
	            con.setRequestProperty("connection", "Keep-Alive");  
	            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            // 发送POST请求必须设置如下两行  
	          //  con.setDoOutput(true);  
	          //  con.setDoInput(true);  
			System.out.println(11111);
			con.connect();
			System.out.println(22222);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 

public static void SendGET(String url,String param){
	   String result="";//访问返回结果
	   BufferedReader read=null;//读取访问结果
	   // http://10.161.2.68:9080/jndiT/servlet/servletCon?name=educateJndi&sql=INSERT INTO PL_USERS(name,title,password) VALUES ('test6','fds','0192023a7bbd73250516f069df18b500')
	   try {
	    //创建url
	    URL realurl=new URL(url+"?"+param);
	    //打开连接
	    URLConnection connection=realurl.openConnection();
	     // 设置通用的请求属性
	             connection.setRequestProperty("accept", "*/*");
	             connection.setRequestProperty("connection", "Keep-Alive");
	             connection.setRequestProperty("user-agent",
	                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	             //建立连接
	             connection.connect();
	          // 获取所有响应头字段
	             Map<String, List<String>> map = connection.getHeaderFields();
	             // 遍历所有的响应头字段，获取到cookies等
	             for (String key : map.keySet()) {
	                 System.out.println(key + "--->" + map.get(key));
	             }
	             // 定义 BufferedReader输入流来读取URL的响应
	             read = new BufferedReader(new InputStreamReader(
	                     connection.getInputStream(),"UTF-8"));
	             String line;//循环读取
	             while ((line = read.readLine()) != null) {
	                 result += line;
	             }
	   } catch (IOException e) {
	    e.printStackTrace();
	   }finally{
	    if(read!=null){//关闭流
	     try {
	      read.close();
	     } catch (IOException e) {
	      e.printStackTrace();
	     }
	    }
	   }
	     
	   
	 }
}
