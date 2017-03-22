package com.archermind.etb.util;

import java.io.File;

/**
 * 公共处理方法
 * 
 * @author chujunyi
 * @version v1.0,2013.11.07
 * @see
 * @since v1.0
 */
public class StringUtil {
	
	/**
	 * 从本地目录中清除文件包括文件夹
	 * @param path
	 */
	public static void deleteAllFiles(File path) {  
	    if (!path.exists())  
	        return;  
	    if (path.isFile()) {  
	        path.delete();  
	        return;  
	    }  
	    File[] files = path.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        deleteAllFiles(files[i]);  
	    }  
	    path.delete();  
   }
}
