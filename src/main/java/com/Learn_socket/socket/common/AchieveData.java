package com.Learn_socket.socket.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AchieveData {

	public static String clientPwd;
	public static String clientStorePwd;
	public static String clientStrustPwd;
	public static String clientStoreFile;
	public static String clientStrustFile;
	public static String serverPwd;
	public static String serverStorePwd;
	public static String serverStrustPwd;
	public static String serverStoreFile;
	public static String serverStrustFile;

	static {
		Properties prop = new Properties();
		// 读取属性文件a.properties
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(
					"config\\certdata.properties"));
			prop.load(in);
			clientPwd = "1";
			clientStorePwd = prop.getProperty("clientStorePwd");
			clientStrustPwd = prop.getProperty("clientStrustPwd");
			clientStoreFile = prop.getProperty("clientStoreFile");
			clientStrustFile = prop.getProperty("clientStrustFile");
			serverPwd = prop.getProperty("serverPwd");
			serverStorePwd = prop.getProperty("serverStorePwd");
			serverStrustPwd = prop.getProperty("serverStrustPwd");
			serverStoreFile = prop.getProperty("serverStoreFile");
			serverStrustFile = prop.getProperty("serverStrustFile");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		/*System.out.println("clientPwd:" + clientPwd);
		System.out.println("clientStorePwd:" + clientStorePwd);
		System.out.println("clientStrustPwd:" + clientStrustPwd);
		System.out.println("clientStoreFile:" + clientStoreFile);
		System.out.println("clientStrustFile:" + clientStrustFile);
		System.out.println("serverPwd:" + serverPwd);
		System.out.println("serverStorePwd:" + serverStorePwd);
		System.out.println("serverStrustPwd:" + serverStrustPwd);
		System.out.println("serverStoreFile:" + serverStoreFile);
		System.out.println("serverStrustFile:" + serverStrustFile);
*/
	}

}
