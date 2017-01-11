package com.solar.service;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public interface ITrackActionService {
	public  Map<String, Object> show(String uid,String deviceid,
			String devicetype,String parDate,String timeStart,String timeEnd);
}
