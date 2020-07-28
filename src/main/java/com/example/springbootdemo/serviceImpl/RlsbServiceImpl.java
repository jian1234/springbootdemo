package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client;
import com.Learn_socket.Client_khd;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.LogsDao;
import com.example.springbootdemo.dao.RlsbDao;
import com.example.springbootdemo.service.RlsbService;
import com.example.springbootdemo.util.HBASEUtil;

@Service
public class RlsbServiceImpl implements RlsbService {

	@Autowired
	private RlsbDao rlsbdao;
	@Autowired
	private LogsDao logsdao;
	
	// 人脸识别
	@Override
	public List<Map<String, Object>> getRlsb() throws Exception {
		
		Map<String, Object> aMap = rlsbdao.getNumsForRlsb();
		int nums = Integer.parseInt(aMap.get("NUM").toString());
		List<Map<String, Object>> list = rlsbdao.getRlsbList(nums);
		HBASEUtil hbase = new HBASEUtil();
		AESCoder Aesc = new AESCoder();
		Client_khd khdfs = new Client_khd();
		Client client = new Client();
//		Socket socket = client.create();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> map1 = new HashMap<String, Object>();
			Map<String, Object> sendMap=new HashMap<>();
			sendMap.put("Timestamp", new Date().getTime()/1000+"");
			sendMap.put("NO", "3");
			sendMap.put("Mode", "facealarm");
			String md5string="";
			for (String key : map.keySet()) {
				if(key.equals("UUID")||key.equals("PERSON_ID")||key.equals("CHARACTER_ID")||key.equals("LIBID")||
				   key.equals("SIMILARITY")||key.equals("IMGPATH")||key.equals("POSITION_RECT")||key.equals("POSITION_P5S")||
				   key.equals("TASKID")||key.equals("CREATE_TIME")||key.equals("PLATFORM_ID")||key.equals("DATA_ID")) {
					md5string = md5string +key+":"+ map.get(key).toString()+";";
					map1.put(key, map.get(key).toString());
				}
				if (key.equals("IMGPATH")) {
					//获取图片
				/*	String picturePath = map.get("IMGPATH").toString();
					byte[] Picture_obj = {'1'};
					md5string = md5string +key+":"+ Picture_obj+";";
					map1.put(key, Picture_obj);*/
				}
			}
			sendMap.put("Data", map1);
			
			byte[] md5Byte_1 = AESCoder.MD5(md5string);
			sendMap.put("MD5", md5Byte_1);
			System.out.println("=1="+sendMap);
			Object send = Aesc.getAlldata(sendMap);
			byte[] bs= send.toString().getBytes();
			System.out.println(bs);
			System.out.println("=2="+bs);
			try {
				boolean ok = khdfs.sendMsg(send.toString().getBytes());
			} catch (Exception e) {
				Map<String, Object> insertMap = new HashMap<>();
				String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
				insertMap.put("uuid", id);
				insertMap.put("tablename", "T_FACEALARM");
				insertMap.put("dataid", map.get("UUID").toString());
				insertMap.put("error_data", "人脸识别错误信息反馈");
				logsdao.insertLogs(insertMap);
				System.out.println("发送失败:"+send);
			}
			
		}
		return null;
	}

}
