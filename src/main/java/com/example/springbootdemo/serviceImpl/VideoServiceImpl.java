package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client_khd;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.AlgorithmDao;
import com.example.springbootdemo.dao.LogsDao;
import com.example.springbootdemo.service.VideoService;
import com.example.springbootdemo.util.HDFSUtil;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private AlgorithmDao algorithmDao;
	@Autowired
	private LogsDao logsdao;
	
	private String flag = "1";
	
	@Override
	public boolean sendVideoData(String videoId) throws Exception {
		List<Map<String, Object>> list=algorithmDao.getMonitorvideo(videoId);  //视频表
		AESCoder Aesc = new AESCoder();
		Client_khd khdfs = new Client_khd();
		HDFSUtil hdfs = new HDFSUtil();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> sendMap=new HashMap<>();
			sendMap.put("Timestamp", new Date().getTime()/1000+"");
			sendMap.put("NO", "3");
			sendMap.put("Mode", "monitorvideo");
			Map<String, Object> map=list.get(i);
			Map<String, Object> map_1=new HashMap<>();
			String md5Msg="";
			for (String key : map.keySet()) {
				if(key.equals("ID")||key.equals("MODELNAME")||key.equals("FORMAT")||key.equals("FILESIZE")||
				   key.equals("CAMERAID")||key.equals("ADDRESS")||key.equals("RESOLUTIONRATE")||key.equals("LENGTH_WEIGHT_SCALE")) {
					md5Msg = md5Msg +key+":"+ map.get(key).toString()+";";
					map_1.put(key, map.get(key).toString());
				}
				if (key.equals("ADDRESS")) {
					//从hdfs获取视频
					String videoPath="/input/upload/haha.mp4"; //测试先固定一个视频
//					String videoPath=map.get(key).toString();
					String[] split = videoPath.split("/");
					String videoname = split[split.length-1];
					String xz = hdfs.DownFromHDFS(videoPath, videoname, flag);
					System.out.println(xz);
					String videoIo = hdfs.getVideoIO_string(flag, videoname);
//					String videoIo = "ces";
					md5Msg = md5Msg +"INPUTVIDEO"+":"+videoIo+";";
					map_1.put("INPUTVIDEO", videoIo);
					hdfs.DeleteLocalVideo(flag);
				}
			}
			sendMap.put("Data", map_1);
			byte[] md5Byte_2 = AESCoder.MD5(md5Msg);
			sendMap.put("MD5", AESCoder.transByte(md5Byte_2));
			System.out.println(sendMap);
			Object send = Aesc.getAlldata(sendMap);
			try {
				boolean ok = khdfs.sendMsg(send.toString().getBytes());
				if (ok) {
					String id = map.get("ID").toString();
					algorithmDao.updateForMonitorvideo(id);
					return true;
				}
			} catch (Exception e) {
				Map<String, Object> insertMap = new HashMap<>();
				String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
				insertMap.put("uuid", id);
				insertMap.put("tablename", "T_MONITORVIDEO");
				insertMap.put("dataid", map.get("ID").toString());
				insertMap.put("error_data", "视频数据错误信息反馈");
				logsdao.insertLogs(insertMap);
				System.out.println("发送失败:"+send);
				return false;
			}
		}
		return true;
	}

}
