package com.archermind.etb.util;


import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.archermind.etb.common.SystemException;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;
/**
 * 
 * APK信息解析工具
 * 
 * @author  002538   chi.zhang
 * @version 1.0, 2013.07.19
 * @see 
 * @since 1.0
 * 
 */
public class AndroidManifestParser {
	//Android配置文件名称
	private static final String FILE_ANDROIDMANIFEST_XML = "AndroidManifest.xml";
	//AndroidManifest中的versionName参数名
	private static final String PARAM_VERSIONNAME = "versionName";
	//AndroidManifest中的versionCode参数名
	private static final String PARAM_VERSIONCODE = "versionCode";
	//AndroidManifest中的package参数名
	private static final String PARAM_PACKAGE = "package";
	
	
	/**
	 * 输入需要解析的APK文件对象，输出对应的ApkInfo对象
	 * @param apkFile 待解析的APK文件
	 * @return ApkInfo 封装了解析后数据的信息对象
	 * @throws IOException 
	 */
	public ApkInfo getApkInfo(File apkFile) throws IOException {
		ApkInfo apkInfo = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(apkFile);
			Enumeration enumeration = zipFile.entries();
			ZipEntry zipEntry = null;
			apkInfo = new ApkInfo();
			while (enumeration.hasMoreElements()) {
				zipEntry = (ZipEntry) enumeration.nextElement();
				if (!zipEntry.isDirectory()) {

					if (FILE_ANDROIDMANIFEST_XML.equals(zipEntry.getName())) {
						
						AXmlResourceParser parser = new AXmlResourceParser();
						parser.open(zipFile.getInputStream(zipEntry));
						
						while (true) {
							int type = parser.next();
							if (type == XmlPullParser.END_DOCUMENT) {
								break;
							}
							switch (type) {
							case XmlPullParser.START_TAG: {
								
								int attrCount = parser.getAttributeCount();
								for (int i = 0; i != attrCount; ++i) {
									if (PARAM_VERSIONNAME.equals(parser.getAttributeName(i))) {
										apkInfo.setVersionName(getAttributeValue(parser, i));
									} else if (PARAM_PACKAGE.equals(parser.getAttributeName(i))) {
										apkInfo.setPackageName(getAttributeValue(parser, i));
									} else if (PARAM_VERSIONCODE.equals(parser.getAttributeName(i))) {
										apkInfo.setVersionCode(getAttributeValue(parser, i));
									}
								}
							}
							}
						}
					}
				}
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			throw new SystemException("Error parse XML.", e);
		} finally {
			if(zipFile!=null){
				zipFile.close();
			}
		}
		
		return apkInfo;
	}
	/**
	 * 获取标签节点中对应参数的值
	 * @param parser
	 * @param index
	 * @return
	 */
	private String getAttributeValue(AXmlResourceParser parser, int index) {
		int type = parser.getAttributeValueType(index);
		int data = parser.getAttributeValueData(index);
		if (type == TypedValue.TYPE_STRING) {
			return parser.getAttributeValue(index);
		}
		if (type == TypedValue.TYPE_ATTRIBUTE) {
			return String.format("?%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_REFERENCE) {
			return String.format("@%s%08X", getPackage(data), data);
		}
		if (type == TypedValue.TYPE_FLOAT) {
			return String.valueOf(Float.intBitsToFloat(data));
		}
		if (type == TypedValue.TYPE_INT_HEX) {
			return String.format("0x%08X", data);
		}
		if (type == TypedValue.TYPE_INT_BOOLEAN) {
			return data != 0 ? "true" : "false";
		}
		if (type == TypedValue.TYPE_DIMENSION) {
			return Float.toString(complexToFloat(data))
					+ DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type == TypedValue.TYPE_FRACTION) {
			return Float.toString(complexToFloat(data))
					+ FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
		}
		if (type >= TypedValue.TYPE_FIRST_COLOR_INT
				&& type <= TypedValue.TYPE_LAST_COLOR_INT) {
			return String.format("#%08X", data);
		}
		if (type >= TypedValue.TYPE_FIRST_INT
				&& type <= TypedValue.TYPE_LAST_INT) {
			return String.valueOf(data);
		}
		return String.format("<0x%X, type 0x%02X>", data, type);
	}

	private String getPackage(int id) {
		if (id >>> 24 == 1) {
			return "android:";
		}
		return "";
	}

	private float complexToFloat(int complex) {
		return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
	}

	private final float RADIX_MULTS[] = { 0.00390625F, 3.051758E-005F,
			1.192093E-007F, 4.656613E-010F };
	private final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in",
			"mm", "", "" };
	private final String FRACTION_UNITS[] = { "%", "%p", "", "", "", "", "", "" };
}
