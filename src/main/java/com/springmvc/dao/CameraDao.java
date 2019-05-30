package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.Camera;

public interface CameraDao {
	
	public List<Camera> findAll();
	
	public List<Camera> findById(String camera_id);

	
	public void insert();
}
