package com.example.springbootdemo.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Learn_socket.Client;
import com.Test_three.AESCoder;
import com.example.springbootdemo.dao.PeopleFaceDao;
import com.example.springbootdemo.dao.PublicDao;
import com.example.springbootdemo.model.NumAndSleeptimeModel;
import com.example.springbootdemo.model.PeopleFaceModel;
import com.example.springbootdemo.service.PeopleFaceService;
import com.example.springbootdemo.util.GetPictureByURL;
@Service("PeopleFaceService")
public class PeopleFaceServiceImpl implements PeopleFaceService {
	@Autowired
	private PublicDao publicdao;
	
	@Autowired
	private PeopleFaceDao PeopleFaceDao;
	@Override
	public boolean TranPeopleFaceDate() {
		/*int type = 1;
		String urlHander = ""; //获取图片的地址前半部
		NumAndSleeptimeModel numAndSleeptime = PeopleFaceDao.getNumAndSleeptime();
		List<String> uuidlist = PeopleFaceDao.GetMainid(numAndSleeptime.getNUM());
		GetPictureByURL GetPic = new GetPictureByURL();
		String uuid = "";
		for (int i = 0; i < uuidlist.size(); i++) {
			uuid = uuidlist.get(i);
			PeopleFaceModel model = PeopleFaceDao.GetpeoFace(uuid);
			String uuid2 = model.getUuid();
			String imgpath = model.getIMGPATH();
			imgpath = urlHander+imgpath;
			byte[] picByte = GetPic.getPicByte(imgpath);
			String similarity = model.getSIMILARITY();//相似的
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Timestamp",new Date().getTime()/1000+"");
			map.put("NO", "3");
			map.put("Mode", "faceCompare");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("picid", "");
			data.put("score", similarity);
			data.put("pid", "");
			data.put("name", "");
			data.put("id_code", "");
			data.put("gender", "");
			data.put("nation", "");
			data.put("native", "");
			data.put("phone_number", "");
			data.put("type", "");
			map.put("Data",data);
			map.put("MD5", "");
			String string = map.toString();
			AESCoder aesCoder = new AESCoder();
			byte[] encryption = null;
			try {
				encryption = aesCoder.encryption(string);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
			Client client = new Client();
			boolean sendMsg = false;
			try {
				sendMsg = client.SendMsg(encryption);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sendMsg) {
				int updateTRANS = PeopleFaceDao.updateTRANS(uuid);
			}
			
		}*/
//		if () {
		return true;	
//		}
//		return false;
	}

	@Override
	public boolean SetSleepAndNum(NumAndSleeptimeModel model) {
		int setSleepAndNum = PeopleFaceDao.setSleepAndNum(model);
		if (setSleepAndNum == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public NumAndSleeptimeModel getNumAndSleeptimeModel() {
		
		return PeopleFaceDao.getNumAndSleeptime();
	}

	@Override
	public void stopScheduled() {
		PeopleFaceDao.StopScheduled();
	}

	@Override
	public void RestartScheduled() {
		
	}

}
