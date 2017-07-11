package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.FindImg;
import com.utils.Constant;
import com.utils.Img;
import com.utils.Pager;
import com.utils.StringUtil;

public class showDetailPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
 	    response.setContentType("text/html;charset=UTF-8");
		
 	    //是否是管理员1和0
 	    String isadmin = request.getParameter("isadmin");
 	    String url = request.getParameter("url");
		// 校验pageNum参数输入合法性
		String pageNumStr = request.getParameter("pageNum"); 
		if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
			request.setAttribute("errorMsg", "参数传输错误");
			request.getRequestDispatcher("/showPage.jsp").forward(request, response);
			return;
		}
		
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
//		int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		int pageSize = 1000;
		String pageSizeStr = request.getParameter("pageSize");
		String name = request.getParameter("imgName");//相册名称
		String photo = request.getParameter("photo");//相册名称
		if(photo!=null&&!photo.equals("")){
			name = photo;
		}
		if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		String newImg = request.getParameter("newImg");
		//调用service 获取查询结果
		FindImg findImg = new FindImg();
		String savePath = this.getServletConfig().getServletContext().getRealPath(url);
		Pager<Img> result = findImg.findImg(newImg,name,savePath,url,pageNum, pageSize);
				
		// 返回结果到页面
		System.out.println("::::::::::result::::::::");
	    if(null!=result){
	    	System.out.println("result::::::::"+result.getTotalRecord());
	    }
		request.setAttribute("result", result);
		request.setAttribute("url", url);
		System.out.println("::::::::::url::::::::"+url);
		if(isadmin!=null){
			if(isadmin.equals("1")){
				System.out.println("::::::::::showPage::::::::");
				request.getRequestDispatcher("/showPage.jsp").forward(request, response);
				
			}else{
				System.out.println("::::::::::showPageOpen::::::::");
				request.getRequestDispatcher("/showPageOpen.jsp").forward(request, response);
			}
		}else{
			System.out.println("::::::::::isadmin------showPageOpen::::::::");
			request.getRequestDispatcher("/showPageOpen.jsp").forward(request, response);
		}
				
	}

}
