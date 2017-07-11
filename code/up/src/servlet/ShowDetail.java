package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.Img;
import com.utils.ReadFiles;

public class ShowDetail extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
 	    response.setContentType("text/html;charset=utf-8");
 	  
		String name = request.getParameter("imgName");//Ïà²áÃû³Æ
		 String ss=new String(name.getBytes("ISO-8859-1"),"utf-8"); //×ªÂëUTF8
		 String savePath = this.getServletConfig().getServletContext().getRealPath("/uploads2");
//			List<Img> listImg = ReadFiles.photo(path, upurl);
//		request.setAttribute("listImg", listImg);
		request.getRequestDispatcher("/Detail.jsp").forward(request,response);
	}

}
