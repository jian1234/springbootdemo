package com.example.springbootdemo.service;

import com.example.springbootdemo.model.NumAndSleeptimeModel;

public interface PeopleFaceService {

	boolean TranPeopleFaceDate();
	
	boolean SetSleepAndNum(NumAndSleeptimeModel model);
	
	NumAndSleeptimeModel getNumAndSleeptimeModel();
	
	void stopScheduled();
	
	void RestartScheduled();
}
