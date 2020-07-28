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
import com.example.springbootdemo.dao.TfDao;
import com.example.springbootdemo.service.TfService;
import com.example.springbootdemo.service.VideoService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;

@Service
public class TfServiceImpl implements TfService {

	@Autowired
	private AlgorithmDao algorithmDao;
	@Autowired
	private VideoService videoService;
	@Autowired
	private LogsDao logsdao;
	@Autowired
	private TfDao TfDao;
	private String flag = "1";
	
	@Override
	public List<Map<String, Object>> getTf() throws Exception {
		//条幅
		Map<String, Object> aMap = TfDao.getNumsForPeopleOther();  //需要执行的条数
		String nums = aMap.get("NUM").toString();
		int num = Integer.parseInt(nums);
		List<Map<String, Object>> list1 = TfDao.getAlgorithm_Tf(num); //第一步 算法表 statu='8'
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
				
				//条幅表
				List<Map<String, Object>> list3 = TfDao.PeopleOther(uuid);
				String tfid= "";
				String key_hbase="";
				int xg = 0;
				for (int j = 0; j < list3.size(); j++) {
					Map<String, Object> sendMap3 = new HashMap<>();
					sendMap3.put("Timestamp", new Date().getTime()/1000+"");
					sendMap3.put("NO", "3");
					sendMap3.put("Mode", "peopleOther");
					Map<String, Object> map3=list3.get(j);
					Map<String, Object> map3_1=new HashMap<>();
					String md5_3="";
					for (String key : map3.keySet()) {
						if (key.equals("UUID")||key.equals("MAIN_ID")||key.equals("BANNER")||key.equals("SEGMENTATION1")||key.equals("SKELETON")||
							key.equals("SEGMENTATION2")||key.equals("CREATE_TIME")||key.equals("KEY_HBASE")||key.equals("FRAME")) {
							md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
							map3_1.put(key, map3.get(key).toString());
						}else if (key.equals("PICTURE")) {
							//获取图片
							key_hbase = map3.get("KEY_HBASE").toString();
							byte[] Picture_obj = hbase.GetPicture(key_hbase, tableName, columnname, "img_peo");
							md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
							map3_1.put(key, Picture_obj);
						}
					}
					sendMap3.put("Data", map3_1);
					byte[] md5Byte_3 = AESCoder.MD5(md5_3);
					sendMap3.put("MD5", md5Byte_3);
					Object send_3 = Aesc.getAlldata(sendMap3);
					System.out.println("条幅表："+send_3);
					boolean sendMsg3=false;
					
					try {
						sendMsg3 = client.SendMsg(send_3.toString().getBytes(), socket);
						// 发送
						if (sendMsg3) {
							tfid=map3.get("UUID").toString();
							int a = TfDao.updatePeopleOther(tfid);
							xg=xg+a;
						}
					} catch (IOException e) {
						Map<String, Object> insertMap = new HashMap<>();
						String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
						insertMap.put("uuid", id);
						insertMap.put("tablename", "T_PEOPLEOTHER");
						insertMap.put("dataid", map3.get("UUID").toString());
						insertMap.put("error_data", "条幅表数据传输错误信息反馈");
						logsdao.insertLogs(insertMap);
						System.err.println(e.getMessage());
					}
					
				}
				boolean ok = false;
				if (list3.size() == xg) {
					//说明全部执行完，再发送视频
					ok=true;
					System.out.println(list3.size()+"全部执行完"+xg);
				}else {
					ok=false;
					System.out.println(list3.size()+"部分执行完"+xg);
				}
				if (video_ok && ok) {
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
						if (sendMsg3) {
							//整个过程都传输成功，算法表is_trans改成‘1’
							algorithmDao.updateForAlgorithm(uuid);
						}
					} catch (IOException e) {
						Map<String, Object> insertMap = new HashMap<>();
						String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
						insertMap.put("uuid", id);
						insertMap.put("tablename", "T_ALGORITHM_MAIN");
						insertMap.put("dataid", map_sf.get("UUID")+"");
						insertMap.put("error_data", "条幅算法主表数据传输错误信息反馈");
						logsdao.insertLogs(insertMap);
						System.err.println(e.getMessage());
					}
					
				
				}
			
		}
		return null;
	}

}
