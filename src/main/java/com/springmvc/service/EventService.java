package com.springmvc.service;

import java.util.List;

import com.springmvc.entity.Temp;

public interface EventService {
	public List<Temp> findByType(String event_type,long start,long end);

	public List<Temp> findByTypeAndCamerId(String event_type,long start,long end,List<String> canmerIds);
	
}
