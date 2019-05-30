package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.PersonDao;
import com.springmvc.entity.Person;
import com.springmvc.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Resource 
	private PersonDao personDao;
	
	@Override
	public List<Person> find(int camId) {
		// TODO Auto-generated method stub
		return personDao.find(camId);
	}

}
