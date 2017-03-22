/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
package com.archermind.etb.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

@Deprecated
public class FtpUtil {
	private static FTPClient ftpClient = new FTPClient();
	private static String encoding = System.getProperty("file.encoding");
	public static String FILEPATH = System.getProperty("user.home")
			+ File.separator + "ETB" + File.separator;

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录,如果是根目录则为“/”
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            本地文件输入流
	 * @return 成功返回true，否则返回false
	 * @throws Exception
	 */
	public static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input)
			throws Exception {
		boolean result = false;
		InputStream is = null;
		// 先写文件到应用服务器
		boolean writeResult = FtpUtil.writeToLocal(input, filename);
		if (writeResult) {
			is = new FileInputStream(FILEPATH + filename);
		} else {
			return result;
		}

		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(url);

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// ftp.connect(url, port);// 连接FTP服务器
			// 登录
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("UTF-8");
			// 检验是否连接成功
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接失败");
				ftpClient.disconnect();
				return result;
			}
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 转移工作目录至指定目录下
			// boolean change = ftpClient.changeWorkingDirectory(path);
			if (path != null && !"".equals(path.trim())) {
				String[] pathes = path.split("/");
				if (pathes.length > 0) {
					// for(int i=1;i<pathes.length;i++){
					String onepath = path.substring(path.lastIndexOf("/") + 1,
							path.length());
					// String onepath=pathes[i];
					onepath = new String(onepath.getBytes("UTF-8"),
							"iso-8859-1");
					if (!ftpClient.changeWorkingDirectory(onepath)) {
						ftpClient.makeDirectory(onepath);
						ftpClient.changeWorkingDirectory(onepath);
					}
					// }
				}
			}
			// result = ftpClient.storeFile(new
			// String(filename.getBytes("UTF-8"),"iso-8859-1"), input);
			result = ftpClient.storeFile(filename, is);

			if (result) {
				System.out.println("上传成功!");
			}
			// ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// if (change) {
			// result = ftpClient.storeFile(new
			// String(filename.getBytes("GBK"),"iso-8859-1"), input);
			// if (result) {
			// System.out.println("上传成功!");
			// }
			// }
			ftpClient.logout();

			// write to local
			// write2Local();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			input.close();
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static OutputStream downFile(String url, int port, String username,
			String password, String remotePath, String fileName) {
		OutputStream result = null;
		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(url);

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// ftp.connect(url, port);// 连接FTP服务器
			// 登录
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("UTF-8");
			// 检验是否连接成功
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接失败");
				ftpClient.disconnect();
				return result;
			}

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 转移到FTP服务器目录至指定的目录下

			ftpClient.changeWorkingDirectory(new String(remotePath
					.getBytes(encoding), "iso-8859-1"));
			// 获取文件列表
			FTPFile[] fs = ftpClient.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					BufferedOutputStream buffOut = null;
					// File localFile = new File(remotePath + "\\" +
					// ff.getName());
					fileName = "d:\\" + fileName;

					buffOut = new BufferedOutputStream(new FileOutputStream(
							fileName));
					ftpClient.retrieveFile(ff.getName(), buffOut);

					// OutputStream is = new FileOutputStream(localFile);
					// result = is ;
					// ftpClient.retrieveFile(ff.getName(), buffOut);
					result = buffOut;
					buffOut.close();
				}
			}

			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String url, int port, String username,
			String password, String filename) throws Exception {
		boolean result = false;

		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(url);

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// ftp.connect(url, port);// 连接FTP服务器
			// 登录
			ftpClient.login(username, password);
			ftpClient.setControlEncoding("UTF-8");
			// 检验是否连接成功
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接失败");
				ftpClient.disconnect();
				return result;
			}

			result = ftpClient.deleteFile(filename);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}

	public static boolean writeToLocal(InputStream is, String filename) {
		File etbDir = new File(FILEPATH);
		if (!etbDir.exists()) {
			etbDir.mkdirs();
		}
		// File file =new
		// File(System.getProperty("user.home")+File.separator+"ETB"+File.separator+filename);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(FILEPATH + filename);
			byte[] b = new byte[1024 * 1024 * 10];
			int a;
			// System.out.println("Client开始写临时文件："+new Date());
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
	 * 获取FTP上文件的size
	 * 
	 * @author 陈然
	 * @param url
	 * @param port
	 * @param userName
	 * @param password
	 * @param path
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(String url, int port, String userName,
			String password, String path, String fileName) throws Exception {

		long fileSize = 0;

		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.connect(url);

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// ftp.connect(url, port);// 连接FTP服务器
			// 登录
			ftpClient.login(userName, password);
			ftpClient.setControlEncoding("UTF-8");
			// 检验是否连接成功
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("连接失败");
				ftpClient.disconnect();
				return fileSize;
			}

			ftpClient.changeWorkingDirectory(new String(
					path.getBytes(encoding), "iso-8859-1"));

			// 获取文件列表
			FTPFile[] fs = ftpClient.listFiles(fileName);
			if (fs != null && fs.length > 0) {
				fileSize = fs[0].getSize();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}

		return fileSize;
	}

}
