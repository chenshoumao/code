package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.utils.Constant;
import com.utils.RandomNum;

public class UploadContinue extends HttpServlet {
	private static final long serialVersionUID = -6645287871363765811L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String savePath = this.getServletConfig().getServletContext().getRealPath("");
        HttpSession session = request.getSession(true);
        String description ="";
        if(session!=null){
        	description = (String) session.getAttribute("description");
        }
        String url = request.getParameter("url");
//        String urlpath = this.getServletContext().getRealPath("/config");//配置上传图片路径的配置文件路径
//		String url = Constant.upUrl(urlpath);//读取配置文件imageConfig.xml获取值
//		String path = this.getServletContext().getRealPath(url);//上传图片路径
		savePath = savePath + "/"+url+"/"+description+"/";
        File f1 = new File(savePath);
//        System.out.println(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }
        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
//                System.out.println(size + " " + type);
                if (name == null || name.trim().equals("")) {
                    continue;
                }
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                File file = null;
                do {
                	name = RandomNum.getRandomString(5);
                    file = new File(savePath + name + extName);
                } while (file.exists());
                File saveFile = new File(savePath + name + extName);
                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request,response);
    }
}