package com.archermind.etb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

public class FileUpload {

	//public static String FILEPATH = File.separator + "work" + File.separator
			//+ "EtbFileClient" + File.separator;
	public static String FILEPATH = ServletActionContext.getServletContext().getRealPath(
			File.separator) + Constant.AD_FILE_UPLOAD_FOLDER + File.separator;

	/**
	 * 把文件写到挂载的文件服务器
	 * 
	 * @param is
	 *            文件流
	 * @param path
	 *            文件服务器的子目录
	 * @param filename
	 *            文件名称
	 * @return 成功返回true，否则返回false
	 */
	public static boolean upToFileServer(InputStream is, String path,
			String filename) {
		path = FILEPATH + path + File.separator;
		File etbDir = new File(path);
		if (!etbDir.exists()) {
			etbDir.mkdirs();
		}
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path + filename);
			byte[] b = new byte[1024 * 1024 * 10];
			int a;
			while ((a = is.read(b)) != -1) {
				os.write(b, 0, a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return true;
	}

	/**
	 * 获取文件大小
	 * 
	 * @author 003383 陈然
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static long getFileSize(String path, String fileName) {

		// 定义文件默认大小
		long fileSize = 0;

		try {
			// 文件路径
			String filePath = FILEPATH + path + File.separator + fileName;

			// 获取文件
			File file = new File(filePath);

			if (file != null) {
				fileSize = file.length();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileSize;
	}

}
