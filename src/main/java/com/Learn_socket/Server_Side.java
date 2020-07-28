package com.Learn_socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server_Side {
	public void getString() throws IOException {
		while (true) {//死循环持续接受客户端内容
			PrintWriter printWriter= null;
			BufferedReader bufferedReader = null;
			ServerSocket serverSocket = null;
			Socket socket = null;
	        try {
	            //创建一个ServerSocket，绑定监听端口为8080
	            serverSocket = new ServerSocket(8080);
	            //调用accept()方法监听客户端请求，该方法是阻塞方法，程序会停留在这里直到有客户端请求服务端的8080接口
	            //Socket用于通信中的数据传输
	            socket = serverSocket.accept();
	            //创建BufferedReader接收来自客户端的请求数据，源数据流对象来自客户端发起请求的Socket
	            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            String requestData = bufferedReader.readLine();
	            System.out.println("接收到客户端的请求数据：" + requestData);
	            //创建PrintWriter发送服务端响应数据，接收数据流对象也来自Socket
	            printWriter = new PrintWriter(socket.getOutputStream());
	            //写入响应数据
	            printWriter.println("服务端已接收到你的请求，返回码为200！");
	            //使用flush()方法强制发送数据而不是等到缓冲区满了后才发送
	            printWriter.flush();
	            //关闭资源
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	        	printWriter.close();
	            bufferedReader.close();
	        	socket.close();
		        serverSocket.close();
	        }
	    }
			
	}
	public void getbyte() throws IOException {
		System.out.println("服务端已启动等待接收客户端消息......");
		ServerSocket ss = new ServerSocket(9020); //创建一个Socket服务器，监听9002端口 
		Socket s = null;
		while (true) {
			s = ss.accept();//利用Socket服务器的accept()方法获取客户端Socket对象。 
	        DataInputStream input=new DataInputStream(s.getInputStream());
	        byte[] buffer = new byte[20480];
	        //消息长度
	        PrintWriter printWriter  = null;
	        while(input.read() !=-1) {
	        	int rlength=input.read(buffer, 0, 20480);
	            System.out.println();
	            System.out.println("接收的消息长度:"+rlength);
	            //传输的实际byte[]
	            byte[] buffer1 = new byte[rlength];
	            for(int i=0;i<buffer1.length;i++){
	                buffer1[i]=buffer[i];
	            }
	        
	            String messageContent1=new String(buffer1,"UTF-8").toString().trim();
	            System.out.println("接收的消息（gbk转码）："+messageContent1);
	           
	            String messageContent=new String(buffer,0,rlength).toString().trim();
	            System.out.println("接收的消息："+messageContent);
	            printWriter = new PrintWriter(s.getOutputStream());
	            //写入响应数据
	            printWriter.println("200！");
	            //使用flush()方法强制发送数据而不是等到缓冲区满了后才发送
	            printWriter.flush();
	        }
	        
	        //关闭资源
	        if (printWriter!=null) {
	        	 printWriter.close();
			}
	       
	        input.close();
	        s.close();
			
		}
	}
	
//	public void testGetMessage() {
//		try {
//			ServerSocket serverSocket = new ServerSocket(9020);
//			while (true) {
//				Socket socket = serverSocket.accept();
//				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//				
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//				
//	}
	
	public static void main(String[] args) {
		Server_Side service = new Server_Side();
		try {
			service.getbyte();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
