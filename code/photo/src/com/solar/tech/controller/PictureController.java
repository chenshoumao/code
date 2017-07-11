package com.solar.tech.controller;

 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; 

import com.ibm.wps.util.Hash;
import com.ibm.ws.batch.xJCL.beans.returnCodeExpression;
import com.solar.tech.bean.Img;
import com.solar.tech.bean.Pager;
import com.solar.tech.dao.FindImg;
import com.solar.tech.dao.PictureDao;
import com.solar.tech.dbutil.StringUtil;
import com.solar.tech.util.Constant;
import com.solar.util.UserInfo;
 
@Controller
@RequestMapping("/picture")
public class PictureController {
	@Autowired
	private PictureDao dao;
	
 
	@RequestMapping("/show") 
	@ResponseBody
	public Map<String, Object> show(String pageNum,String pageSize){
		UserInfo userInfo = new UserInfo();
		String username = userInfo.getLoginName();
		System.out.println("username is : " + username);
		Map<String,Object> map = this.dao.show(username,  pageNum,  pageSize);
		 
		
		return map;
	}
	@RequestMapping("/showDetail")
	@ResponseBody
	public Map<String, Object> showDetail(HttpServletRequest request,HttpServletResponse response,
			String isadmin,String url,
			String pageNum,String pageSize,String imgName,String photo,String newImg){
		  
		Map<String,Object> map = new HashMap<String,Object>();
		map = this.dao.showDetail( request, response,
				 isadmin, url,
				 pageNum, pageSize, imgName, photo, newImg);
				
		return map;
	}
	
	
	@RequestMapping("/CreatePhoto")
	@ResponseBody
	public void CreatePhoto(HttpServletRequest request,String description){
		 HttpSession session = request.getSession(); 
		 session.setAttribute("description", description);
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request,@RequestParam(value="Filedata")MultipartFile file){ 
		Map<String,Object> map=new HashMap<String, Object>();
        map = this.dao.add(file);
 		return map; 
       
       
	}
	
	@RequestMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(HttpServletRequest request,@RequestParam(value="Filedata")MultipartFile file){
		Map<String,Object> map=new HashMap<String, Object>();
		this.dao.uploadImage(request,file); 
		return map;
	}
	
	//下载图片
	@RequestMapping("/downLoadImage") 
		public void downLoadImage(HttpServletRequest request,HttpServletResponse response,String id){
			this.dao.downLoadImage(request,response,id);
	}
	
	@RequestMapping("/getImage")
	@ResponseBody
	public Map<String, Object> getImage(HttpServletRequest request,Pager pager){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.dao.getImage(request,pager);
		return map;
	} 
	
	
	//删除图片
	@RequestMapping("/deleteImage") 
	@ResponseBody
	public 	Map<String,Object>  deleteImage(HttpServletRequest request,HttpServletResponse response,String id){
		Map<String,Object> map=new HashMap<String, Object>();
		map= this.dao.deleteImage(request,response,id);
		return map;
	}

//	@RequestMapping("/getImageByAlbum")
//	@ResponseBody
//	public Map<String, Object> getImageByAlbum(HttpServletRequest request,String albumName){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = this.dao.getImageByAlbum(request,albumName);
//		return map; 
//	}
	
	@RequestMapping("/getImageByAlbum")
	@ResponseBody
	public Map<String, Object> getImageByAlbum(HttpServletRequest request,String albumId,String stage){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.dao.getMyDraftImageByFolder(request,albumId,stage);
		return map; 
	}
	
	
	//提交相册包含的草稿 或者是 通过 相册的待审核图片文件
	@RequestMapping("/commitDraftImageByAlbum")
	@ResponseBody
	public Map<String, Object> commitMyDraftImageByAlbum(HttpServletRequest request,String albumId,String stage){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.dao.commitDraftImageByAlbum(request,albumId,stage);
		return map; 
	}
	
	//审核不通过 滚回上一个阶段
	@RequestMapping("/moveDraftImageToPrevWF")
	@ResponseBody
	public Map<String, Object> moveDraftImageToPrevWF(HttpServletRequest request,String albumId){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.dao.moveDraftImageToPrevWF(request,albumId);
		return map; 
	}
	
	
	
	
	/*
	 * @author 陈守貌
	 * 上传图片，第一步先保存字段信息
	 */
	@RequestMapping("/addFirst")
	@ResponseBody
	public Map<String,Object> addFirst(String description){ 
		Map<String,Object> map=new HashMap<String, Object>();
        map = this.dao.addFirst(description);
 		return map; 
       
       
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(String photo,String nphoto,String url,HttpServletRequest request){ 
 	  
	   Map<String, Object> map = new HashMap<String, Object>(); 
	   map = this.dao.edit(photo,nphoto,url);
 	  
	   return map;
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Map<String,Object>  remove(HttpServletRequest request,String albumId){  
		 Map<String,Object> map = new HashMap<String, Object>();
		 map = this.dao.remove(request,albumId);  
		 return map;
	}
}
