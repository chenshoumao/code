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
public class GetHaveList extends HttpServlet {
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
			detail.setTitle("漳州开发区社发局防汛");
			detail.setUrl("http://www.baidu.com");
			detail.setPendingid("pendingId"+i);
			detail.setReceiveTime(df.format(new Date()));
			detail.setModule("OA");
			detail.setCurrNode("王XX");
			list.add(detail);
			PendDetail detail2 =new PendDetail();
			detail2.setTitle("建设项目公示");
			detail2.setUrl("http://www.baidu.com");
			detail2.setPendingid("pendingId"+i);
			detail2.setReceiveTime(df.format(new Date()));
			detail2.setModule("HR");
			detail2.setCurrNode("王XX");
			list.add(detail2);
			PendDetail detail3 =new PendDetail();
			detail3.setTitle("境保护设施竣工验收");
			detail3.setUrl("http://www.baidu.com");
			detail3.setPendingid("pendingId"+i);
			detail3.setReceiveTime(df.format(new Date()));
			detail3.setModule("OA");
			detail3.setCurrNode("王XX");
			list.add(detail3);
			PendDetail detail4 =new PendDetail();
			detail4.setTitle("设施工程答疑纪要");
			detail4.setUrl("http://www.baidu.com");
			detail4.setPendingid("pendingId"+i);
			detail4.setReceiveTime(df.format(new Date()));
			detail4.setModule("HR");
			detail4.setCurrNode("张XX");
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
