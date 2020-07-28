package com.example.springbootdemo.util;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;  
import java.net.URL; 


public class GetPictureByURL {

	public void saveToFile(String destUrl) {  
		 FileOutputStream fos = null;  
		 BufferedInputStream bis = null;  
		 HttpURLConnection httpUrl = null;  
		 URL url = null;  
		 int BUFFER_SIZE = 1024;  
		 byte[] buf = new byte[BUFFER_SIZE];  
		 int size = 0;  
		 try {  
			 url = new URL(destUrl);  
			 httpUrl = (HttpURLConnection) url.openConnection();  
			 httpUrl.connect();  
			 bis = new BufferedInputStream(httpUrl.getInputStream());  
			 fos = new FileOutputStream("E:\\test\\pic.jpg");  
			 while ((size = bis.read(buf)) != -1) {  
				 fos.write(buf, 0, size);  
			 }  
			 fos.flush();  
		 	} catch (IOException e) { 
		 		
		 	} catch (ClassCastException e) { 
		 		
		 	} finally {  
		 		try {  
		 			fos.close();  
		 			bis.close();  
		 			httpUrl.disconnect();  
		 		} catch (IOException e) {  
			 
		 		} catch (NullPointerException e) { 
			 
		 		}  
		 	}  
	 }  
	
	private static byte[] inputToByte(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	    byte[] buff = new byte[1024];
	    int rc;
	    while ((rc = inputStream.read(buff, 0, 1024)) > 0) {
	      swapStream.write(buff, 0, rc);
	    }
	    byte[] in2b = swapStream.toByteArray();
	    return in2b;
	  }

	public byte[] getPicByte(String destUrl) {
		 HttpURLConnection httpUrl = null;  
		 InputStream inputStream = null;
		 URL url = null;  
		 int BUFFER_SIZE = 1024;  
		 byte[] buf = new byte[BUFFER_SIZE];  
		 int size = 0;  
		 try {  
			 url = new URL(destUrl);  
			 httpUrl = (HttpURLConnection) url.openConnection();  
			 httpUrl.connect();  
			 inputStream = httpUrl.getInputStream();
			 
		 	} catch (IOException e) { 
		 		
		 	} catch (ClassCastException e) { 
		 		
		 	} finally {  
		 		try {  
		 			httpUrl.disconnect();  
		 		}  catch (NullPointerException e) { 
			 
		 		}  
		 	}  
		if (inputStream != null) {
			try {
				buf = this.inputToByte(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buf;
		
	} 
		 public static void main(String[] args) {  
		 GetPictureByURL dw=new GetPictureByURL();  
		 dw.saveToFile("http://localhost:9090/GISDSS_BD2/plugin/images/logo1.png");  
		 }  

}
