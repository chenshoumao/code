package com.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {
	 private String encoding;  
	    private Map<String, String> params = new HashMap<String, String>(); 
	public void destroy() {
	        params=null;  
	        encoding=null;  

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) {
		//UtilTimerStack.push("EncodingFilter_doFilter:");  
//        System.out.println("before encoding " + encoding + " filter！");  
        try {
			req.setCharacterEncoding(encoding);
			resp.setContentType("text/html;charset="+encoding);  //设置响应编码和页面编码
			chain.doFilter(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        //只针对post有效
//         resp.setCharacterEncoding(encoding); 
//         resp.setCharacterEncoding("text/html");
       
                
        //UtilTimerStack.pop("EncodingFilter_doFilter:");  

	}
   /**
    * 初始化拦截器
    */
	public void init(FilterConfig config) throws ServletException {
			//获取编码
	        encoding = config.getInitParameter("encoding");
	        
//	        for (Enumeration e = config.getInitParameterNames(); e  
//	                .hasMoreElements();) {  
//	            String name = (String) e.nextElement();  
//	            String value = config.getInitParameter(name);  
//	            params.put(name, value);  
//	        }  

	}

}
