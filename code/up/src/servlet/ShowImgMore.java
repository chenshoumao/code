package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.Constant;
import com.utils.Img;
import com.utils.Pager;
import com.utils.ReadFiles;
import com.utils.StringUtil;

/**
 * 
 * ClassName: ShowImg 
 * @Description: 显示相册
 * @author houhuayu
 * @date 2016-6-20
 */
public class ShowImgMore extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
 	    response.setContentType("text/html;charset=UTF-8");
 	    
 	    String isadmin = request.getParameter("isadmin");
 	    String pageNumStr = request.getParameter("pageNum");
 	   String pageSizeStr = request.getParameter("pageSize");
 	   
    	// 校验pageNum参数输入合法性
		if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
			request.setAttribute("errorMsg", "参数传输错误");
			request.getRequestDispatcher("/showPage.jsp").forward(request, response);
			return;
		}
		
		//显示第几页数据
		int pageNum = Constant.DEFAULT_PAGE_NUM; 
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		// 每页显示多少条记录
//		int pageSize = Constant.DEFAULT_PAGE_SIZE; 
		int pageSize = Constant.DEFAULT_PAGE_SIZE_MORE; 
		String name = request.getParameter("imgName");//相册名称
		if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		 String urlpath = this.getServletContext().getRealPath("/config");//配置上传图片路径的配置文件路径
		 List<String> urlList = Constant.urlList(urlpath);//读取配置文件imageConfig.xml获取值
		 List<String> pathList = new ArrayList<String>();
		 for(int i=0;i<urlList.size();i++){
			 String path = this.getServletContext().getRealPath(urlList.get(i));//上传图片路径
			 pathList.add(path);
			//判断上传upUrl该文件是否存在
			 File f1 = new File(path);
		        if (!f1.exists()) {
		            f1.mkdirs();
		        }
		 }
		 
		 
		 //循环获取相册
		 Pager<Img> result = ReadFiles.photo(pathList,urlList,pageNum,pageSize);
		 
		request.setAttribute("result", result);
		 if(isadmin!=null&&!isadmin.equals("")){
	 	    	if(isadmin.equals("1")){
	 	    		request.getRequestDispatcher("/showOpenMore.jsp").forward(request,response);
	 	    	}else{
	 	    		request.getRequestDispatcher("/showMore.jsp").forward(request,response);
	 	    	}
	 	    }else{
	 	    	request.getRequestDispatcher("/showMore.jsp").forward(request,response);
	 	    }
		
	}

}
