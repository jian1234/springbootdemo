package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.LingerDao;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.model.LingerModel;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PicAndVideoModel;
import com.example.springbootdemo.service.LingerServer;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;




@Service("LingerServer")
public class LingerServerImpl  implements LingerServer   {
	@Autowired
	private LingerDao lingerDao;
	
	@Autowired
	private PublicDao publicDao;
	
	private String num;
	
	private long sleeptime;
	
	private String flag = "2";
	
	@Override
//	@Scheduled(fixedRate = 5000 )//隔5000毫秒执行一次
//	@Scheduled(cron = "${scheduled.cron}" )//从配置文件中读取
//	@Scheduled(cron = num )//从配置文件中读取
	public boolean TranLingerDate() {
		/*Logger logger = LoggerFactory.getLogger(getClass());
		int type = 1 ;//用来判断是否所有的都已经执行成功
		NumAndSleeptimeModel numAndSleeptimeModel = this.getNumAndSleeptimeModel();
		num = numAndSleeptimeModel.getNUM();
		List<String> getMainid = lingerDao.GetMainid(num);
		List<LingerModel> list = new ArrayList<LingerModel>();
		HDFSUtil hdfs = new HDFSUtil();
		for (int i = 0; i < getMainid.size(); i++) {
			String main_id = getMainid.get(i);
			PicAndVideoModel picAndVideoModel = publicDao.getPicAndVideoModel(main_id);
			String cameraid = picAndVideoModel.getCAMERAID();//摄像头ID
			String outputpath = picAndVideoModel.getOUTPUTPATH();//结果视频再hdfs上的地址
			String tablename = picAndVideoModel.getTABLENAME();//hbase上的表名
			String columnname = picAndVideoModel.getCOLUMNNAME();//hbase上的列簇名
			
			//从hdfs上下载视频并且转换成输出流
			if (outputpath != null) {
				String[] split = outputpath.split("/");
				String videoname = split[split.length-1];
				hdfs.DownFromHDFS(outputpath,videoname , flag);
				ByteArrayOutputStream videoIO = hdfs.getVideoIO(flag, videoname);
				
			}
			List<LingerModel> getLingerlist = lingerDao.GetLinger(getMainid.get(i));
			HBASEUtil hbase = new HBASEUtil();
			list.addAll(getLingerlist);
			for (int j = 0; j < getLingerlist.size(); j++) {
				LingerModel getLinger = getLingerlist.get(j);
				String frame = getLinger.getFRAME();//帧数
				String main_ID = getLinger.getMAIN_ID();//main_ID
				String create_TIME = getLinger.getCREATE_TIME();//获取插入时间
				
				byte[] Picture_obj = null; //遗弃者图片
				byte[] Picture_susp = null; //遗弃物图片
//				if (outputpath!=null &&key_HBASE !=null) {
//					try {
//						Picture_obj = hbase.GetPicture(key_HBASE, tablename, columnname, "img_obj");
//						Picture_susp = hbase.GetPicture(key_HBASE, tablename, columnname, "img_susp");
//					} catch (IOException e) {
//						logger.error(" hbase main_id"+main_id,"帧数"+frame,"错误"+e.getMessage());
//					}
//				}
//				if ("0".equals(person_RESULT)) {
//					continue;
//				}else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("Timestamp", new Date().getTime()/1000+"");
					map.put("NO", "3");
					map.put("Mode", "wanderingAlarm");
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("id", "");
					dataMap.put("Camera_id", cameraid);
					dataMap.put("pid", "");
					dataMap.put("Boundingbox_t", "");
					dataMap.put("Boundingbox_l", "");
					dataMap.put("Boundingbox_w", "");
					dataMap.put("Boundingbox_h", "");
					dataMap.put("time", "");
					dataMap.put("S_time", "");
					dataMap.put("pic", "");
					dataMap.put("Video", "");
					dataMap.put("level", "");
					dataMap.put("reliability", "");
					dataMap.put("entropy", "");
					map.put("Data", dataMap);
					map.put("MD5", "");
					String string = map.toString();
					AESCoder aesCoder = new AESCoder();
					byte[] encryption = null;
					try {
						encryption = aesCoder.encryption(string);
					} catch (Exception e) {
						logger.error(" socket main_id"+main_id,"帧数"+frame,"错误"+e.getMessage());
					}
					Client client = new Client();
					boolean sendMsg = false;
					try {
						sendMsg = client.SendMsg(encryption);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!sendMsg) {
						type = -1;
					}
			}
			if (type==-1) {
				
			}else {
				publicDao.updateTrans(main_id);
			}
			
			
		}
		hdfs.DeleteLocalVideo(flag);*/
		return true;
	}
	

	@Override
	public boolean SetSleepAndNum(NumAndSleeptimeModel model) {
		lingerDao.setSleepAndNum(model);
		return true;
	}
	
	
	@Override
	public NumAndSleeptimeModel getNumAndSleeptimeModel() {
			
		return lingerDao.getNumAndSleeptime();
	}


	@Override
	public void stopScheduled() {
		lingerDao.StopScheduled();
		
	}



	@Override
	public void RestartScheduled() {
		lingerDao.RestartScheduled();
		
	}


	
	
}
