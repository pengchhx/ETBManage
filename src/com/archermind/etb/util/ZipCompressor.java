package com.archermind.etb.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
/**
 * ZIP压缩操作帮助类
 * 
 * @author 002594王亮
 * @version v1.0,2013.07.11
 * @see
 * @since v1.0
 */
public class ZipCompressor {
	private File zipFile;  
	  
	/**
	 * Description: 构造方法
	 * 
	 * @Version1.0
	 * @param pathName 压缩后存放路径及压缩包名称
	 * @return 
	 * @throws Exception 
	 */
    public ZipCompressor(String pathName) {  
        zipFile = new File(pathName);  
    }  
      
    /**
	 * Description: ZIP压缩方法
	 * 
	 * @Version1.0
	 * @param srcPathName 压缩内容源路径
	 * @return 
	 * @throws Exception 
	 */
    public void compress(String srcPathName) {  
        File srcdir = new File(srcPathName);  
        if (!srcdir.exists()) { 
            throw new RuntimeException(srcPathName + "不存在！");  
        }
        Project prj = new Project();  
        Zip zip = new Zip();  
        zip.setProject(prj);  
        zip.setDestFile(zipFile);  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(prj);  
        fileSet.setDir(srcdir);  
      //fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");  
        //fileSet.setExcludes(...); 排除哪些文件或文件夹
        zip.addFileset(fileSet);  
          
        zip.execute();  
    }  
}
