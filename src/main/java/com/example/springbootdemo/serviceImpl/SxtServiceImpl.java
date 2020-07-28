package com.example.springbootdemo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client_khd;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.LogsDao;
import com.example.springbootdemo.dao.SxtDao;
import com.example.springbootdemo.service.SxtService;
import com.example.springbootdemo.util.HBASEUtil;

@Service
public class SxtServiceImpl implements SxtService {

	@Autowired
	private SxtDao sxtDao;
	
	@Autowired
	private LogsDao logsdao;
	
	// 摄像头
	@Override
	public List<Map<String, Object>> getSxt() throws Exception {
		
		Map<String, Object> aMap = sxtDao.getNumsForSxt();
		int nums = Integer.parseInt(aMap.get("NUM").toString());
		List<Map<String, Object>> list = sxtDao.getSxtList(nums);
		AESCoder Aesc = new AESCoder();
		Client_khd khdfs = new Client_khd();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> map1 = new HashMap<String, Object>();
			Map<String, Object> sendMap=new HashMap<>();
			sendMap.put("Timestamp", new Date().getTime()/1000+"");
			sendMap.put("NO", "3");
			sendMap.put("Mode", "camera");
			String md5string="";
			for (String key : map.keySet()) {
				if(key.equals("ID")||key.equals("NAME")||key.equals("CODE")||key.equals("CAMERATYPE")||key.equals("REGIONID")||
				   key.equals("HEIGHT")||key.equals("DENSE")||key.equals("ANGLE")||key.equals("LAYOUT")||key.equals("DAYLIGHTING")||
				   key.equals("BRAND")||key.equals("MODEL")||key.equals("CLARITY")||key.equals("VIEWAREA")||key.equals("MAXFRAME")||
				   key.equals("OUTPUTFORMAT")||key.equals("OUTPUTINTERFACE")||key.equals("SOFTWARECOMPATIBILITY")||key.equals("FRAMERATE")||
				   key.equals("IMAGERANGE")||key.equals("LONGITUDE")||key.equals("LATITUDE")||key.equals("PLATFORM_URL")||key.equals("IMAGESOURCE_IP")) {
					md5string = md5string +key+":"+ map.get(key).toString()+";";
					map1.put(key, map.get(key).toString());
				}
			}
			sendMap.put("Data", map1);
			System.out.println(sendMap);
			byte[] md5Byte_1 = AESCoder.MD5(md5string);
			sendMap.put("MD5", AESCoder.transByte(md5Byte_1));
			Object send = Aesc.getAlldata(sendMap);
			try {
				boolean ok = khdfs.sendMsg(send.toString().getBytes());
//				boolean ok = client.SendMsg(send.toString().getBytes());
				if (ok) {
					String sxtid=map.get("ID").toString();
					sxtDao.updateForSxt(sxtid);
				}
			} catch (Exception e) {
				Map<String, Object> insertMap = new HashMap<>();
				String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
				insertMap.put("uuid", id);
				insertMap.put("tablename", "T_CAMERA");
				insertMap.put("dataid", map.get("ID").toString());
				insertMap.put("error_data", "摄像头数据错误信息反馈");
				logsdao.insertLogs(insertMap);
			}
			
		}
		return null;
	}

}
