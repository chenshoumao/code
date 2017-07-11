package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * 1.根据网址和页面编码集抓取网页源代码
 * 2.解析源代码
 * 3.通过条件语句筛选信息
 * */
public class Test {
	
	
	
	public static void main(String[] args) {
		//System.out.println("");
		String url="http://www.qq.com/";
		String encoding="gb2312";
		//1.根据网络和页面的编码集 抓取网页的源代码
		String htmlResouce=GetHtmlResouceByURL(url, encoding);
		//System.out.println(htmlResouce);
		
		//2.解析网页的源代码 jsoup jar包
		Document document = Jsoup.parse(htmlResouce);
		
		//ex：抓取图片例子 图片 标签<img src=" " alt=" " width=" " height=" ">
		Elements elements=document.getElementsByTag("img");
		for(Element element : elements) {
			String imgSrc=element.attr("src"); //获取src属性的值
			System.out.println(imgSrc);
			//下载到本地文件夹中
			//downImgs(imgSrc, "D:\\nouse");
		}
		
		//3.通过调剂语句进行筛选信息
		
	}
	/**
	 * 根据网址和页面的编码集 抓取网页
	 * @param url 网址
	 * @param encoding 网页的编码集
	 * @return 源代码
	 * 
	 * */
	public static String GetHtmlResouceByURL(String url,String encoding){
		
		// 建立容器存储网页源代码
		StringBuffer buffer=new StringBuffer();
		URL urlobj=null;
		URLConnection uc =null;
		InputStreamReader isr=null;
		BufferedReader input=null;
		try {
			//建立网络连接
			urlobj =new URL(url);
			//打开网络连接
			uc = urlobj.openConnection();
			//建立网络输入流
			isr=new InputStreamReader(uc.getInputStream(),encoding);
			//建立缓冲流读输入的数据
			input=new BufferedReader(isr);
			
			//循环遍历数据
			String line=null;
			while((line=input.readLine())!=null){
				//添加换行
				buffer.append(line+"\n");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.println("连接源代码失败");
		}finally{
			try {
				if(isr!=null)
					isr.close();
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("关闭失败");				
			}
		}
		
		return buffer.toString();
	}
	/**
	 *  下载文件夹到本地
	 * @param imgsrc 图片地址
	 * @param filepath 存储图片地址
	 * 
	 * */
	public static void downImgs(String imgsrc,String filePath){
		

		//获取图片
		try {
			//创建目录
			File files = new File(filePath);
			if(!files.exists()){
				files.mkdirs();
			}
			URL url = new URL(imgsrc);
			//连接网络图片地址
			HttpURLConnection uc=(HttpURLConnection) url.openConnection();
			//获取连接的输入流
			InputStream is=uc.getInputStream();
			
			//截取文件名
			//String filename=imgsrc.substring(imgsrc.lastIndexOf("/"));
			
			//创建文件
			File file=new File(filePath+imgsrc.substring(imgsrc.lastIndexOf("/")));
			//输入到本地
			FileOutputStream fos=new FileOutputStream(file);
			int line=-1;
			while((line=is.read())!=-1){
				fos.write(line);
			}
			is.close();
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}