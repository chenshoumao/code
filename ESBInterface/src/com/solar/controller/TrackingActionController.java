package com.solar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solar.service.ITrackActionService;

@Controller
@RequestMapping("/track")
public class TrackingActionController {
	@Autowired
	private ITrackActionService service;
	
	@RequestMapping("/show")
	@ResponseBody
	public Map<String,Object> show(String uid,String deviceid,
			String devicetype,String parDate,String timeStart,String timeEnd){
		Map<String,Object> map = this.service.show(uid, deviceid, devicetype, parDate, timeStart, timeEnd);
		return map;
	}
}
