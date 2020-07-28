package com.Learn_socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.Learn_socket.server.handler.IoHandler_data;



/***
 * socket 服务端线程
 */

public class ExchangeServer_data {
	private boolean isRunning = true;// 控制线程启停
	private int port;

	public ExchangeServer_data(int port) {
		super();
		this.port = port;
	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("有客户端来连接");
				new IoHandler_data(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		ExchangeServer_data exchangeServer = new ExchangeServer_data(8899);
		exchangeServer.run();

	}
}
