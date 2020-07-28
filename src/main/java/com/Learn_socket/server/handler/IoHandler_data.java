package com.Learn_socket.server.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * 数据传输 socket 服务
 * */
public class IoHandler_data {
	private Socket socket;
	// 保存文件的路径
	private static String filePath = "E:\\test\\receive";
	// 数字格式化
	private static DecimalFormat decimalFormat = null;

	public IoHandler_data() {

	}

	public IoHandler_data(Socket socket) {
		this.socket = socket;
	}

	static {
		// 设置数字格式，保留一位有效小数
		decimalFormat = new DecimalFormat("#0.0");
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		decimalFormat.setMinimumFractionDigits(1);
		decimalFormat.setMaximumFractionDigits(1);
	}

	public void start() {
		DataInputStream dis = null;// 数据输入流
		FileOutputStream fos = null; // 文件输出流
		DataOutputStream dataOutputStream = null;
		try {
			// 从socket中获取输入流信息
			dis = new DataInputStream(socket.getInputStream());
			// 获取文件名
			String fileName = dis.readUTF();
			// 获取文件大小
			Long fileLength = dis.readLong();
			// 创建相应的文件夹
			File directory = new File(filePath);
			// 检查文件夹是否存在，不存在则生成相应的文件夹
			if (!directory.exists()) {
				directory.mkdirs();
			}
			System.out.println("==============开始接收文件================");
			// 创建新的文件
			File newFile = new File(directory.getAbsolutePath()
					.concat(File.separator).concat(fileName));
			fos = new FileOutputStream(newFile);
			dataOutputStream = new DataOutputStream(fos);
			// 设置读取文件的大小
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
				dataOutputStream.write(bytes, 0, length);
				dataOutputStream.flush();
			}
			System.out.println("文件接收成功 [File Name：" + fileName + "] [Size："
					+ getFormatFileSize(fileLength) + "]");
			System.out.println("==============结束接收文件================");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (dis != null)
					dis.close();
				if (dataOutputStream != null)
					dataOutputStream.close();
				socket.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * 格式化文件大小
	 * 
	 * @param length
	 * @return
	 */
	private String getFormatFileSize(long length) {
		double size = ((double) length) / (1 << 30);
		if (size >= 1) {
			return decimalFormat.format(size) + "GB";
		}
		size = ((double) length) / (1 << 20);
		if (size >= 1) {
			return decimalFormat.format(size) + "MB";
		}
		size = ((double) length) / (1 << 10);
		if (size >= 1) {
			return decimalFormat.format(size) + "KB";
		}
		return length + "B";
	}

}
