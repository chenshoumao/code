package com.solar.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IAlarmActionDao {
	public Map<String, Object> show(String uid);
}
