package com.solar.tech.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Constant {

	/**
	 * 男性
	 */
	public static final int GENDER_MALE = 1;
	
	/**
	 * 女性
	 */
	public static final int GENDER_FEMALE = 2;
	
	/**
	 * 默认每页显示多少条记录
	 */
	public static final int DEFAULT_PAGE_SIZE = 5;
	/*
	 * 默认每页显示9条记录
	 */
	public static final int DEFAULT_PAGE_SIZE_FIRST = 6;
	/*
	 * 默认每页显示20条记录
	 */
	public static final int DEFAULT_PAGE_SIZE_MORE = 20;
	/**
	 * 默认显示第几页记录
	 */
	public static final int DEFAULT_PAGE_NUM = 1;
	
	/**
	 * 默认学生性别
	 */
	public static final int DEFAULT_GENDER = 0;
	
	/**
	 * 
	 * @Description: 读取xml，上传文件
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-21
	 */
	public static String upUrl(String urlpath){
		String upUrl = "";
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	        try  
	        {
	            DocumentBuilder db = dbf.newDocumentBuilder(); 
	            Document doc = db.parse(urlpath+"/imageConfig.xml");  
	            NodeList ImagesList = doc.getElementsByTagName("image");  
	            for (int i = 0; i < ImagesList.getLength(); i++)  
	            {  
	                Node image = ImagesList.item(i);  
//	                Element elem = (Element) image;  
	                upUrl= image.getFirstChild().getNodeName();
	                for (Node node = image.getFirstChild(); node != null; node = node.getNextSibling())  
	                {  
	                	System.out.println(node.getNodeType());
	                    if (node.getNodeType() == Node.ELEMENT_NODE)  
	                    {  
	                         upUrl = node.getFirstChild().getNodeValue(); 
	                         return upUrl;
	                    }  
	                }  
	            }  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
		return upUrl;
	}
	
	
	/**
	 * 
	 * @Description: 读取所有上传文件目录
	 * @param @param urlpath
	 * @param @return   
	 * @return String  
	 * @throws ParserConfigurationException 
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-24
	 */
	
//	public static void main(String[] args) throws ParserConfigurationException {
//		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
//	        try  
//	        {
//	            DocumentBuilder db = dbf.newDocumentBuilder(); 
//	            Document doc = db.parse("/root/IBM/rationalsdp/workspace/photo/src/imageConfig.xml");
//	            NodeList ImagesList = doc.getElementsByTagName("image");  
//	            System.out.println(ImagesList.getLength());
//	            for (int i = 0; i < ImagesList.getLength(); i++)  
//	            {  
//	            	
//	            	System.out.println(i);
//	                Node image = ImagesList.item(i);  
////	                Element elem = (Element) image;  
//	              String  upUrl= image.getFirstChild().getNodeName();
//	                System.out.println("upUrl : " + upUrl);
//	                for (Node node = image.getFirstChild(); node != null; node = node.getNextSibling())  
//	                {  
//	                	System.out.println(node.getNodeType());
//	                 
//	                    if (node.getNodeType() == Node.ELEMENT_NODE)  
//	                    {  
//	                         upUrl = node.getFirstChild().getNodeValue(); 
//	                         
//	                         System.out.println("now upUrl : " + upUrl);
//	                    }  
//	                }  
//	            }  
//	        }  
//	        catch (Exception e)  
//	        {  
//	            e.printStackTrace();  
//	        }  
//	}
	
	public static void main(String[] args) {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
+ "<image>"
+ "<imageurl>upimg1</imageurl>"
+ "<imgageurl>upimg2</imgageurl>"
+ "<imageurl>upimg3</imageurl>"
+ "<imgageurl>upimg4</imgageurl>"
+ "</image>";
		
		  
	}
	
	
	public static List<String> urlList(String urlpath){
		List<String> urlList = new ArrayList<String>();
		String upUrl = "";
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	        try  
	        {
	            DocumentBuilder db = dbf.newDocumentBuilder(); 
	            Document doc = db.parse(urlpath+"/imageConfig.xml");
	            NodeList ImagesList = doc.getElementsByTagName("image");  
	            for (int i = 0; i < ImagesList.getLength(); i++)  
	            {  
	            	System.out.println(i);
	                Node image = ImagesList.item(i);  
//	                Element elem = (Element) image;  
	                upUrl= image.getFirstChild().getNodeName();
	                System.out.println("upUrl : " + upUrl);
	                for (Node node = image.getFirstChild(); node != null; node = node.getNextSibling())  
	                {  
	                	System.out.println(node.getNodeType());
	                	System.out.println(Node.ELEMENT_NODE);
	                    if (node.getNodeType() == Node.ELEMENT_NODE)  
	                    {  
	                         upUrl = node.getFirstChild().getNodeValue(); 
	                         urlList.add(upUrl);
	                         System.out.println("now upUrl : " + upUrl);
	                    }  
	                }  
	            }  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
		return urlList;
	}
}
