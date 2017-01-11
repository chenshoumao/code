package com.solar.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface ITrackActionDao {
	public  Map<String, Object> show(String uid,String deviceid,
			String devicetype,String parDate,String timeStart,String timeEnd);
}
