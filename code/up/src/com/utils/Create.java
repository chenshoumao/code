package com.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 
 * ClassName: Create 
 * @Description: TODO
 * @author houhuayu
 * @date 2016-6-24
 */
public class Create extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String savePath = "H:/ok3/c/3/43/3";
		  File f1 = new File(savePath);
	        System.out.println(savePath);
	        if (!f1.exists()) {
	            f1.mkdirs();
	        }
	        String urlpath = this.getServletContext().getRealPath("/config");//配置上传图片路径的配置文件路径
	    	String url = Constant.upUrl(urlpath);//读取配置文件imageConfig.xml获取值
//	        DiskFileItemFactory fac = new DiskFileItemFactory();
//	        ServletFileUpload upload = new ServletFileUpload(fac);
//	        upload.setHeaderEncoding("utf-8");
//	        List fileList = null;
//	        try {
//	            fileList = upload.parseRequest(request);
//	        } catch (FileUploadException ex) {
//	            return;
//	        }
	        out.print(url);
		out.flush();
		out.close();
		} 

		

}
