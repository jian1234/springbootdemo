package com.Learn_socket.server.factory;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.Learn_socket.socket.common.AchieveData;

public class ClientSocketFactory {

	public static SSLSocketFactory getSocketFactory() throws Exception {

		String clientPwd = AchieveData.clientPwd;
		String clientStorePwd = AchieveData.clientStorePwd;
		String clientStrustPwd = AchieveData.clientStrustPwd;
		String clientStoreFile = AchieveData.clientStoreFile;
		String clientStrustFile = AchieveData.clientStrustFile;

		KeyStore clientKeyStore = KeyStore.getInstance("JKS");
		clientKeyStore.load(new FileInputStream(clientStoreFile),
				clientStorePwd.toCharArray());

		KeyStore clientTrustKeyStore = KeyStore.getInstance("JKS");
		clientTrustKeyStore.load(new FileInputStream(clientStrustFile),
				clientStrustPwd.toCharArray());

		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
				.getDefaultAlgorithm());
		kmf.init(clientKeyStore, clientPwd.toCharArray());

		TrustManagerFactory tmf = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(clientTrustKeyStore);

		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		return sslContext.getSocketFactory();

	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getSocketFactory());
	}

}
