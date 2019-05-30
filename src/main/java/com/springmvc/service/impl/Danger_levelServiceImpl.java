package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.Danger_levelDao;
import com.springmvc.entity.Danger_level;
import com.springmvc.service.Danger_levelService;

@Service
public class Danger_levelServiceImpl implements Danger_levelService{

	@Resource 
	private Danger_levelDao danger_levelDao;

	@Override
	public List<Danger_level> getDangerLevel(String danger_type) {
		// TODO Auto-generated method stub
		return danger_levelDao.getDangerLevel(danger_type);
	}
	
	
}
