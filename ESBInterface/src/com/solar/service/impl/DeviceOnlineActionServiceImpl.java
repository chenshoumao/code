package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.IDeviceOnlineActionDao;
import com.solar.service.IDeviceOnlineActionService;
@Service	
public class DeviceOnlineActionServiceImpl implements IDeviceOnlineActionService{

	@Autowired
	private IDeviceOnlineActionDao dao;
	@Override
	public Map<String, Object> show(String uid) {
		// TODO Auto-generated method stub
		return dao.show(uid);
	}

}
