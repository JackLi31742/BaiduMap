package com.springmvc.service;
 
 
import java.util.List;

import com.springmvc.entity.Person;
 
/**
 * service接口
 */
public interface PersonService {
 
	 public List<Person> find(int camId);
}
