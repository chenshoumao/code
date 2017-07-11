package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpPhotoname extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
 	    response.setContentType("text/html;charset=utf-8");
 	    String photo = request.getParameter("photo");
 	    String nphoto = request.getParameter("nphoto");
 	   String url = request.getParameter("url");
 	   String PhotoPath = getServletContext().getRealPath(url+"/"+photo); // 转为物理路径  
 	  String PhotoPathn = getServletContext().getRealPath(url+"/"+nphoto);
 	   File file  = new File(PhotoPath);
 	   File f = null;
       File f1 = null;
	      boolean bool = false;
		 try{      
			 f = new File(PhotoPath);
	         f1 = new File(PhotoPathn);
	         bool = f.renameTo(f1);
	      }catch(Exception e){
	         e.printStackTrace();
	      }
		 request.setAttribute("data", "success");
	}

}
