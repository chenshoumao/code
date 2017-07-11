package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreatePhoto extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	   response.setContentType("text/html;charset=utf-8");
    	   String description=request.getParameter("description");
    	   HttpSession session = request.getSession(); 
           session.setAttribute("description", description);
    	   PrintWriter out = response.getWriter();
    	   out.write(description);
    	   out.flush();
    	   out.close();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);
    }
}