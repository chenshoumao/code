package com.solar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solar.service.IDeviceOfflineActionService;

@Controller
@RequestMapping("/deviceOffline")
public class DeviceOfflineActionController {
	@Autowired
	private IDeviceOfflineActionService service;
	
	@RequestMapping("/show")
	@ResponseBody
	public Map<String, Object> show(String uid){
		Map<String, Object> map = this.service.show(uid);
		return map;
	}
}
