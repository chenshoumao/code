package com.solar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solar.service.IDeviceActionService;

@Controller
@RequestMapping("/device")
public class DeviceActionController {
	@Autowired
	private IDeviceActionService service;
	
	@RequestMapping("/show")
	@ResponseBody
	public Map<String, Object> show(String uid,String devicetype,String deviceid){
		Map<String, Object> map = service.show(uid,devicetype,deviceid);
		System.out.println(12);
		return map;
	}
	
	@RequestMapping("/showInfo") 
	public String showInfo(){ 
		return "DeviceActionQuery";
	}
}
