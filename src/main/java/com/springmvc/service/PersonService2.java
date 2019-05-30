package com.springmvc.service;
 
 
import java.util.List;

import com.springmvc.entity.Person;
import com.springmvc.entity.Temp;
 
/**
 * service接口
 */
public interface PersonService2 {
 
	public List<Temp> findByName(String name,long start,long end);
	public List<Temp> findByNameAndCamerId(String name,long start,long end,List<String> canmerIds);
}
