package com.solar.service;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public interface IDeviceOnlineActionService {
	public Map<String, Object> show(String uid);
}
