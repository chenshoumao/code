package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.ITrackActionDao;
import com.solar.service.ITrackActionService;
@Service
public class TrackActionServiceImpl implements ITrackActionService{

	@Autowired 
	private ITrackActionDao dao;
	
	@Override
	public Map<String, Object> show(String uid, String deviceid, String devicetype, String parDate, String timeStart,
			String timeEnd) {
		// TODO Auto-generated method stub
		return dao.show(uid, deviceid, devicetype, parDate, timeStart, timeEnd);
	}

}
