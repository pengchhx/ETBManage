
package com.archermind.etb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件读取相关工具类
 * 
 * @author 002611  王巍
 * @version v1.0, 2013-7-11 
 * @since v1.0
 */
public class PropertyUtil {
	private static final Logger log = Logger.getLogger(PropertyUtil.class);
	
	public static String readData(String key) {  
        Properties props = new Properties();
        InputStream in = null;
        try {
        	in = PropertyUtil.class.getResourceAsStream("/common.properties");
        	props.load(in);
        	String value = props.getProperty(key);
        	return value;
        } catch (Exception e) {
        	log.error("common properties file is not found.", e);
        }finally{
        	if(null!=in){
        		try {
					in.close();
				} catch (IOException e) {
					log.error("close inputStream failed.", e);
				}
        	}
        }
        return null;

    }  
}
