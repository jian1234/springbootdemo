package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn_socket.Client;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.PeopleCountDao;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PeopleModel;
import com.example.springbootdemo.model.PicAndVideoModel;
import com.example.springbootdemo.service.PeopleCountService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;

@Service("PeopleCountService")
public class PeopleCountServiceImpl implements PeopleCountService {

	@Autowired
	private PeopleCountDao peopleCountDao;
	@Autowired
	private PublicDao publicDao;
	
	private String num;
	
	private String flag = "1";
	@Override
	public boolean TranPeopleCOuntDate() {
		/*Logger logger = LoggerFactory.getLogger(getClass());
		int type = 1;
		NumAndSleeptimeModel numAndSleeptimeModel = this.getNumAndSleeptimeModel();
		num = numAndSleeptimeModel.getNUM();
		List<String> getMainid = peopleCountDao.GetMainid(num);
		HDFSUtil hdfs = new HDFSUtil();
		for (int i = 0; i < getMainid.size(); i++) {
			String main_id = getMainid.get(i);
			PicAndVideoModel picAndVideoModel = publicDao.getPicAndVideoModel(main_id);
			String cameraid = picAndVideoModel.getCAMERAID();//摄像头ID
			String outputpath = picAndVideoModel.getOUTPUTPATH();//结果视频再hdfs上的地址
			String tablename = picAndVideoModel.getTABLENAME();//hbase上的表名
			String columnname = picAndVideoModel.getCOLUMNNAME();//hbase上的列簇名
			//从hdfs上下载视频并且转换成输出流
			String[] split = outputpath.split("/");
			String videoname = split[split.length-1];
			hdfs.DownFromHDFS(outputpath,videoname , flag);
			ByteArrayOutputStream videoIO = hdfs.getVideoIO(flag, videoname);
			HBASEUtil hbase = new HBASEUtil();
			List<PeopleModel> getGroupEventEarl = peopleCountDao.GetGroupEventEarl(main_id);
			for (int j = 0; j < getGroupEventEarl.size(); j++) {
				PeopleModel peopleModel = getGroupEventEarl.get(j);
				String frame = peopleModel.getFRAME();
				String main_ID = peopleModel.getMAIN_ID();
				String csra_COUNT = peopleModel.getCSRA_COUNT()==null?"0":peopleModel.getCSRA_COUNT();
				String csrb_COUNT = peopleModel.getCSRB_COUNT()==null?"0":peopleModel.getCSRB_COUNT();
				String key_HBASE = peopleModel.getKEY_HBASE()==null?"0":peopleModel.getKEY_HBASE();
				String seg_COUNT = peopleModel.getSEG_COUNT();
				byte[] img = null; //人员聚集图片
				try {
					img = hbase.GetPicture(key_HBASE, tablename, columnname, "img");
				} catch (IOException e) {
					System.err.println(e.getMessage());
					logger.error(" hbase main_id"+main_id,"帧数"+frame,"错误"+e.getMessage());
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Timestamp", new Date().getTime()/1000+"");
				map.put("NO", "3");
				map.put("Mode", "groupEventEarlyWarning");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", "");
				dataMap.put("carmera_id", cameraid);
				dataMap.put("time", "");
				if (!"null".equals(seg_COUNT)&&!"0".equals(seg_COUNT)) {
					dataMap.put("Crowd_number", seg_COUNT);
				}else if (!"null".equals(csra_COUNT)&&!"0".equals(csra_COUNT)) {
					dataMap.put("Crowd_number", csra_COUNT);
				}else {
					dataMap.put("Crowd_number", csrb_COUNT);
				}
				dataMap.put("Crowd_number", "");//需要判断
				dataMap.put("form", "");
				dataMap.put("cause", "");
				dataMap.put("pic", "");
				dataMap.put("Video", "");
				dataMap.put("level", "");
				dataMap.put("reliability", "");
				map.put("Data", dataMap);
				String md5string = "";
				for (String key : dataMap.keySet()) {
					md5string = md5string +key+ dataMap.get(key).toString()+";"; 
				}
				AESCoder Aesc = new AESCoder();
				byte[] md5 = null;
				try {
					md5 = Aesc.MD5(md5string);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				map.put("MD5", md5);
				String string = map.toString();
				AESCoder aesCoder = new AESCoder();
				byte[] encryption = null;
				try {
					encryption = aesCoder.encryption(string);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				Client client = new Client();
				boolean sendMsg = false;
				
				try {
					sendMsg = client.SendMsg(encryption);
				} catch (IOException e) {
					System.err.println(e.getMessage());
					logger.error("socket"+main_id,"帧数"+frame+"错误"+e.getMessage());
				}
				
				
				if (!sendMsg) {
					type = -1 ;
				}
			}
			if (type == 1) {
				publicDao.updateTrans(main_id);
			}
			
		}
		
		hdfs.DeleteLocalVideo(flag);*/
		return true;
	}

	@Override
	public boolean SetSleepAndNum(NumAndSleeptimeModel model) {
		peopleCountDao.setSleepAndNum(model);
		return true;
	}

	@Override
	public NumAndSleeptimeModel getNumAndSleeptimeModel() {
		
		return peopleCountDao.getNumAndSleeptime();
	}

	@Override
	public void stopScheduled() {
		peopleCountDao.StopScheduled();
		
	}

	@Override
	public void RestartScheduled() {
		peopleCountDao.RestartScheduled();
		
	}


}
