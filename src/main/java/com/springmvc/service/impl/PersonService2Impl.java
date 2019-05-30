package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.PersonDao2;
import com.springmvc.entity.Temp;
import com.springmvc.service.PersonService2;

@Service
public class PersonService2Impl implements PersonService2 {

	@Resource 
	private PersonDao2 personDao;

	@Override
	public List<Temp> findByName(String name, long start, long end) {
		// TODO Auto-generated method stub
		return personDao.findByName(name, start, end);
	}

	@Override
	public List<Temp> findByNameAndCamerId(String name, long start, long end, List<String> canmerIds) {
		// TODO Auto-generated method stub
		return personDao.findByNameAndCamerId(name, start, end, canmerIds);
	}
	
	

}
