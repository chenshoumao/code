package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.IDeviceOfflineActionDao;
import com.solar.service.IDeviceOfflineActionService;

@Service
public class DeviceOfflineActionServiceImpl implements IDeviceOfflineActionService{

	@Autowired
	private IDeviceOfflineActionDao dao;
	@Override
	public Map<String, Object> show(String uid) {
		// TODO Auto-generated method stub
		return dao.show(uid);
	}

}
