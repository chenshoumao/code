package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.Constant;
/**
 * 
 * ClassName: Delete 
 * @Description: 删除相册
 * @author houhuayu
 * @date 2016-6-16
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 6907568866268004697L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,12 response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
 	   response.setContentType("text/html;charset=utf-8");
 	   String photo = request.getParameter("photo");
 	   String url = request.getParameter("url");
 	   String isadmin = request.getParameter("isadmin");
 	   String PhotoPath = getServletContext().getRealPath(url+"/"+photo); // 转为物理路径  
 	   File file  = new File(PhotoPath);
 	  if (file.exists()) {//判断文件是否存在  
 	     if (file.isFile()) {//判断是否是文件  
 	      file.delete();//删除文件   
 	     } else if (file.isDirectory()) {//否则如果它是一个目录  
 	      File[] files = file.listFiles();//声明目录下所有的文件 files[];  
 	      for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件  
// 	       this.delete(files[i]);//把每个文件用这个方法进行迭代  
 	    	 files[i].delete();
 	      }  
 	      file.delete();//删除文件夹  
 	     }  
 	    } else {  
 	     System.out.println("所删除的文件不存在");  
 	    } 
 	  request.setAttribute("data", "success1");
// 	  request.setAttribute("isadmin", isadmin);
// 	  request.getRequestDispatcher("/servlet/ShowImgMore").forward(request, response);
	}

}
