package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.Hdfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.AlgorithmDao;
import com.example.springbootdemo.dao.LogsDao;
import com.example.springbootdemo.service.AlgorithmService;
import com.example.springbootdemo.service.VideoService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;
import com.fasterxml.jackson.core.sym.Name;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

	@Autowired
	private AlgorithmDao algorithmDao;
	@Autowired
	private LogsDao logsdao;
	@Autowired
	private VideoService videoService;
	
	private String flag = "1";
	
	//人群徘徊
	
	@Override
	public List<Map<String, Object>> getAlgorithm() throws UnsupportedEncodingException, Exception {
		//人群徘徊执行条数
		Map<String, Object> aMap = algorithmDao.getNumsForLinger();
		String nums = aMap.get("NUM").toString();
		int num = Integer.parseInt(nums);
		List<Map<String, Object>> list = algorithmDao.getAlgorithm(num); //第一步 算法表
		String t_monitorvideo_id="";
		String uuid ="";
		String tableName = "";  //hbases上该视频截图存储的表名
		String columnname = ""; //hbases上该视频截图存储的列簇名
		HDFSUtil hdfs = new HDFSUtil();
		HBASEUtil hbase = new HBASEUtil();
		boolean sendMsg = false;
		boolean sendMsg1 = false;
		boolean sendMsg2 = false;
		AESCoder Aesc = new AESCoder();
		Client client = new Client();
		Socket socket = client.create();
		boolean video_ok = false;
		for (int i = 0; i < list.size(); i++) {
			uuid = list.get(i).get("UUID")+"";  //主键
			t_monitorvideo_id = list.get(i).get("T_MONITORVIDEO_ID")+"";  //视频表id
			tableName = list.get(i).get("TABLENAME")+"";  //表名
			columnname = list.get(i).get("COLUMNNAME")+"";  //列簇名
			
			video_ok = videoService.sendVideoData(t_monitorvideo_id);
			
			//人员徘徊处理 获取人员徘徊表信息
			List<Map<String, Object>> list3 = algorithmDao.getLinger(uuid);
			if (list3.size()>0) {
				Map<String, Object> sendMap3=new HashMap<>();
				sendMap3.put("Timestamp", new Date().getTime()/1000+"");
				sendMap3.put("NO", "3");
				sendMap3.put("Mode", "linger");
				Map<String, Object> map3=list3.get(0);
				Map<String, Object> map3_1=new HashMap<>();
				String md5_3="";
				for (String key : map3.keySet()) {
					if(key.equals("UUID")||key.equals("MAIN_ID")||key.equals("FRAME")||key.equals("PERSON_MATCH")||
					   key.equals("CREATE_TIME")) {
						md5_3 = md5_3 +key+":"+ map3.get(key).toString()+";";
						map3_1.put(key, map3.get(key).toString());
					}
				}
				sendMap3.put("Data", map3_1);
				byte[] md5Byte_3 = AESCoder.MD5(md5_3);
				sendMap3.put("MD5", md5Byte_3.toString());  //加密的data
				System.out.println(sendMap3);                //整个数据
				//发送
				Object send_3 = Aesc.getAlldata(sendMap3);
				System.out.println(send_3);
				
				try {
					sendMsg1 = client.SendMsg(send_3.toString().getBytes(),socket);
				} catch (IOException e) {
					Map<String, Object> insertMap = new HashMap<>();
					String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
					insertMap.put("uuid", id);
					insertMap.put("tablename", "T_LINGER");
					insertMap.put("dataid", map3.get("UUID").toString());
					insertMap.put("error_data", "人员徘徊数据传输错误信息反馈");
					logsdao.insertLogs(insertMap);
					System.err.println(e.getMessage());
				}
				
				//发送完 处理人员徘徊辅表
				if (sendMsg1) {
					//人员徘徊附表处理 获取人员徘徊附表信息
					List<Map<String, Object>> list4 = algorithmDao.getLinger_Frame(uuid);
					String ryphfbid="";
					String key_hbase="";
					int xg = 0;
					for (int j = 0; j < list4.size(); j++) {
						Map<String, Object> sendMap4=new HashMap<>();
						sendMap4.put("Timestamp", new Date().getTime()/1000+"");
						sendMap4.put("NO", "3");
						sendMap4.put("Mode", "lingerFrame");
						Map<String, Object> map4=list4.get(j);
						Map<String, Object> map4_1=new HashMap<>();
						String md5_4="";
						for (String key : map4.keySet()) {
							if(key.equals("LINGER_ID")||key.equals("MAIN_ID")||key.equals("FRAME")||key.equals("KEY_HBASE")||
							   key.equals("PEO_ID")||key.equals("SPEEN")||key.equals("DISTANCE")||key.equals("TIME")||key.equals("SLOPE")||
							   key.equals("PERSON_RESULT")||key.equals("LOITER_TYPE")||key.equals("CREATE_TIME")) {
								md5_4 = md5_4 +key+":"+ map4.get(key).toString()+";";
								map4_1.put(key, map4.get(key).toString());
							}else if(key.equals("PICTURE")) {
								//获取图片
								key_hbase = map4.get("KEY_HBASE").toString();
								byte[] Picture_obj = hbase.GetPicture(key_hbase, tableName, columnname, "img_peo");
								md5_4 = md5_4 +key+":"+ Picture_obj+";";
								map4_1.put(key, Picture_obj);
							}
						}
						sendMap4.put("data", map4_1);
						byte[] md5Byte_4 = AESCoder.MD5(md5_4);
						sendMap4.put("MD5", md5Byte_4);
						System.out.println("人员徘徊附表"+sendMap4);
						//发送
						Object send_4 = Aesc.getAlldata(sendMap4);
						System.out.println(send_4);
						
						try {
							sendMsg2 = client.SendMsg(send_4.toString().getBytes(),socket);
						} catch (IOException e) {
							Map<String, Object> insertMap = new HashMap<>();
							String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
							insertMap.put("uuid", id);
							insertMap.put("tablename", "t_Linger_Frame");
							insertMap.put("dataid", map4.get("LINGER_ID").toString());
							insertMap.put("error_data", "人员徘徊附表数据传输错误信息反馈");
							logsdao.insertLogs(insertMap);
							System.err.println(e.getMessage());
						}
						//发送成功修改人员徘徊附表状态
						if (sendMsg2) {
							int a = algorithmDao.updateForLinger_Frame(ryphfbid);
							xg= xg+a;
						}
					}
					boolean ok = false;
					if (list4.size() == xg) {
						ok=true;
						System.out.println(list4.size()+"全部修改完"+xg);
					}else {
						ok=false;
						System.out.println(list4.size()+"部分修改完"+xg);
					}
					
					if (ok) {
						//把人员徘徊表标识改为 “1”
						String ryphid =map3.get("UUID").toString();
						algorithmDao.updateForLinger(ryphid);

					}
					
					//处理算法主表
					if (ok&&video_ok&&sendMsg1) {
						Map<String, Object> sendMap_sf=new HashMap<>();
						sendMap_sf.put("Timestamp", new Date().getTime()/1000+"");
						sendMap_sf.put("NO", "3");
						sendMap_sf.put("Mode", "algorithmMain");
						Map<String, Object> map_sf=list.get(i);
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
						} catch (IOException e) {
							Map<String, Object> insertMap = new HashMap<>();
							String id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
							insertMap.put("uuid", id);
							insertMap.put("tablename", "T_ALGORITHM_MAIN");
							insertMap.put("dataid", map_sf.get("UUID")+"");
							insertMap.put("error_data", "人员徘徊算法主表数据传输错误信息反馈");
							logsdao.insertLogs(insertMap);
							System.err.println(e.getMessage());
						}
						if (sendMsg3) {
							//整个过程都传输成功，算法表is_trans改成‘1’
							algorithmDao.updateForAlgorithm(uuid);
						}
					}
				}
			}

			
		}
		return list;
	}
	
	
	/**  
	 * a对象转数组  
     * @param obj  
     * @return  
     */  
    public byte[] toByteArray (Object obj) {      
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;    
    }

	
}
