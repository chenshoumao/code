package com.ibm.utils;
/**
 * 
 * @Description: 请求url
 * @author houhuayu
 * @date 2016年7月7日
 */
public class HttpUrl {
	//服务器地址
//	public static final String URL            = "http://10.161.2.72:10039/"; 
//	public static final String URL            = "http://192.168.199.110:10039/"; 
//	public static final String URL            = "http://portal01.ibm.com:10039/";
//	public static final String URL            = "http://127.0.0.1:8080/"; 
	//数量接口
	public static final String PENDINGURL     = "NeedDeal/getPendingNum";
	//获取待办列表接口
	public static final String PENDLISTURL    = "NeedDeal/getPendList";
	//获取已办列表接口
	public static final String HAVELISTURL    = "NeedDeal/getHaveList";
	//获取会议列表接口
	public static final String MEETINGLISTURL = "NeedDeal/getMeetingList";
	//获取链接接口
//	public static final String LINKSURL       ="http://localhost:8080/NeedDeal/getLink";
	public static final String LINKSURL       ="NeedDeal/getLink";

}
