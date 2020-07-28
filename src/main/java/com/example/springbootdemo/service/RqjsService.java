package com.example.springbootdemo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface RqjsService {
	List<Map<String, Object>> getRqjs() throws UnsupportedEncodingException, Exception;
	
		
}
