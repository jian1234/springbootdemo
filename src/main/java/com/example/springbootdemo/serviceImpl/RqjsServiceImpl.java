package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
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
import com.example.springbootdemo.dao.RqjsDao;
import com.example.springbootdemo.service.RqjsService;
import com.example.springbootdemo.service.VideoService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;

@Service
public class RqjsServiceImpl implements RqjsService {
	
	@Autowired
	private AlgorithmDao algorithmDao;
	@Autowired
	private RqjsDao RqjsDao;
	@Autowired
	private VideoService videoService;
	@Autowired
	private LogsDao logsdao;
	
	private String flag = "1";
	
	//人群计数
	@Override
	public List<Map<String, Object>> getRqjs() throws UnsupportedEncodingException, Exception {
		boolean rqjs = true;
		//人群计数执行条数
		Map<String, Object> aMap = RqjsDao.getNumsForPeopleCount();  //需要执行的条数
		String nums = aMap.get("NUM").toString();
		int num = Integer.parseInt(nums);
		List<Map<String, Object>> list1 = RqjsDao.getAlgorithm_rqjs(num); //第一步 算法表 statu='1'
		String t_monitorvideo_id="";
		String uuid ="";
		String tableName = "";  //hbases上该视频截图存储的表名
		String columnname = ""; //hbases上该视频截图存储的列簇名
		HDFSUtil hdfs = new HDFSUtil();
		HBASEUtil hbase = new HBASEUtil();
		Client_khd khdfs = new Client_khd();
		boolean video_ok = false;
		for (int i = 0; i < list1.size(); i++) {
			uuid = list1.get(i).get("UUID")+"";  //主键
			t_monitorvideo_id = list1.get(i).get("T_MONITORVIDEO_ID")+"";  //视频表id
			tableName = list1.get(i).get("TABLENAME")+"";  //表名
			columnname = list1.get(i).get("COLUMNNAME")+"";  //列簇名 
			
//			video_ok = videoService.sendVideoData(t_monitorvideo_id);
			
			//人群计数表
			List<Map<String, Object>> list3 = RqjsDao.getPeopleCount(uuid);
			String rqjsid= "";
			String key_hbase="";
			for (int j = 0; j < list3.size(); j++) {
				Map<String, Object> sendMap3 = new HashMap<>();
				sendMap3.put("Timestamp", new Date().getTime()/1000+"");
				sendMap3.put("NO", "3");
				sendMap3.put("Mode", "peopleCount");
				Map<String, Object> map3=list3.get(j);
				Map<String, Object> map3_1=new HashMap<>();
				String md5_3="";
				key_hbase = map3.get("KEY_HBASE").toString();
//				String Picture_obj = hbase.GetPicture_string(key_hbase, tableName, columnname, "img_peo");
				String Picture_obj = hbase.GetPicture_string("1", "test_pic", "pic", "picture"); //测试先固定写
				md5_3 = md5_3 +"PICTURE"+":"+ Picture_obj+";";
				map3_1.put("PICTURE", Picture_obj);
				for (String key : map3.keySet()) {
					if (key.equals("UUID")||key.equals("MAIN_ID")||key.equals("PEOPLE_COUNT")||key.equals("FRAME")||key.equals("KEY_HBASE")||
						key.equals("CSRA_COUNT")||key.equals("CSRB_COUNT")||key.equals("SEG_COUNT")||key.equals("CREATE_TIME")||key.equals("DENSITY_MAP")) {
						md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
						map3_1.put(key, map3.get(key).toString());
					}
				}
				sendMap3.put("Data", map3_1);
				byte[] md5Byte_3 = AESCoder.MD5(md5_3);
				sendMap3.put("MD5", AESCoder.transByte(md5Byte_3));
				System.out.println(sendMap3);
				Object send = AESCoder.getAlldata(sendMap3);
				//发送成功修改人群计数表状态
				boolean rqjs_ok = false;
				try {
					rqjs_ok = khdfs.sendMsg(send.toString().getBytes());
					if (rqjs_ok) {
						rqjsid=map3.get("UUID").toString();
						int a = RqjsDao.updatePeopleCount(rqjsid);
					}else {
						rqjs = false;
					}
				} catch (Exception e) {
					Map<String, Object> insertMap = new HashMap<>();
					String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
					insertMap.put("uuid", id);
					insertMap.put("tablename", "T_MONITORVIDEO");
					insertMap.put("dataid", map3.get("UUID").toString());
					insertMap.put("error_data", "人群计数数据错误信息反馈");
					logsdao.insertLogs(insertMap);
					System.out.println("发送失败:"+sendMap3);
					rqjs = false;
				}
			}
			if (video_ok && rqjs) {
				//处理算法主表-人群计数
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
						String videoPath="/input/upload/haha.mp4";
//						String videoPath=map_sf.get(key).toString();
						String[] split = videoPath.split("/");
						String videoname = split[split.length-1];
//						hdfs.DownFromHDFS(videoPath, videoname, flag);
					    String videoIo = hdfs.getVideoIO_string(flag, videoname);
//						String videoIo = "视频流";
						md5_sf = md5_sf +"OUTPUTVIDEO"+":"+videoIo+";";
						map_sf_1.put("OUTPUTVIDEO", videoIo);
//						hdfs.DeleteLocalVideo(flag);
					}
				}
				sendMap_sf.put("Data", map_sf_1);
				byte[] md5Byte_3 = AESCoder.MD5(md5_sf);
				sendMap_sf.put("MD5", md5Byte_3);
				Object send_sf = AESCoder.getAlldata(sendMap_sf);
				try {
					boolean sf_ok = khdfs.sendMsg(send_sf.toString().getBytes());
					if (sf_ok) {
						algorithmDao.updateForAlgorithm(uuid);
					}
				} catch (Exception e) {
					Map<String, Object> insertMap = new HashMap<>();
					String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
					insertMap.put("uuid", id);
					insertMap.put("tablename", "T_ALGORITHM_MAIN");
					insertMap.put("dataid", sendMap_sf.get("UUID")+"");
					insertMap.put("error_data", "算法主表数据错误信息反馈");
					logsdao.insertLogs(insertMap);
				}
			}
		}
		
		return null;
	}

}
