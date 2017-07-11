package com.need.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpMethod;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetPendingNum
 */
public class GetPendingNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String appID = request.getParameter("appID");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		if(type.equals("pend")){//待办
			jsonObject.put("num", "7");
			jsonObject.put("code", "1");
			out.print(jsonObject.toString());
		}
		if(type.equals("have")){//已办
			jsonObject.put("num", "6");
			jsonObject.put("code", "1");
			out.print(jsonObject.toString());
			
		}
		if(type.equals("meeting")){//会议
			jsonObject.put("num", "4");
			jsonObject.put("code", "1");
			out.print(jsonObject.toString());
		}
	}

}
