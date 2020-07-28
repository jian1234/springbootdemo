package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.AlgorithmDao;
import com.example.springbootdemo.dao.LogsDao;
import com.example.springbootdemo.dao.YlwDao;
import com.example.springbootdemo.service.VideoService;
import com.example.springbootdemo.service.YlwService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;

@Service
public class YlwServiceImpl implements YlwService {

	@Autowired
	private AlgorithmDao algorithmDao;
	@Autowired
	private YlwDao ylwDao;
	@Autowired
	private VideoService videoService;
	@Autowired
	private LogsDao logsdao;
	
	private String flag = "1";
	
	/**
	 * 可以物品遗留
	 */
	@Override
	public List<Map<String, Object>> getYlw() throws Exception {
		//遗留物
		Map<String, Object> aMap = ylwDao.getNumsForRemnant();  //需要执行的条数
		String nums = aMap.get("NUM").toString();
		int num = Integer.parseInt(nums);
		List<Map<String, Object>> list1 = ylwDao.getAlgorithm_Ylw(num); //第一步 算法表 statu='3'
		String t_monitorvideo_id="";
		String uuid ="";
		String tableName = "";  //hbases上该视频截图存储的表名
		String columnname = ""; //hbases上该视频截图存储的列簇名
		HDFSUtil hdfs = new HDFSUtil();
		HBASEUtil hbase = new HBASEUtil();
		AESCoder Aesc = new AESCoder();
		Client client = new Client();
		Socket socket = client.create();
		boolean video_ok = false;
		for (int i = 0; i < list1.size(); i++) {
			uuid = list1.get(i).get("UUID")+"";  //主键
			t_monitorvideo_id = list1.get(i).get("T_MONITORVIDEO_ID")+"";  //视频表id
			tableName = list1.get(i).get("TABLENAME")+"";  //表名
			columnname = list1.get(i).get("COLUMNNAME")+"";  //列簇名
			
			video_ok = videoService.sendVideoData(t_monitorvideo_id);
			
			//遗留物表
			List<Map<String, Object>> list3 = ylwDao.getRemnant(uuid);
			String ylwid= "";
			String key_hbase="";
			String main_id="";
			int xg = 0;
			for (int j = 0; j < list3.size(); j++) {
				Map<String, Object> sendMap_ylw = new HashMap<>();
				sendMap_ylw.put("Timestamp", new Date().getTime()/1000+"");
				sendMap_ylw.put("NO", "3");
				sendMap_ylw.put("Mode", "remnant");
				Map<String, Object> map3=list3.get(j);
				Map<String, Object> map3_1=new HashMap<>();
				String md5_3="";
				for (String key : map3.keySet()) {
					if (key.equals("UUID")||key.equals("MAIN_ID")||key.equals("SUSP_ID")||key.equals("ABADON_VID")||
						key.equals("KEY_HBASE")||key.equals("OBJ_ID")||key.equals("ROI")||key.equals("CREATE_TIME")) {
						md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
						map3_1.put(key, map3.get(key).toString());
					}else if (key.equals("PICTURE_SUSP")||key.equals("PICTURE_OBJ")) {
						//获取图片
						key_hbase = map3.get("KEY_HBASE").toString();
						byte[] Picture_obj = hbase.GetPicture(key_hbase, tableName, columnname, "img_peo");
						md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
						map3_1.put(key, Picture_obj);
					}
				}
				sendMap_ylw.put("Data", map3_1);
				byte[] md5Byte_3 = AESCoder.MD5(md5_3);
				sendMap_ylw.put("MD5", md5Byte_3);
				Object send_3 = Aesc.getAlldata(sendMap_ylw);
				boolean ylw = false;
				try {
					ylw=client.SendMsg(send_3.toString().getBytes(), socket);
				} catch (Exception e) {
					Map<String, Object> insertMap = new HashMap<>();
					String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
					insertMap.put("uuid", id);
					insertMap.put("tablename", "T_REMNANT");
					insertMap.put("dataid", map3.get("UUID").toString());
					insertMap.put("error_data", "可疑物品遗留表数据错误信息反馈");
					logsdao.insertLogs(insertMap);
				}
				//遗留物视频
				main_id = map3.get("MAIN_ID").toString();
				List<Map<String, Object>> list4 = ylwDao.getRemnant_Sp(main_id);
				int xgsp=0;
				String ylwspid="";
				for (int k = 0; k < list4.size(); k++) {
					Map<String, Object> sendMap_ylwsp=new HashMap<>();
					sendMap_ylwsp.put("Timestamp", new Date().getTime()/1000+"");
					sendMap_ylwsp.put("NO", "3");
					sendMap_ylwsp.put("Mode", "remnantVideo");
					Map<String, Object> map4=list4.get(k);
					Map<String, Object> map4_1=new HashMap<>();
					String md5_4="";
					for (String key : map4.keySet()) {
						if(key.equals("MAIN_ID")||key.equals("OBJ_ID")||key.equals("VIDEOPATH")||key.equals("CREATE_TIME")) {
							md5_4 = md5_4 +key+":"+ map4.get(key).toString()+";";
							map4_1.put(key, map4.get(key).toString());
						}
						if (key.equals("VIDEOPATH")) {
							//从hdfs获取视频
							String videoPath=map4.get(key).toString();
							String[] split = videoPath.split("/");
							String videoname = split[split.length-1];
							hdfs.DownFromHDFS(videoPath, videoname, flag);
							ByteArrayOutputStream videoIo = hdfs.getVideoIO(flag, videoname);
							md5_4 = md5_4 +"VIDEO"+":"+videoIo+";";
							map4_1.put("VIDEO", videoIo);
							hdfs.DeleteLocalVideo(flag);
						}
					}
					sendMap_ylwsp.put("Data", map4_1);
					byte[] md5Byte_4 = AESCoder.MD5(md5_4);
					sendMap_ylwsp.put("MD5", md5Byte_4);
					Object sendMap4 = Aesc.getAlldata(sendMap_ylwsp);
					System.out.println("遗留物视频表："+sendMap4);
					try {
						boolean ylwsp=client.SendMsg(sendMap4.toString().getBytes(), socket);
						// 发送
						if (ylwsp) {
							ylwspid=map3.get("OBJ_ID").toString();
							int b = ylwDao.updateRemnant(ylwid);
							xgsp=xgsp+b;
						}
					} catch (Exception e) {
						Map<String, Object> insertMap = new HashMap<>();
						String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
						insertMap.put("uuid", id);
						insertMap.put("tablename", "T_REMNANT_VIDEO");
						insertMap.put("dataid", map3.get("UUID").toString());
						insertMap.put("error_data", "可疑物品遗留视频表数据错误信息反馈");
						logsdao.insertLogs(insertMap);
					}
				}
				boolean ok = false;
				if (list4.size() == xgsp) {
					ok = true;
					System.out.println(list4.size()+"全部执行完"+xgsp);
				}else {
					ok = false;
					System.out.println(list4.size()+"部分执行完"+xgsp);
				}
				//遗留物视频全部发送完
				if (ok && ylw) {
					ylwid=map3.get("UUID").toString();
					ylwDao.updateRemnant(ylwid);
					
					//处理算法主表
					Map<String, Object> sendMap_sf=new HashMap<>();
					sendMap_sf.put("Timestamp", new Date().getTime()/1000+"");
					sendMap_sf.put("NO", "3");
					sendMap_sf.put("Mode", "algorithmMain");
					Map<String, Object> map_sf=list1.get(i);
					Map<String, Object> map_sf_1=new HashMap<>();
					String md5_sf="";
					for (String key : map_sf.keySet()) {
						if(key.equals("UUID")||key.equals("INPUTPATH")||key.equals("OUTPUTPATH")||key.equals("TABLENAME")||
						   key.equals("COLUMNNAME")||key.equals("STATUS")||key.equals("CREATE_TIME")||key.equals("T_MONITORVIDEO_ID")) {
							md5_sf = md5_sf +key+":"+ map_sf.get(key).toString()+";";
							map_sf_1.put(key, map_sf.get(key).toString());
						}else if(key.equals("OUTPUTVIDEO")) {
							//从hdfs获取视频
							String videoPath=map_sf.get(key).toString();
							String[] split = videoPath.split("/");
							String videoname = split[split.length-1];
							hdfs.DownFromHDFS(videoPath, videoname, flag);
							ByteArrayOutputStream videoIo = hdfs.getVideoIO(flag, videoname);
							md5_sf = md5_sf +"OUTPUTVIDEO"+":"+videoIo+";";
							map_sf_1.put("OUTPUTVIDEO", videoIo);
							hdfs.DeleteLocalVideo(flag);
						}
					}
					sendMap_sf.put("Data", map_sf_1);
					byte[] md5Byte_sf = AESCoder.MD5(md5_sf);
					sendMap_sf.put("MD5", md5Byte_sf);
					System.out.println("算法表："+sendMap_sf);
					Object send_sf = Aesc.getAlldata(sendMap_sf);
					System.out.println(send_sf);
					boolean sendMsg3 = false;
					try {
						sendMsg3 = client.SendMsg(send_sf.toString().getBytes(),socket);
						if (sendMsg3 && video_ok) {
							//整个过程都传输成功，算法表is_trans改成‘1’
							algorithmDao.updateForAlgorithm(uuid);
						}
					} catch (IOException e) {
						Map<String, Object> insertMap = new HashMap<>();
						String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
						insertMap.put("uuid", id);
						insertMap.put("tablename", "T_ALGORITHM_MAIN");
						insertMap.put("dataid", sendMap_sf.get("UUID").toString());
						insertMap.put("error_data", "算法主表数据错误信息反馈");
						logsdao.insertLogs(insertMap);
					}
					
				}
			}
	
		}
		return null;
	}

}
