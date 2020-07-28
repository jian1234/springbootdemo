package com.Learn_socket.server.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;

import com.example.springbootdemo.util.ShowTimeUtil;
import com.example.springbootdemo.util.SocketUtil;


public class IoHandler_commond {

    private SSLSocket socket;

    public IoHandler_commond(SSLSocket socket) {
        this.socket = socket;
    }
    
    public void revMsg() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String revJson = "";
        String result="";
        try {
            SocketUtil.getAddress(socket);
            // 读取客户端数据
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            // 向客户端发送数据
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            // 读取客户端的信息
            revJson = reader.readLine();
            ShowTimeUtil.ShowTime("服务端读取的json：" + revJson);
            // 转到反向映射分发类
//            result = Reflect.GoReflect(revJson);
            // 获取请求方式
            result = result+"\r\n";
            writer.write(result);
            writer.flush();
        } catch (Exception e) {
            String str = "{\"message\":\"失败,失败原因：" + e.toString() + "\"}";
            try {
            	 // 获取请求方式
            	str = str+"\r\n";
                writer.write(str);
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                	reader.close();
                }
                if (writer != null) {
                	writer.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
