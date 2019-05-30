package com.springmvc.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.Neo4jDao;
import com.springmvc.entity.User;
import com.springmvc.service.Neo4jService;
 
/**
 * 用户service实现类
 */
@Service
public class Neo4jServiceImpl implements Neo4jService {
 
	@Resource 
	private Neo4jDao Neo4jDao;
 
	public String findByUsernameAndPwd(String name, String pwd) {
 
		 return  Neo4jDao.findByUsernameAndPwd(name, pwd);
		 
		
	}

	@Override
	public List<User> find(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User User) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
 
	
}
