package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.IDeviceActionDao;
import com.solar.service.IDeviceActionService;
@Service
public class DeviceActionServiceImpl implements IDeviceActionService{

	@Autowired
	private IDeviceActionDao dao;
	
	@Override
	public Map<String, Object> show(String uid, String devicetype, String deviceid) {
		// TODO Auto-generated method stub
		return dao.show(uid,devicetype,deviceid);
	}

}
