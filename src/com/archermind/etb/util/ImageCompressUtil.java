package com.archermind.etb.util;

import java.io.*;
import java.util.logging.Logger;
import java.awt.*;

//import com.sun.image.codec.jpeg.*;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

import java.awt.image.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

/**
 * 图片等比压缩
 * 
 * @author wangliang
 * @version v1.0,2013.08.20
 * @see
 * @since v1.0
 */
public class ImageCompressUtil {
	/**
	 * 图片压缩，留白
	 * 
	 * @param file
	 *            源图片
	 * @param fileName
	 *            图片名称
	 * @param width目标宽
	 * @param height目标高
	 * @param per
	 *            压缩百分比
	 */
	public static boolean compressImg(File file, String fileName, int width,
			int height, float per) {
		boolean result = true;

		Image src;
		try {
			src = javax.imageio.ImageIO.read(file); // 构造Image对象
			int oldWidth = src.getWidth(null); // 得到源图宽
			int oldHeight = src.getHeight(null);// 得到源图高
			int newWidth = 0;// 缩放后的图宽
			int newHeight = 0; // 缩放后的图高

			// 为等比缩放计算输出的图片宽度及高度
			double w2 = (oldWidth * 1.00) / (width * 1.00);// 宽的比例
			double h2 = (oldHeight * 1.00) / (height * 1.00);// 高的比例

			// 根据缩放比率大的进行缩放控制
			if (w2 > h2) {
				newWidth = width;
				newHeight = (int) (oldHeight / w2);
			} else {
				if (w2 < h2) {
					newWidth = (int) (oldWidth / h2);
					newHeight = height;
				} else {
					newWidth = width;
					newHeight = height;
				}
			}

			// 绘制缩放后的图片
			BufferedImage tag = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_ARGB);
			tag.getGraphics().drawImage(
					src.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);

			// 创建画布,留白
			BufferedImage canvas = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D g = canvas.createGraphics();
			g.setColor(Color.white);

			if (w2 > h2) {
				g.fillRect(0, 0, width, height);
				g.drawImage(tag, 0, (height - newHeight) / 2, newWidth,
						newHeight, Color.white, null);
			} else {
				if (w2 < h2) {
					g.fillRect(0, 0, width, height);
					g.drawImage(tag, (width - newWidth) / 2, 0, newWidth,
							newHeight, Color.white, null);
				} else {
					newWidth = width;
					newHeight = height;
					g.fillRect(0, 0, width, height);
					g.drawImage(src.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);
				}
			}

			g.dispose();

			FileOutputStream newimage = new FileOutputStream(fileName); // 输出到文件流
			try {
				// png图片处理
				if ("PNG".equalsIgnoreCase(file.getName().substring(
						file.getName().lastIndexOf(".") + 1,
						file.getName().length()))) {
					ImageIO.write(canvas, "png", file);
				} else {
//					JPEGImageEncoder encoder = JPEGCodec
//							.createJPEGEncoder(newimage);
//					JPEGEncodeParam jep = JPEGCodec
//							.getDefaultJPEGEncodeParam(canvas);
//					// 压缩质量
//					jep.setQuality(per, true);
//					encoder.encode(canvas, jep);// 近JPEG编码
					
					JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
					ImageOutputStream ios = ImageIO.createImageOutputStream(newimage);
					imageWriter.setOutput(ios);
					// and metadata
					IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(canvas), null);
					imageWriter.write(imageMetaData,new IIOImage(canvas, null, null), null);
					ios.close();
					imageWriter.dispose();
				}
			} catch (Exception e) {
				result = false;
			} finally {
				newimage.close();
			}

		} catch (Exception ex) {
			Logger.getLogger(ImageCompressUtil.class.getName()).info(
					"Image Compress Failed:" + ex);
			result = false;
		}

		return result;
	}

	/**
	 * 图片压缩，不留白
	 * 
	 * @param file
	 *            源图片
	 * @param fileName
	 *            图片名称
	 * @param width目标宽
	 * @param height目标高
	 * @param per
	 *            压缩百分比
	 */
	public static boolean compressImgNoWhite(File file, String fileName,
			int width, int height, float per) {
		boolean result = true;
		BufferedImage tag;

		Image src;
		try {
			src = javax.imageio.ImageIO.read(file); // 构造Image对象
			int oldWidth = src.getWidth(null); // 得到源图宽
			int oldHeight = src.getHeight(null);// 得到源图高
			int newWidth = 0;// 缩放后的图宽
			int newHeight = 0; // 缩放后的图高

			// 为等比缩放计算输出的图片宽度及高度
			double w2 = (oldWidth * 1.00) / (width * 1.00);// 宽的比例
			double h2 = (oldHeight * 1.00) / (height * 1.00);// 高的比例

			// 根据缩放比率大的进行缩放控制
			if (w2 > h2) {
				newWidth = width;
				newHeight = (int) (oldHeight / w2);
			} else {
				if (w2 < h2) {
					newWidth = (int) (oldWidth / h2);
					newHeight = height;
				} else {
					newWidth = width;
					newHeight = height;
				}
			}

			FileOutputStream newimage = new FileOutputStream(fileName); // 输出到文件流
			try {
				// png图片处理
				if ("PNG".equalsIgnoreCase(file.getName().substring(
						file.getName().lastIndexOf(".") + 1,
						file.getName().length()))) {
					// 绘制缩放后的图片
					tag = new BufferedImage(newWidth, newHeight,
							BufferedImage.TYPE_INT_ARGB);
					tag.getGraphics().drawImage(
							src.getScaledInstance(newWidth, newHeight,
									Image.SCALE_SMOOTH), 0, 0, null);
					ImageIO.write(tag, "png", file);
				} else {
					tag = new BufferedImage(newWidth, newHeight,
							BufferedImage.TYPE_INT_RGB);
					tag.getGraphics().drawImage(
							src.getScaledInstance(newWidth, newHeight,
									Image.SCALE_SMOOTH), 0, 0, null);
					
//					JPEGImageEncoder encoder = JPEGCodec
//							.createJPEGEncoder(newimage);
//					JPEGEncodeParam jep = JPEGCodec
//							.getDefaultJPEGEncodeParam(tag);
//					// 压缩质量
//					jep.setQuality(per, true);
//					encoder.encode(tag, jep);// 近JPEG编码
					JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
					ImageOutputStream ios = ImageIO.createImageOutputStream(newimage);
					imageWriter.setOutput(ios);
					// and metadata
					IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(tag), null);
					imageWriter.write(imageMetaData,new IIOImage(tag, null, null), null);
					ios.close();
					imageWriter.dispose();
				}

			} catch (Exception e) {
				result = false;
			} finally {
				newimage.close();
			}

		} catch (Exception ex) {
			Logger.getLogger(ImageCompressUtil.class.getName()).info(
					"Image Compress Failed:" + ex);
			result = false;
		}

		return result;
	}

}
