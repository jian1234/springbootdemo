package com.example.springbootdemo.util;

import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.Learn_socket.server.factory.ClientSocketFactory;


public class SocketUtil {

	public static void getAddress(Socket socket) {
		ShowTimeUtil.ShowTime("连接的客户端ip信息："
				+ socket.getInetAddress().getHostAddress());

	}

	public static Socket getSocket(String address, int port) throws Exception {
		SSLSocketFactory factory = ClientSocketFactory.getSocketFactory();
		SSLSocket sslSocket = (SSLSocket) factory.createSocket();
		sslSocket.connect(new InetSocketAddress(address, port),
				10 * 1000);
		sslSocket.setSoTimeout(30000);
		return sslSocket;
	}

}
