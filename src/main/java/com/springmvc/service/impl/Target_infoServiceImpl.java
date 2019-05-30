package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.Target_infoDao;
import com.springmvc.entity.Targets;
import com.springmvc.service.Target_infoService;

@Service
public class Target_infoServiceImpl implements Target_infoService {

	
	@Resource 
	private Target_infoDao target_infoDao;
	
	@Override
	public List<Targets> getTarget_info(int id) {
		// TODO Auto-generated method stub
		return target_infoDao.getTarget_info(id);
	}

}
