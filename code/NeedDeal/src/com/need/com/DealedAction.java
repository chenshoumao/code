package com.need.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.need.entity.PendDetail;
import com.need.entity.PendingNum;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/***
 * 已办数据接口的Servlet
 * @author admin
 * @date 2016-05-12
 *
 */
public class DealedAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DealedAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		PendingNum pd=new PendingNum();
		pd.setAppID("simon0001");
		pd.setAuthenticator("X0dfa0309tyi99dd11abckdddeetqe");
		List<PendDetail> list=new ArrayList<PendDetail>();
		for (int i = 0; i < 10; i++) {
			PendDetail detail =new PendDetail();
			detail.setTitle("已办test"+i);
			detail.setUrl("http://www.baidu.com");
			detail.setPendingid("pendingId"+i);
			detail.setReceiveTime(df.format(new Date()));
			detail.setModule("通知公告"+i);
			list.add(detail);
			
		}
		pd.setResult("0");
		pd.setErrorDescription("");
		pd.setTimeStamp(df.format(new Date()));
		pd.setData(list);
		JSONObject jsonObject = new JSONObject();  
        jsonObject.put("obj", pd);  
          
        JSONArray jsonArray = new JSONArray();  
        jsonArray.add(jsonObject);  
        System.out.println(jsonArray);  
		PrintWriter out = response.getWriter();
		out.write(jsonArray.toString());  
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
