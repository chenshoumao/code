package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.Constant;
/**
 * 
 * ClassName: DownImg 
 * @Description: 下载图片
 * @author houhuayu
 * @date 2016-6-21
 */
public class DownImg extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		  // 获取当前目录的图片路径
        // String path=this.getServletContext().getRealPath("/image/tuxing.png");
//		 String urlpath = this.getServletContext().getRealPath("/config");//配置上传图片路径的配置文件路径
//		 String url = Constant.upUrl(urlpath);//读取配置文件imageConfig.xml获取值
		 String tourl = req.getParameter("url");
//		 String imgName = req.getParameter("imgName");
//		 String photo = req.getParameter("photo");
		String path=this.getServletContext().getRealPath(tourl);
//        String path=this.getServletContext().getRealPath(url+"/"+photo+"/"+imgName);
        // 获取文件名
        String fileName=path.substring(path.lastIndexOf("\\")+1);
        System.out.println(path);
        System.out.println(fileName);
        //制定浏览器头
        //在下载的时候这里是英文是没有问题的 
        //resp.setHeader("content-disposition", "attachment;fileName="+fileName);
        //如果图片名称是中文需要设置转码
        resp.setHeader("content-disposition", "attachment;fileName="+URLEncoder.encode(fileName, "UTF-8"));
        InputStream reader = null;
        OutputStream out = null;
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            // 读取文件
        	File file = new File(path);
        	
            reader = new FileInputStream(file);
            // 写入浏览器的输出流
            out = resp.getOutputStream();

//            while ((len = reader.read(bytes)) > 0) {
//                out.write(bytes, 0, len);
//            }
            while ((len = reader.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null)
                out.close();
        }
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   doGet(request, response);
	}

}
