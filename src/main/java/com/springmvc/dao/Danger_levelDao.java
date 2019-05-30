package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.Danger_level;

public interface Danger_levelDao {

	public List<Danger_level> getDangerLevel(String danger_type);
}
