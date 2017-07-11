package com.ibm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @Description: 获取系统时间
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author houhuayu
	 * @date 2016年7月7日
	 */
	public static String getSystemTime(){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(new Date());
		return time;
	}

}
