package com.springmvc.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.UserDao2;
import com.springmvc.entity.User;
import com.springmvc.service.UserService2;
 
/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl2 implements UserService2 {
 
	@Resource 
	private UserDao2 userDao2;
 
	public User findByUsernameAndPwd(String name, String pwd) {
 
		return userDao2.findByUsernameAndPwd(name, pwd);
	}
 
	public List<User> find(User user){
 
		return userDao2.find(user);
	}
 
 
	public void add(User user) {
 
		userDao2.add(user);
	}
	
	public void update(User user) {
 
		userDao2.update(user);
	}
 
	public void delete(String id) {
 
		userDao2.delete(id);
	}
}
