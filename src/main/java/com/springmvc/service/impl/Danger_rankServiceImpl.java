package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.Danger_rankDao;
import com.springmvc.entity.Danger_rank;
import com.springmvc.service.Danger_rankService;

@Service
public class Danger_rankServiceImpl implements Danger_rankService {

	@Resource 
	private  Danger_rankDao danger_rankDao;
	@Override
	public List<Danger_rank> getDanger_rank() {
		// TODO Auto-generated method stub
		return danger_rankDao.getDanger_rank();
	}

}
