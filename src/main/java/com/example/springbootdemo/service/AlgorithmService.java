package com.example.springbootdemo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface AlgorithmService {
	List<Map<String, Object>> getAlgorithm() throws UnsupportedEncodingException, Exception;
	
		
}
