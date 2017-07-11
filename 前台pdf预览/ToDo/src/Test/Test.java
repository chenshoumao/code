package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Test {
	public static void main(String[] args) {
		String result = getInfo();
		System.out.println(result);
	}
	public static String SendGET(){
		String url = "http://10.161.2.72:10039/AppData/PersonalSpace/show.action";
		   String result="";//访问返回结果
		   BufferedReader read=null;//读取访问结果
		   // http://10.161.2.68:9080/jndiT/servlet/servletCon?name=educateJndi&sql=INSERT INTO PL_USERS(name,title,password) VALUES ('test6','fds','0192023a7bbd73250516f069df18b500')
		   try {
		    //创建url
		    URL realurl=new URL(url);
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
		             System.out.println(result);
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
		     
		   return result;
		 }
	
	public static String getget(){
		String updateUrl = "http://10.161.2.72:10039/AppData/PersonalSpace/show.action";
		updateUrl = updateUrl.replace(" ", "%20");
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getInfo() {
		String dataurl = "http://10.161.2.72:10039/AppData/PersonalSpace/show.action";
		Map<String, Object> map = new HashMap<String, Object>();
		 
		System.out.println("参数map是……………………………………………………^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+map);
		try {
			String result = httpClientPost(dataurl, map, "UTF-8");
			return result;
		} catch (Exception e) {
			return "error";
		}
	}
	
	public static   String httpClientPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        DefaultHttpClient client = null; 
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
            System.out.println("client 初始化前………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            client = new DefaultHttpClient();
            System.out.println("client 初始化后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            HttpResponse response = client.execute(httpPost); 
            System.out.println("响应后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
            // 读取服务器响应数据  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }
            System.out.println("读取数据后………………………………………………………………………………………………………………………………^^^^^^^^^^^^^^");
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
        return resultBuffer.toString();  
    }  
}
