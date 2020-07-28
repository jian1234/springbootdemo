package com.Learn_socket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.query.criteria.internal.path.CollectionAttributeJoin.TreatedCollectionAttributeJoin;
import org.springframework.web.context.ContextLoaderListener;

public class FileServer extends HttpServlet{
         private ExecutorService executorService;//线程池
         private int port = 8899;//监听端口
         private ServerSocket server;
         public FileServer(int port)
         {
                 this.port = port;
//                 //创建线程池，池中具有(cpu个数*50)条线程
                 executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 50);
         }
         
         
         /**
          * 启动服务
          * @throws Exception
          */
         public void start() throws Exception
         {
        	     System.out.println("开启");
                 server = new ServerSocket(8899);//实现端口监听
                 while(true){
		             try{
		               Socket socket = server.accept();
		               executorService.execute(new SocketTask(socket));//为支持多用户并发访问，采用线程池管理每一个用户的连接请求
		             }catch (Exception e){
		                 e.printStackTrace();
		             }
                 }
         }
         
         private class SocketTask implements Runnable{
            private Socket socket = null;
            public SocketTask(Socket socket){
                    this.socket = socket;
            }
            public void run(){
            	InputStream inStream = null;
            	OutputStream outStream = null;
            	RandomAccessFile fileOutStream=null;
                try{
                System.out.println("FileServer accepted connection "+ socket.getInetAddress()+ ":"+ socket.getPort());
                inStream = socket.getInputStream();
                if(true){
                        if(inStream.available() > 0) {
                        	System.out.println("有数据响应");
                        }else if(inStream.available() == 0) {
                        	System.out.println("说明没有响应数据,可能是对方已经断开连接,");
                        }
                        //***************************上面是对协议头的处理，下面正式接收数据***************************************
                        //向客户端请求传输数据
                        outStream = socket.getOutputStream();
                        String response = "sourceid="+ 1+ ";position="+ 1222;
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
        	            String requestData = bufferedReader.readLine();
        	            System.out.println("接收到客户端的请求数据：" + requestData);
        	            //创建PrintWriter发送服务端响应数据，接收数据流对象也来自Socket
        	            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        	            //写入响应数据
        	            printWriter.println("服务端已接收到你的请求，返回码为200！");
        	            printWriter.flush();
        	            //关闭资源
        	            printWriter.close();
        	            bufferedReader.close();
        	            socket.close();
        	            server.close();
                        //移动文件指定的位置开始写入数据
                		}
                    }catch (Exception e){
                            e.printStackTrace();
                    }finally{
		                    try{
		                        if(socket!=null && !socket.isClosed()) {
		                   //     	socket.close();
		                        }
		                        if(inStream!=null){
		                        	inStream.close();
		                        }
		                        if(outStream!=null){
		                        	outStream.close();
		                        }
		                        if(fileOutStream!=null){
		                        	fileOutStream.close();
		                        }
		                    } catch (IOException e){
		                            e.printStackTrace();
		                    }
                } 
            }
            
         } 

         @Override
         public void init(){
        	 try{
         	    FileServer fs = new FileServer(8899);
         	    fs.start();

         	 }catch(Exception ex){
         		 ex.printStackTrace();
         	 } 
         }  
         public static void main(String[] args) {
        	 try{
        	    FileServer fs = new FileServer(8899);
        	    fs.start();

        	 }catch(Exception ex){
        		 ex.printStackTrace();
        	 }
        	 
		}
         
}
