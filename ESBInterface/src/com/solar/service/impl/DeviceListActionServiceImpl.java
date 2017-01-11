package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.IDeviceListActionDao;
import com.solar.dao.impl.DeviceListActionDaoImpl;
import com.solar.service.IDeviceListActionService;
@Service
public class DeviceListActionServiceImpl implements IDeviceListActionService{

	@Autowired
	private IDeviceListActionDao dao;
	@Override
	public Map<String, Object> show(String uid) {
		// TODO Auto-generated method stub
		return dao.show(uid);
	}

}
