package com.example.springbootdemo.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.dao.RemnantDao;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PicAndVideoModel;
import com.example.springbootdemo.model.RemnantModel;
import com.example.springbootdemo.service.RemnantService;
import com.example.springbootdemo.util.HBASEUtil;
import com.example.springbootdemo.util.HDFSUtil;

@Service("remnantService")
public class RemnantServiceImpl implements RemnantService{
	@Autowired
	private RemnantDao remnantDao;
	
	@Autowired
	private PublicDao publicDao;
	
	private String num;
	
	private String flag = "3";
	
	@Override
	public boolean TranRemnantDate() {
		/*Logger logger = LoggerFactory.getLogger(getClass());
		int type = 1;
		NumAndSleeptimeModel numAndSleeptimeModel = this.getNumAndSleeptimeModel();
		num = numAndSleeptimeModel.getNUM();
		List<String> getMainid = remnantDao.GetMainid(num);
		HDFSUtil hdfs = new HDFSUtil();
		for (int i = 0; i < getMainid.size(); i++) {
			String main_id = getMainid.get(i);
			PicAndVideoModel picAndVideoModel = publicDao.getPicAndVideoModel(main_id);
			String cameraid = picAndVideoModel.getCAMERAID();//摄像头ID
			String outputpath = picAndVideoModel.getOUTPUTPATH();//结果视频再hdfs上的地址
			String tablename = picAndVideoModel.getTABLENAME();//hbase上的表名
			String columnname = picAndVideoModel.getCOLUMNNAME();//hbase上的列簇名
			//从hdfs上下载视频并且转换成输出流,若地址为空，则代表无数据，不需要下载，hbase也不需要下载
			if(outputpath != null) {
				String[] split = outputpath.split("/");
				String videoname = split[split.length-1];
				hdfs.DownFromHDFS(outputpath,videoname , flag);
				ByteArrayOutputStream videoIO = hdfs.getVideoIO(flag, videoname);
			}
			HBASEUtil hbase = new HBASEUtil();
			
			List<RemnantModel> getRemnant = remnantDao.GetRemnant(getMainid.get(i));
			for (int j = 0; j < getRemnant.size(); j++) {
				RemnantModel remnantModel = getRemnant.get(j);
				String frame = remnantModel.getFRAME();
				String key_HBASE = remnantModel.getKEY_HBASE();
				String main_ID = remnantModel.getMAIN_ID();
				String obj_ID = remnantModel.getOBJ_ID();
				String roi = remnantModel.getROI();
				String susp_ID = remnantModel.getSUSP_ID();
				String create_TIME = remnantModel.getCREATE_TIME();
				String abadon_VID = remnantModel.getABADON_VID();
				byte[] Picture_obj = null; //遗弃者图片
				byte[] Picture_susp = null; //遗弃物图片
				if (outputpath != null) {
					try {
						Picture_obj = hbase.GetPicture(key_HBASE, tablename, columnname, "img_obj");
						Picture_susp = hbase.GetPicture(key_HBASE, tablename, columnname, "img_susp");
					} catch (IOException e) {
						System.err.println(e.getMessage());
						logger.error(" hbase main_id"+main_id,"帧数"+frame,"错误"+e.getMessage());
					}
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Timestamp", new Date().getTime()/1000+"");
				map.put("NO", "3");
				map.put("Mode", "suspiciousItemsLeft");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("id", "");
				dataMap.put("Camera_id", "");
				dataMap.put("time", "");
				dataMap.put("Size", "");
				dataMap.put("Goods_ pic", Picture_obj);
				dataMap.put("Person_ pic", Picture_susp);
				dataMap.put("Video", "");
				dataMap.put("level", "");
				dataMap.put("reliability", "");//可信度
				map.put("Data", dataMap);
				map.put("MD5", "");
				String string = map.toString();
				AESCoder aesCoder = new AESCoder();
				byte[] encryption = null;
				try {
					 encryption = aesCoder.encryption(string);
				} catch (Exception e) {
					System.err.println(e.getMessage());
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
			if (type == 1) {
				publicDao.updateTrans(main_id);
			}
			
		}
		hdfs.DeleteLocalVideo(flag);*/
		return true;
	}

	@Override
	public boolean SetSleepAndNum(NumAndSleeptimeModel model) {
		remnantDao.setSleepAndNum(model);
		return true;
	}

	@Override
	public NumAndSleeptimeModel getNumAndSleeptimeModel() {
		
		return remnantDao.getNumAndSleeptime();
	}

	@Override
	public void stopScheduled() {
		remnantDao.StopScheduled();
		
	}

	@Override
	public void RestartScheduled() {
		remnantDao.RestartScheduled();
	}

}
