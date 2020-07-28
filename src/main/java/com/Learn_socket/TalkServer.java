package com.Learn_socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TalkServer {
	private static String str = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TalkServer ts = new TalkServer();
//		ts.execesff();
	}

	/*public void execesff() {
		int numTasks = 7;
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < numTasks; i++) {
			str = i + "QQ";
			System.out.println(str);
			try {
				exec.execute(new createTask(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("===" + i + "==");
		}
	}*/

	public void SendMsg(byte[] sendmsg) {
		ExecutorService exec = Executors.newCachedThreadPool();
			try {
				exec.execute(new createTask(sendmsg));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	class createTask implements Runnable {
		Socket socket;
		PrintWriter os;

		BufferedReader is;
		byte[] sendmsg = null;

		public createTask(byte[] sendmsg) {
			this.sendmsg = sendmsg;
		}

		public void run() {

			try {
				Socket socket = new Socket("127.0.0.1", 8899);
				// 向8899端口发出客户请求
				os = new PrintWriter(socket.getOutputStream());
				// 由Socket对象得到输出流，并构造PrintWriter对象
				is = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				Map<String, Object> map = new HashMap<String, Object>();
				byte[] message=sendmsg;
				DataOutputStream out;
		        out = new DataOutputStream(socket.getOutputStream());
		        int length = 1024;//定义每次发送的数组长度
			    byte[] bytes = new byte[length];
			    System.out.println(message.length/length);
			    int sendnum = message.length/length+1;
			    //循环发送
			    for (int i = 0; i <sendnum; i++) {
			    	if ((i+1)*length<=message.length-1) {
			    		if (i == 0) {
			    			bytes = Arrays.copyOfRange(message, i*length, (i+1)*length);
						}else {
							bytes = Arrays.copyOfRange(message, i*length+1, (i+1)*length+1);
						}
					}else {
						System.out.println(i*length);
						System.out.println(message.length-1);
						if (i == 0) {
							bytes = Arrays.copyOfRange(message, i*length, message.length);
						}else {
							bytes = Arrays.copyOfRange(message, i*length+1, message.length);
						}
						
					}
			    	out.write(bytes,0,bytes.length);
				}

		        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		        out.write(message,0,message.length);
		        String responseData = bufferedReader.readLine();
		        System.out.println("接收到服务端的响应数据为：" + responseData);
		        //关闭资源
		        out.close();
		        bufferedReader.close();
//		        s.close();
		        if (responseData.indexOf("200")!=-1) {
					System.out.println("成功");
				}else {
					System.out.println("失败");
				}

			} catch (Exception e) {
				System.out.println("Error" + e);
				// 出错，则打印出错信息
				e.printStackTrace();
			} finally {
				if (os != null) {
					os.close(); // 关闭Socket输出流
				}
				if (is != null) {
					try {
						is.close(); // 关闭Socket输入流
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (socket != null && !socket.isClosed()) {// 关闭socket
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}

}
