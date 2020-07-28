package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.CameraModel;

public interface CameraDao {
	//获取所有的摄像头信息
	public CameraModel GetAll();
	
	public int UpdateCamera(CameraModel model);
	
}
