package com.Learn_socket.server.factory;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.Learn_socket.socket.common.AchieveData;

public class ServerSocketFactory {

	public static SSLServerSocketFactory getServerSocketFactory()
			throws Exception {
		// String serverKeyStoreFile = "c:\\_tmp\\catserver.keystore";
		// String serverKeyStorePwd = "catserverks";
		// String catServerKeyPwd = "catserver";
		// String serverTrustKeyStoreFile = "c:\\_tmp\\catservertrust.keystore";
		// String serverTrustKeyStorePwd = "catservertrustks";
		String serverPwd = AchieveData.serverPwd;
		String serverStorePwd = AchieveData.serverStorePwd;
		String serverStrustPwd = AchieveData.serverStrustPwd;
		String serverStoreFile = AchieveData.serverStoreFile;
		String serverStrustFile = AchieveData.serverStrustFile;
		KeyStore keyServerStore = KeyStore.getInstance("JKS");
		keyServerStore.load(new FileInputStream(serverStoreFile),
				serverStorePwd.toCharArray());
		KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyServerStore, serverPwd.toCharArray());
		KeyStore serverTrustKeyStore = KeyStore.getInstance("JKS");
		serverTrustKeyStore.load(new FileInputStream(serverStrustFile),
				serverStrustPwd.toCharArray());
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(serverTrustKeyStore);
		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		sslContext.init(keyManagerFactory.getKeyManagers(),
				trustManagerFactory.getTrustManagers(), null);
		SSLServerSocketFactory sslServerSocketFactory = sslContext
				.getServerSocketFactory();
		return sslServerSocketFactory;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getServerSocketFactory());
	}

}
