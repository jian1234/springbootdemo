package com.example.springbootdemo.service;

import com.example.springbootdemo.model.CameraModel;

interface CameraService {
	
	public CameraModel Getall();
	
	public int UpdateCamera(CameraModel model);
	
}
