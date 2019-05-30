package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.PeopleDao;
import com.springmvc.entity.People;
import com.springmvc.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Resource 
	private  PeopleDao peopleDao;
	@Override
	public List<People> getPeople(int id) {
		// TODO Auto-generated method stub
		return peopleDao.getPeople(id);
	}
	@Override
	public List<People> getLink(int id) {
		// TODO Auto-generated method stub
		return peopleDao.getLink(id);
	}

}
