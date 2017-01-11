package com.solar.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IDeviceActionDao {
	public Map<String, Object> show(String uid, String devicetype, String deviceid);
}
