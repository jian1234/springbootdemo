package com.Learn_socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Test_three.AESCoder;
import com.sun.tools.javac.code.Attribute.Array;

public class Client {
	
	public boolean SendMsg_string(byte[] message) throws UnknownHostException, IOException {
		
            //创建Socket用来发起请求，设置请求IP为本机，端口号为8080
            Socket socket = new Socket("127.0.0.1",8080);
            //通过Socket的流对象创建PrintWriter用于发送请求数据，创建BufferedReader用于接收服务端响应数据
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
//            String requestData = "这是从客户端socket发送出来的数据2";
            System.out.println(message.length);
//            printWriter.println(message.toString());
            printWriter.write(message.toString());
            System.out.println(message.toString());
            printWriter.flush();
            //接收服务端响应数据
            String responseData = bufferedReader.readLine();
            System.out.println("接收到服务端的响应数据为：" + responseData);
            //关闭资源
            printWriter.close();
            bufferedReader.close();
            socket.close();
            if (responseData.indexOf("200")!=-1) {
				return true;
			}else {
				return false;
			}
    }
	public Socket create() {
		Socket s = null;
		try {
			 s = new Socket("127.0.0.1",9020);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s ;
	}
	
	public boolean SendMsg(byte[] message,Socket s) throws UnknownHostException, IOException {
		//创建一个Socket对象，连接IP地址为192.168.101.56的服务器的9002端口
		Logger logger = LoggerFactory.getLogger(getClass());
//		Socket s = null;
//		try {
//			s = new Socket("127.0.0.1",9020);
//		} catch (Exception e) {
//			logger.error("socket连接失败");
//			return false;
//		}
        
        DataOutputStream out;
        out = new DataOutputStream(s.getOutputStream());
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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        out.write(message,0,message.length);
        String responseData = bufferedReader.readLine();
        System.out.println("接收到服务端的响应数据为：" + responseData);
        //关闭资源
        out.close();
        bufferedReader.close();
//        s.close();
        if (responseData.indexOf("200")!=-1) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean SendMsg(byte[] message) throws UnknownHostException, IOException {
		//创建一个Socket对象，连接IP地址为192.168.101.56的服务器的9002端口
		Socket s = null;
		try {
			s = new Socket("127.0.0.1",9020);
		} catch (Exception e) {
			return false;
		}
        
        DataOutputStream out;
        out = new DataOutputStream(s.getOutputStream());
        
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
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out.write(message,0,message.length);
        System.out.println("消息长度："+message.length);
        String responseData = bufferedReader.readLine();
        System.out.println("接收到服务端的响应数据为：" + responseData);
        //关闭资源
        out.close();
        bufferedReader.close();
        s.close();
        if (responseData.indexOf("200")!=-1) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
        Client client = new Client();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "qweasd");
        map.put("2", "qweasd");
        map.put("3", "qweasd");
        map.put("4", "qweasd");
        map.put("5", "qweasd");
        map.put("6", "qweasd");
        map.put("7", "qweasd");
        map.put("17", "qweasd");
        map.put("18", "qweasd");
        System.out.println(map.toString().getBytes().length);
        AESCoder Aesc = new AESCoder();
        try {
        	for (int i = 0; i < 10; i++) {
        		Object aa = Aesc.getAlldata(map);
        		boolean sendMsg2 = client.SendMsg(aa.toString().getBytes());
        		System.out.println(sendMsg2);
			}
			/*boolean sendMsg2 = client.SendMsg(map.toString().getBytes());
			System.out.println(sendMsg2);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
