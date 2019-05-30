package com.springmvc.service;

import java.util.List;

import com.springmvc.entity.Camera;

public interface CameraService {

	public List<Camera> findAll();
	
	public List<Camera> findById(String camera_id);
	
	public void insert();
}
