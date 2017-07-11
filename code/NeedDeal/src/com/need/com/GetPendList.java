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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetPendList
 */
public class GetPendList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		PendingNum pd=new PendingNum();
		pd.setAppID("houhuayu001");
		pd.setAuthenticator("X0dfa0309tyi99dd11abckdddeetqe");
		List<PendDetail> list=new ArrayList<PendDetail>();
		for (int i = 0; i < 1; i++) {
			PendDetail detail =new PendDetail();
			detail.setTitle("关于发布店地安置房小区");
			detail.setUrl("http://www.baidu.com");
			detail.setPendingid("pendingId"+i);
			detail.setReceiveTime("2015-12-19");
			detail.setModule("OA");
			detail.setCurrNode("张xx");
			list.add(detail);
			PendDetail detail2 =new PendDetail();
			detail2.setTitle("双鱼岛旅游码头项目");
			detail2.setUrl("http://www.baidu.com");
			detail2.setPendingid("pendingId"+i);
			detail2.setReceiveTime(df.format(new Date()));
			detail2.setModule("OA");
			detail2.setCurrNode("王XX");
			list.add(detail2);
			PendDetail detail3 =new PendDetail();
			detail3.setTitle("石坑社区田洋内整体拆迁");
			detail3.setUrl("http://www.baidu.com");
			detail3.setPendingid("pendingId"+i);
			detail3.setReceiveTime(df.format(new Date()));
			detail3.setModule("OA");
			detail3.setCurrNode("张xx");
			list.add(detail3);
			PendDetail detail4 =new PendDetail();
			detail4.setTitle("漳州开发区实验幼儿园多媒体");
			detail4.setUrl("http://www.baidu.com");
			detail4.setPendingid("pendingId"+i);
			detail4.setReceiveTime(df.format(new Date()));
			detail4.setModule("OA");
			detail4.setCurrNode("王XX");
			list.add(detail4);
		}
		pd.setResult("0");
		pd.setErrorDescription("");
		pd.setTimeStamp(df.format(new Date()));
		pd.setData(list);
		JSONObject jsonObject = new JSONObject();  
        jsonObject.put("obj", pd);  
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
