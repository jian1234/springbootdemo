package com.example.springbootdemo.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.util.Base64Utils;

public class HDFSUtil {
	private String hdfsNode1 = "hdfs://199.66.68.111:8020";
	
	private String hdfsNode2 = "hdfs://199.66.68.112:8020";
	
	private String localPath_linger = "D:/hdfs_linger";
	
	private String localPath_peoplecount = "D:/hdfs_peoplecount";
	
	private String localPath_Remnant = "D:/hdfs_Remnant";
	
	public String DownFromHDFS(String hdfsPath,String videoname,String flag) {
		String localPath ;
		if ("1".equals(flag)) {
			localPath = this.localPath_peoplecount;
		}else if ("2".equals(flag)) {
				localPath = this.localPath_linger;
		}else if ("3".equals(flag)) {
			localPath = this.localPath_Remnant;
		}else {
			return "flag不正确";
		}
		Configuration conf = new Configuration();
		//指定hdfs的nameservice为cluster1,是NameNode的URI-Uniform Resource Locator
        conf.set("fs.defaultFS","hdfs://ns");
        //指定hdfs的nameservice为cluster1
        conf.set("dfs.nameservices","ns");
        //cluster1下面有两个NameNode，分别是nna，nns active/standby
        conf.set("dfs.ha.namenodes.ns","nna,nns");
        //nna的RPC通信地址
        conf.set("dfs.namenode.rpc-address.ns.nna",hdfsNode1);
        //nns的RPC通信地址
        conf.set("dfs.namenode.rpc-address.ns.nns",hdfsNode2);
        //配置失败自动切换实现方式
        conf.set("dfs.client.failover.proxy.provider.ns", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem fs;
		try {
			// 获取输入流
			fs = FileSystem.get(conf);
//			FSDataInputStream fis = fs.open(new Path("/input/upload/haha.mp4"));
			FSDataInputStream fis = fs.open(new Path(hdfsPath));
			// 创建输出流
//			FileOutputStream fos = new FileOutputStream(new File("D:/testhdfs/haha.mp4"));
			File dir = new File(localPath);
            if (!dir.exists()) {// 判断目录是否存在     
                dir.mkdir();   
            }
            
			FileOutputStream fos = new FileOutputStream(new File(localPath+"/"+videoname));
			IOUtils.copyBytes(fis, fos, conf);
			fis.close();
			fos.close();
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "下载失败";
		}
		return "Success";
	}
	
	public String DeleteLocalVideo(String flag) {
		String localPath ;
		if ("1".equals(flag)) {
			localPath = this.localPath_peoplecount;
		}else if ("2".equals(flag)) {
			localPath = this.localPath_linger;
		}else if ("3".equals(flag)) {
			localPath = this.localPath_Remnant;
		}else {
			return "flag不正确";
		}
		File file = new File(localPath);
		File[] listFiles = file.listFiles();// 获取文件夹下的所有文件
		if (listFiles!=null) {
			for (int i = 0; i < listFiles.length; i++) {
				listFiles[i].delete();
			}
		}
		
		return "Success";
		
	}
	
	public ByteArrayOutputStream getVideoIO(String flag,String videoname) {
		String localPath ;
		if ("1".equals(flag)) {
			localPath = this.localPath_peoplecount;
		}else if ("2".equals(flag)) {
			localPath = this.localPath_linger;
		}else if ("3".equals(flag)) {
			localPath = this.localPath_Remnant;
		}else {
			return null;
		}
		//输入流
		DataInputStream dis=null;
		FileInputStream fis=null;
		ByteArrayOutputStream dos = null;
		try {
			fis = new FileInputStream(localPath+"/"+videoname);
			dis = new DataInputStream(fis);
			dos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];//out写的时候，每次写1024个字节，如果in有2048个字节数，则读2048/1024=2次
		    int len;
		    while ((len = fis.read(buffer)) > 0){
		    	dos.write(buffer, 0, len);
		    }
		} catch (IOException e) {
			return null;
		}
		return dos;
	}
	
	public String getVideoIO_string(String flag,String videoname) throws IOException {
		String localPath ;
		if ("1".equals(flag)) {
			localPath = this.localPath_peoplecount;
		}else if ("2".equals(flag)) {
			localPath = this.localPath_linger;
		}else if ("3".equals(flag)) {
			localPath = this.localPath_Remnant;
		}else {
			return null;
		}
		InputStream in = new FileInputStream(localPath+"\\"+videoname);
	    byte[] data = toByteArray(in);
	    in.close();
	    String encodeToString = Base64Utils.encodeToString(data);
	    return encodeToString;
	}
	
	//输入流转换为byte[]
	private byte[] toByteArray(InputStream in) throws IOException {
				  
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
				 out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}
	
	public String getHdfsNode1() {
		return hdfsNode1;
	}

	public void setHdfsNode1(String hdfsNode1) {
		this.hdfsNode1 = hdfsNode1;
	}

	public String getHdfsNode2() {
		return hdfsNode2;
	}

	public void setHdfsNode2(String hdfsNode2) {
		this.hdfsNode2 = hdfsNode2;
	}
	public static void main(String[] args) {
		HDFSUtil hdfs = new HDFSUtil();
		hdfs.DownFromHDFS("/input/upload/haha.mp4","haha.mp4","2");
//		ByteArrayOutputStream videoIO = hdfs.getVideoIO("2", "haha.mp4");
//		System.out.println(videoIO);
	}
}
