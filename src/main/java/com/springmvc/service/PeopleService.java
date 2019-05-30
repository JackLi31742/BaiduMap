package com.springmvc.service;

import java.util.List;

import com.springmvc.entity.People;

public interface PeopleService {
	
	public List<People> getPeople(int id);
	
	public List<People> getLink(int id);

}
