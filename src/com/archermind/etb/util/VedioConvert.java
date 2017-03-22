package com.archermind.etb.util;

import java.io.File;
import java.util.logging.Logger;

/**
 * 提取视频缩略图
 * 
 * @author chujunyi
 * @version v1.0,2013.10.22
 * @see
 * @since v1.0
 */
public class VedioConvert {

	public static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 提取视频中某一帧的缩略图
	 * 
	 * @param oldfilepath
	 *            :c:\\ffmpeg\\input\\zaibiekangqiao.mp4
	 * @param newfilepath
	 *            :c:\\ffmpeg\\output\\azbkq.jpg
	 * @return
	 */
	public static boolean processThumbnail(String oldfilepath,
			String newfilepath) {
		// 检查视频文件是否存在
		if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			Logger.getLogger(VedioConvert.class.getName()).info(
					oldfilepath + " is not file");
			return false;
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			// 生成缩略图
			Process proce = null;
			String cmd = "";
			// ffmpeg参数设置:注意，上传到Lunix服务器上时盘符要去掉，".exe"要去掉："ffmpeg   -i "
			String cut = "ffmpeg   -i   "
					+ oldfilepath
					+ "   -y   -f   image2   -ss   1   -t   0.001   -s   200x200  "
					+ newfilepath;
			String cutCmd = cmd + cut;
			proce = runtime.exec(cutCmd);

			return true;
		} catch (Exception e) {
			Logger.getLogger(VedioConvert.class.getName()).info(
					oldfilepath + "  generate thumbnail failed!");
			e.printStackTrace();
			return false;
		}
	}

}
