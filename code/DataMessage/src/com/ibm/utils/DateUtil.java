package com.ibm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @Description: ��ȡϵͳʱ��
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author houhuayu
	 * @date 2016��7��7��
	 */
	public static String getSystemTime(){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(new Date());
		return time;
	}

}
