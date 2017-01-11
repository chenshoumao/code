package com.solar.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.dao.IAlarmActionDao;
import com.solar.service.IAlarmActionService;
@Service
public class AlarmActionServiceImpl implements IAlarmActionService{

	@Autowired
	private IAlarmActionDao dao;
	@Override
	public Map<String, Object> show(String uid) {
		// TODO Auto-generated method stub
		return dao.show(uid);
	}

}
