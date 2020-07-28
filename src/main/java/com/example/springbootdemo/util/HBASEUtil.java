package com.example.springbootdemo.util;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.Base64Utils;

public class HBASEUtil {
	/**
	 * 
	 * @param rowKey //行键
	 * @param tablename 表名
	 * @param family 列簇名
	 * @param col 列名
	 * @return
	 * @throws IOException
	 */
	
	public byte[] GetPicture(String rowKey,String tablename,String family,String col) throws IOException {
				Configuration configuration = HBaseConfiguration.create();
		        //configuration.set("fs.defaultFS","hdfs://199.66.68.112:8020");
		        configuration.set("hbase.zookeeper.quorum","199.66.68.111,199.66.68.112,199.66.68.113,199.66.68.114,199.66.68.115,199.66.68.116,199.66.68.117");
		        configuration.set("hbase.zookeeper.property.clientPort", "2181");
		        
		        
		        HTable table = new HTable(configuration,tablename);
		        Get get = new Get(rowKey.getBytes());
		        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(col));
		        Result rs = table.get(get);
		        byte[] bs = null;
		        if(!rs.isEmpty()) {
		        	//bs为图片的字节流
		        	//保存get result的结果，字节数组形式
		        	bs = rs.getValue(Bytes.toBytes(family), Bytes.toBytes(col));

		            table.close();
		    }
		    return bs;
		   
	}
	
	public String GetPicture_string(String rowKey,String tablename,String family,String col) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
        //configuration.set("fs.defaultFS","hdfs://199.66.68.112:8020");
        configuration.set("hbase.zookeeper.quorum","199.66.68.111,199.66.68.112,199.66.68.113,199.66.68.114,199.66.68.115,199.66.68.116,199.66.68.117");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        
        
        HTable table = new HTable(configuration,tablename);
        Get get = new Get(rowKey.getBytes());
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(col));
        Result rs = table.get(get);
        byte[] bs = null;
        if(!rs.isEmpty()) {
        	//bs为图片的字节流
        	//保存get result的结果，字节数组形式
        	bs = rs.getValue(Bytes.toBytes(family), Bytes.toBytes(col));

            table.close();
        }
        if (bs == null) {
			return bs+"";
		}
        
        return Base64Utils.encodeToString(bs);
}
	
	public static void main(String[] args) {
		System.setProperty("hadoop.home.dir", "F:\\hadoop-common-2.2.0-bin-master");
		HBASEUtil hbase = new HBASEUtil();
		try {
			byte[] testhbase = hbase.GetPicture("1", "test_pic", "pic", "picture");
 			System.out.println(testhbase.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
