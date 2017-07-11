package com.solar.tech.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.faces.component.UIForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.json.java.JSONObject;
import com.ibm.wps.services.cache.debug.Get;
import com.ibm.ws.batch.xJCL.beans.returnCodeExpression;
import com.solar.tech.bean.UserInfo;
 
import com.solar.tech.dao.PersonalSpaceDao;

@Controller
@RequestMapping("/PersonalSpace")
public class PersonalSpace {
	@Autowired
	private PersonalSpaceDao personalSpaceDao;
	
	@RequestMapping("/getTest")
	@ResponseBody
	public Map<String, Object> get(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	
	@RequestMapping(value ="/search")
	@ResponseBody
	public Map<String, Object> search(Model model,String curPage,UserInfo user){
		 
		Map<String, Object> map = 
				this.personalSpaceDao.searchPeople(curPage,user);
		model.addAttribute("totalPage",map.get("totalPage")); 
		model.addAttribute("list",map.get("list")); 
		return map;
	}
	
	@RequestMapping(value = "/show")
	@ResponseBody
	public Map<String, Object> showUserInfo(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo user = new UserInfo();
		System.out.println(111);
		com.solar.util.UserInfo uInfo = new com.solar.util.UserInfo();
	//	Principal principal = request.getUserPrincipal();
		System.out.println("name is : " + uInfo.getLoginName());
		user.setName(uInfo.getLoginName());
		
		user = this.personalSpaceDao.getInfo(user);
		map.put("user",user); 
		 
		return map;
	}
	
	/**
	 * 
	 * @param model
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updatePeople(UserInfo userInfo){
		Map<String, Object> resultMap = this.personalSpaceDao.updateUser(userInfo);
		return resultMap;
	}
	
    /**
     * 
     * @param file
     * @param request
     * @param response
     * @param session
     * @throws IllegalStateException
     * @throws IOException
     * 上传图片
     */ 
	@RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException{
//		ResultData<Object> resultData=new ResultData<>();
		 
		Map<String,Object> map = new HashMap<String,Object>();
		//String username = "leader1";
		com.solar.util.UserInfo uInfo = new com.solar.util.UserInfo();
		String loginName = uInfo.getLoginName();
		UserInfo user = new UserInfo();
		user.setName(loginName);
		
		//获取图片存储路径
		ResourceBundle bundle = ResourceBundle.getBundle("PersonSpace", new Locale(""));
		String url = bundle.getString("PersonalImageUrl");
		System.out.println(url);
		url += "/" + loginName;
		File file2  = new File(url);
		if(!file2.exists()){
			file2.mkdir();
		} 
		
		user = this.personalSpaceDao.getInfo(user);
		 if (file!=null) {// 判断上传的文件是否为空
	            String path=null;// 文件路径
	            String type=null;// 文件类型
	            String fileName=file.getOriginalFilename();// 文件原名称
	            path = url  + "/" + fileName;
	            System.out.println("上传的文件原名称:"+fileName);
	            // 判断文件类型
	            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
	            if (type!=null) {// 判断文件类型是否为空
	                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
	                    // 项目在容器中实际发布运行的根路径
	                    String realPath=request.getSession().getServletContext().getRealPath("/");
	                    // 自定义的文件名称
	                    //String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
	                    // 设置存放图片文件的路径
	                  //  path=realPath+/*System.getProperty("file.separator")+*/fileName;
	                    System.out.println("存放图片文件的路径:"+path);
	                    // 转存文件到指定的路径
	                    file.transferTo(new File(path));
	                    System.out.println("文件成功上传到指定目录下");
	                    user.setImageUrl(path);
	                    this.personalSpaceDao.updateUser(user);
	                    map.put("result", "success");
	                }else {
	                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
	                    map.put("result", "failed");
	                }
	            }else {
	                System.out.println("文件类型为空");
	                map.put("result", "failed");
	            }
	        }else {
	            System.out.println("没有找到相对应的文件");
	            map.put("result", "failed");
	        }
	
		 return map;
	}
	
	
	@RequestMapping(value = "/get") 
	public void getImage(HttpServletRequest request,HttpServletResponse response,String path) {
	    FileInputStream fis = null;
	    response.setContentType("image/gif");
	    try {
	        OutputStream out = response.getOutputStream();
	        File file = new File(path);
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
		        e.printStackTrace();
		    }   
	          }
	    }
	}
	
	
	 
}
