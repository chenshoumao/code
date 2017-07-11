package com.solar.tech.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solar.tech.bean.ToDo;
import com.solar.tech.dao.ToDoDao;
@Controller
@RequestMapping("/todo")
public class ToDoController {
	@Autowired
	private ToDoDao dao;
	
	@RequestMapping("/show") 
	public String showTodo(Model model,int perPage ,int curPage){
		List list = new ArrayList();

		list = this.dao.show(perPage,curPage);
		model.addAttribute("list", list);
		return "showToDo";
	}
	
	@RequestMapping("/turnToDetail") 
	public String turnToDetail(Model model,ToDo todo){
		 
		model.addAttribute("todo", todo);
		return "detail";
	}
	
	@RequestMapping("/download")
	@ResponseBody
	public Map<String, Object> download(String fileUrl,String filename,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.dao.download(fileUrl, filename, request, response);
		return map;
	}
}
