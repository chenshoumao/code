package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utils.Constant;

@SuppressWarnings("serial")
public class DeleteImg extends HttpServlet {
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   request.setCharacterEncoding("utf-8");
    	   response.setContentType("text/html;charset=utf-8");
    	   String upurl = request.getParameter("url");
    	   String realUpurl =  this.getServletContext().getRealPath(upurl);
    	   File delPhotoPath  = new File(realUpurl);
    	   delPhotoPath.delete();
    	   request.setAttribute("data", "success");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);
    }
}