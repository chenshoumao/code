package com.solar.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IDeviceOfflineActionDao {
	public Map<String, Object> show(String uid);
}
