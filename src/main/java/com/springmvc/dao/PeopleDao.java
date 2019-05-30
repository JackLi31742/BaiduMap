package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.People;

public interface PeopleDao {

	public List<People> getPeople(int id);
	
	public List<People> getLink(int id);
}
