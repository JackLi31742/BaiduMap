
package com.springmvc.service;

import java.util.List;

import com.springmvc.entity.Danger_level;

public interface Danger_levelService {

	public List<Danger_level> getDangerLevel(String danger_type);
}
