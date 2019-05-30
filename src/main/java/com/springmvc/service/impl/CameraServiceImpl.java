package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.dao.CameraDao;
import com.springmvc.entity.Camera;
import com.springmvc.service.CameraService;


@Service
public class CameraServiceImpl implements CameraService {

	
	@Resource 
	private CameraDao cameraDao;
	
	@Override
	public List<Camera> findAll() {
		// TODO Auto-generated method stub
		return cameraDao.findAll();
	}

	@Override
	public List<Camera> findById(String camera_id) {
		// TODO Auto-generated method stub
		return cameraDao.findById(camera_id);
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		cameraDao.insert();
	}

}
