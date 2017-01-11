package com.solar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solar.service.IAlarmActionService;

@Controller
@RequestMapping("/alarm")
public class AlarmActionController {
	@Autowired
	private IAlarmActionService service;
	
	@RequestMapping("/show")
	@ResponseBody
	public Map<String, Object> show(String uid){
		Map<String, Object> map = this.service.show(uid);
		return map;
	}
}
