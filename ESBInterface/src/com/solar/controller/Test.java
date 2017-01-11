package com.solar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {
	@RequestMapping("/show")
	@ResponseBody
	public Map<String, Object> show(){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String,Object>();
		map1.put("sd", "csm");
		Map<String, Object> map2 = new HashMap<String,Object>();
		map2.put("sd22", "csm22");
		
		List list = new ArrayList();
		list.add(map1);
		//list.add(map2);
		
		map.put("test", list);
		
		return map;
	}

}
