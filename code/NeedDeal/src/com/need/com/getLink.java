package com.need.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.need.entity.Link;
import com.need.entity.LinkNum;
import com.need.entity.PendDetail;

import net.sf.json.JSONObject;

public class getLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkNum pd=new LinkNum();
		List<PendDetail> list=new ArrayList<PendDetail>();
		for (int i = 0; i < 10; i++) {
			PendDetail detail =new PendDetail();
			detail.setTitle("王XX会议"+i);
			detail.setUrl("http://www.baidu.com");
			detail.setPendingid("pendingId"+i);
			detail.setReceiveTime(df.format(new Date()));
			detail.setModule("OA系统"+i);
			detail.setCurrNode("hellokity");
			list.add(detail);
		}
		Map<String,List<Link>> map = new HashMap<String,List<Link>>();
		List listData = new ArrayList();
		
		
		List<Link> list_one = new ArrayList<Link>();
		List<Link> list_two = new ArrayList<Link>();
		List<Link> list_three = new ArrayList<Link>();
		List<Link> list_four = new ArrayList<Link>();
		List<Link> list_five = new ArrayList<Link>();
		//招商旗舰
		for(int i=0;i<1;i++){
			Link link1 = new Link();
			link1.setName("招商局国际");
			link1.setUrl("http://www.cmhi.com.hk/");
			list_one.add(link1);
			
			Link link2 = new Link();
			link2.setName("金融集团");
			link2.setUrl("http://www.cmfhk.com/");
			list_one.add(link2);
			
			Link link3 = new Link();
			link3.setName("招商轮船");
			link3.setUrl("http://www.cmenergyshipping.com/");
			list_one.add(link3);
			
			Link link4 = new Link();
			link4.setName("南山集团");
			link4.setUrl("http://www.cndi.com/");
			list_one.add(link4);
			
			Link link5 = new Link();
			link5.setName("蛇口集装箱码头");
			link5.setUrl("http://www.sctcn.com/");
			list_one.add(link5);
			
			Link link6 = new Link();
			link6.setName("华北高速");
			link6.setUrl("http://www.hbgsgl.com.cn/");
			list_one.add(link6);
			
			Link link7 = new Link();
			link7.setName("中集集团");
			link7.setUrl("http://www.cimc.com/");
			list_one.add(link7);
			
			Link link8 = new Link();
			link8.setName("招商局国际");
			link8.setUrl("http://www.cmhi.com.hk/");
			list_one.add(link8);
			
			Link link9 = new Link();
			link9.setName("招商地产");
			link9.setUrl("http://www.cmpd.cn/web/index.aspx");
			list_one.add(link9);
			
			Link link10 = new Link();
			link10.setName("招商证券");
			link10.setUrl("http://www.newone.com.cn/");
			list_one.add(link10);
			
			Link link11 = new Link();
			link11.setName("招商银行");
			link11.setUrl("http://www.cmbchina.com/");
			list_one.add(link11);
			
			Link link12 = new Link();
			link12.setName("招商局物流集团");
			link12.setUrl("http://www.cml-1872.com/");
			list_one.add(link12);
			
			Link link13 = new Link();
			link13.setName("蛇口工业区");
			link13.setUrl("http://skiz.shekou.com/");
			list_one.add(link13);
			
			Link link14 = new Link();
			link14.setName("工业集团");
			link14.setUrl("http://www.cmindustry.com.hk/");
			list_one.add(link14);
			
			Link link15 = new Link();
			link15.setName("香港海通");
			link15.setUrl("http://www.hoitung.com.hk/cn/Content/index.html");
			list_one.add(link15);
		   }
		
		//区内单位
		for(int i=0;i<1;i++){
			Link link1 = new Link();
			link1.setName("厦门大学附属实验中学");
			link1.setUrl("http://www.xiadafz.com/Index.html");
			list_two.add(link1);
			
			Link link2 = new Link();
			link2.setName("漳州开发区综合行政执法局");
			link2.setUrl("http://cmzdzf.com/");
			list_two.add(link2);
			
			Link link3 = new Link();
			link3.setName("漳州开发区政府采购网");
			link3.setUrl("http://zzzs.fjzfcg.gov.cn/n/zzzfcg/index.do");
			list_two.add(link3);
			
			Link link4 = new Link();
			link4.setName("厦门大学漳州校区");
			link4.setUrl("http://zzxq.xmu.edu.cn/");
			list_two.add(link4);
			
			Link link5 = new Link();
			link5.setName("厦门大学嘉庚学院");
			link5.setUrl("http://jgxy.xmu.edu.cn/index.php");
			list_two.add(link5);
		}
		//政府部门
		for(int i=0;i<1;i++){
			Link link1 = new Link();
			link1.setName("国资委");
			link1.setUrl("http://www.sasac.gov.cn/");
			list_three.add(link1);
			
			Link link2 = new Link();
			link2.setName("福建省政府");
			link2.setUrl("http://www.fujian.gov.cn/");
			list_three.add(link2);
			
			Link link3 = new Link();
			link3.setName("国家交通运输部");
			link3.setUrl("http://www.moc.gov.cn/");
			list_three.add(link3);
			
			Link link4 = new Link();
			link4.setName("福建省交通厅");
			link4.setUrl("http://www.fjjt.gov.cn");
			list_three.add(link4);
			
			Link link5 = new Link();
			link5.setName("厦门市政府");
			link5.setUrl("http://www.xm.gov.cn/");
			list_three.add(link5);
			
			Link link6 = new Link();
			link6.setName("龙海市政府");
			link6.setUrl("http://www.longhai.gov.cn/");
			list_three.add(link6);
			
			Link link7 = new Link();
			link7.setName("福州市政府");
			link7.setUrl("http://www.fuzhou.gov.cn/");
			list_three.add(link7);
			
			Link link8 = new Link();
			link8.setName("泉州市政府");
			link8.setUrl("http://www.fjqz.gov.cn/");
			list_three.add(link8);
			
			Link link9 = new Link();
			link9.setName("莆田市政府");
			link9.setUrl("http://www.putian.gov.cn/");
			list_three.add(link9);
			
			Link link10 = new Link();
			link10.setName("三明市政府");
			link10.setUrl("http://www.sm.gov.cn/");
			list_three.add(link10);
			
			Link link11 = new Link();
			link11.setName("南平市政府");
			link11.setUrl("http://www.np.gov.cn/");
			list_three.add(link11);
			
			Link link12 = new Link();
			link12.setName("龙岩市政府");
			link12.setUrl("http://www.longyan.gov.cn/");
			list_three.add(link12);
			
			Link link13 = new Link();
			link13.setName("宁德市政府");
			link13.setUrl("http://www.ningde.gov.cn/");
			list_three.add(link13);
			
			Link link14 = new Link();
			link14.setName("教育部");
			link14.setUrl("http://www.moe.edu.cn/");
			list_three.add(link14);
			
			Link link15 = new Link();
			link15.setName("发展改革委");
			link15.setUrl("http://www.ndrc.gov.cn/");
			list_three.add(link15);
			
			Link link16 = new Link();
			link16.setName("国土资源部");
			link16.setUrl("http://www.mlr.gov.cn/");
			list_three.add(link16);
			
			Link link17 = new Link();
			link17.setName("工商总局");
			link17.setUrl("http://www.saic.gov.cn/");
			list_three.add(link17);
			
			Link link18 = new Link();
			link18.setName("审计署");
			link18.setUrl("http://www.audit.gov.cn/");
			list_three.add(link18);
			
			Link link19 = new Link();
			link19.setName("税务总局");
			link19.setUrl("http://www.chinatax.gov.cn/");
			list_three.add(link19);
			
			Link link20 = new Link();
			link20.setName("银监会");
			link20.setUrl("http://www.cbrc.gov.cn/");
			list_three.add(link20);
			
			Link link21 = new Link();
			link21.setName("证监会");
			link21.setUrl("http://www.csrc.gov.cn/");
			list_three.add(link21);
			
			Link link22 = new Link();
			link22.setName("漳州市政府");
			link22.setUrl("http://www.zhangzhou.gov.cn/");
			list_three.add(link22);
		}
		//港行物流
		for(int i=0;i<1;i++){
			Link link1 = new Link();
			link1.setName("中国海事局");
			link1.setUrl("http://www.msa.gov.cn/");
			list_four.add(link1);
			
			Link link2 = new Link();
			link2.setName("航运交易所");
			link2.setUrl("http://www.sse.net.cn/");
			list_four.add(link2);
			
			Link link3 = new Link();
			link3.setName("海事仲裁委员会");
			link3.setUrl("http://www.cmac.org.cn/");
			list_four.add(link3);
			
			Link link4 = new Link();
			link4.setName("中国航海学会");
			link4.setUrl("http://www.cinnet.cn/");
			list_four.add(link4);
		}
		
		for(int i=0;i<1;i++){
			Link link1 = new Link();
			link1.setName("漳州港客船航班");
			link1.setUrl("http://www.cmzd.com/NewsDetail.aspx?id=11352");
			list_five.add(link1);
			
			Link link2 = new Link();
			link2.setName("漳州港论坛");
			link2.setUrl("http://www.zzport.com/");
			list_five.add(link2);
		}
		
		Map mapLinkOne = new HashMap();
		mapLinkOne.put("title", "招商旗舰");
		mapLinkOne.put("links", list_one);
		Map mapLinkTwo = new HashMap();
		mapLinkTwo.put("title", "区类单位");
		mapLinkTwo.put("links", list_two);
		Map mapLinkThree = new HashMap();
		mapLinkThree.put("title", "政府部门");
		mapLinkThree.put("links", list_three);
		Map mapLinkFour = new HashMap();
		mapLinkFour.put("title", "港行物流");
		mapLinkFour.put("links", list_four);
		Map mapLinkFive = new HashMap();
		mapLinkFive.put("title", "其他链接");
		mapLinkFive.put("links", list_five);
		
		listData.add(mapLinkOne);
		listData.add(mapLinkTwo);
		listData.add(mapLinkThree);
		listData.add(mapLinkFour);
		listData.add(mapLinkFive);
		pd.setData(listData);
		pd.setResult("0");
		pd.setErrorDescription("");
		pd.setTimeStamp(df.format(new Date()));
		JSONObject jsonObject = new JSONObject();  
        jsonObject.put("obj", pd);  
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
