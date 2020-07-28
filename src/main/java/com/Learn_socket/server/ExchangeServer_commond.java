package com.Learn_socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import com.Learn_socket.server.factory.ServerSocketFactory;
import com.Learn_socket.server.handler.IoHandler_commond;


/***
 * socket 服务端线程
 */

public class ExchangeServer_commond extends Thread {
	private boolean isRunning = true;// 控制线程启停
	private int port;
	SSLServerSocket serverSocket = null;

	public ExchangeServer_commond(int port) {
		super();
		this.port = port;
	}

	@Override
	public void run() {
		super.run();
		try {
			// 创建工厂对象
			SSLServerSocketFactory serverSocketFactory = ServerSocketFactory
					.getServerSocketFactory();

			serverSocket = (SSLServerSocket) serverSocketFactory
					.createServerSocket(port);
			serverSocket.setNeedClientAuth(true);// 需要客户端验证
			//MainApp.showMsg("启动了commond线程  port：" + port);
			System.out.println("启动了commond线程  port：" + port);
			while (true) {
				SSLSocket sslSocket = (SSLSocket) serverSocket.accept();
				if (!isRunning) {
					break;
				}
				new IoHandler_commond(sslSocket).revMsg();

			}
			if (!isRunning && serverSocket != null) {
				try {
					serverSocket.close();
					System.out.println("socekt服务关闭了");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("关闭了 commond线程");

	}

	public void stopServer() {
		this.setRunning(false);
		try {
			new Socket("localhost", port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		ExchangeServer_commond exchangeServer = new ExchangeServer_commond(8899);
		exchangeServer.start();

	}
}
