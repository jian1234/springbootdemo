package com.example.springbootdemo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface RqhzdService {

	// 人群混杂度
	List<Map<String, Object>> getRqhzd() throws UnsupportedEncodingException, Exception;
}
