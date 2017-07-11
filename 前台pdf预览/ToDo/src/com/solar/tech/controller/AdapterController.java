package com.solar.tech.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.solar.tech.bean.Adapter;
import com.solar.tech.dao.AdapterDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/adapter"})
public class AdapterController
{

  @Autowired
  private AdapterDao dao;

  @RequestMapping({"/add.action"})
  @ResponseBody
  @Transactional
  public Map<String, Object> add(Adapter adapter)
  {
    Map map = null;
    try {
      map = this.dao.add(adapter);
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return map;
  }
  @RequestMapping({"/edit.action"})
  @ResponseBody
  @Transactional
  public Map<String, Object> edit(Adapter adapter) { Map map = null;

    map = this.dao.edit(adapter);
    return map; } 
  @RequestMapping({"/remove.action"})
  @ResponseBody
  @Transactional
  public Map<String, Object> remove(Adapter adapter) {
    Map map = null;

    map = this.dao.remove(adapter);
    return map;
  }
  @RequestMapping({"/detailInfo.action"})
  @ResponseBody
  @Transactional
  public Map<String, Object> detailInfo(String systemName) {
    Map map = new HashMap();

    map = this.dao.detailInfo(systemName);

    return map;
  }

  @RequestMapping({"/daibandaiyue.action"})
  @ResponseBody
  @Transactional
  public Map<String, Object> daibandaiyue(String sysName, String sysUrl)
  {
    Map map = new HashMap();

    map = this.dao.daibandaiyue(sysName, sysUrl);

    return map;
  }
  
  
  @RequestMapping({"/show.action"})
  @Transactional
  public String show(Model model) {
    Map map = new HashMap();

    map = this.dao.show();
    model.addAttribute("map", map);

    return "NewFile";
  }
  
  @RequestMapping(value="/openTodo.action")   
  public String openTodo(String url,Model model) {
	List<Object> list = new ArrayList<Object>();

    list = this.dao.openTodo(url);
    model.addAttribute("list", list);

    return "OpenToDo";
  }
  
  
  @RequestMapping("/test")
  @ResponseBody
  public Map<String, Object> test(HttpServletRequest request){
	  
	  Map<String, Object> map = new HashMap<String, Object>();
	  List list = new ArrayList();
	  Adapter a = new Adapter();
	  a.setCnName("cnname");
	  list.add(a);
	  
	  Adapter b = new Adapter();
	  b.setCnName("bbbbb");
	  list.add(b);
	  
	  map.put("list", list);
	  return map;
  }
}