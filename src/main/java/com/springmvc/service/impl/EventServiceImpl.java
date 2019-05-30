package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.EventDao;
import com.springmvc.entity.Temp;
import com.springmvc.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Resource 
	private EventDao eventDao;

	
	@Override
	public List<Temp> findByType(String event_type, long start, long end) {
		// TODO Auto-generated method stub
		return eventDao.findByType(event_type, start, end);
	}


	@Override
	public List<Temp> findByTypeAndCamerId(String event_type, long start, long end, List<String> canmerIds) {
		// TODO Auto-generated method stub
		return eventDao.findByTypeAndCamerId(event_type, start, end, canmerIds);
	}

}
