package com.archermind.etb.util.rwexcel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.archermind.etb.common.SystemException;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.util.Constant;

/**
 * Excel文件解析类
 * 
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
public class ExcelOperator {

	/**
	 * 日志
	 */
	public static final Logger LOGGER = Logger.getLogger(ExcelOperator.class);

	/**
	 * 导出EXCEL文件模板目录
	 */
	private static final String DIR_TEMP = "template";

	/**
	 * POI方式导出excel文件数据到list XSSF支持excel2007
	 * 
	 * @param filePath
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static List<Object> getList(String filePath,
			AbstractExcelRow excelRow) throws Exception {
		// 输入流读取文件
		InputStream is = null;
		// 声明工作簿
		Workbook workbook = null;
		// 声明工作表
		Sheet sheet = null;
		// 声明行
		Row row = null;
		// 声明单元格
		Cell cell = null;
		// 声明单元格字符串数组
		String[] cellValue = null;

		List<Object> list = new ArrayList<Object>(512);
		try {
			is = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(is);
		} catch (Exception e) {
			LOGGER.error("找不到导入的EXCEL文件或者Excel解析异常", e);
			throw new SystemException(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("关闭输入流异常", e);
				}
			}
		}

		if (workbook != null) {
			sheet = workbook.getSheetAt(0);
			if (sheet != null) {
				int countNumRow = sheet.getLastRowNum();
				// 去掉第一行列表名称,遍历行
				for (int rowNum = 2; rowNum <= countNumRow; rowNum++) {
					row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}

					int countNumCell = row.getLastCellNum();
					if (countNumCell > 0) {
						cellValue = new String[countNumCell + 1];
						cellValue[countNumCell] = String.valueOf(rowNum + 1);
						// 遍历单元格，将单元格内容放入字符串数组中
						for (int cellNum = 0; cellNum < countNumCell; cellNum++) {
							cell = row.getCell(cellNum);
							cellValue[cellNum] = getValue(cell);
						}
						// 行保存到实体
						excelRow.set(cellValue);
						// 实体保存到list
						list.add(excelRow.get());
					}
				}
			}
		}
		return list;
	}

	/**
	 * 根据单元格数据类型返回相应的单元格内容
	 * 
	 * @param hssfCell
	 * @return
	 */
	private static String getValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		int key = cell.getCellType();
		String value = "";
		switch (key) {
		case Cell.CELL_TYPE_BOOLEAN:
			value = Boolean.toString(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = String.valueOf(cell.getDateCellValue());
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String temp = cell.getStringCellValue();
				// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
				if (temp.indexOf(".") > -1) {
					value = String.valueOf(new Double(temp)).trim();
				} else {
					value = temp.trim();
				}
			}
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue().trim();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		case Cell.CELL_TYPE_ERROR:
			value = "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			value = cell.getStringCellValue();
			if (value != null) {
				value = value.replaceAll("#N/A", "").trim();
			}
			break;
		}
		return value.trim();
	}

	/**
	 * 生成excle文件
	 * 
	 * @param map
	 * @param templateFileName
	 * @param clientFileName
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public static String generateExcel(String path, List<EtbClientDevice> list,
			String templateFileName, String clientFileName)
			throws ParsePropertyException, InvalidFormatException, IOException {
		int length = path.length();
		if (path.charAt(length - 1) == File.separatorChar) {
			path = path.substring(0, length - 1);
		}
		String templateFile = path + File.separator + Constant.DIR_ROOT + File.separator
				+ DIR_TEMP + File.separator + templateFileName;
		String filePath = path + File.separator + Constant.DIR_ROOT + File.separator
				+ Constant.DIR_DATA;
		File dataDir = new File(filePath);
		if (!dataDir.exists() && !dataDir.isDirectory()) {
			dataDir.mkdirs();
		}
		
		
		filePath += File.separator + clientFileName;
		List<List<EtbClientDevice>> listItems = new ArrayList<List<EtbClientDevice>>();
		List<String> sheetNames = new ArrayList<String>();
		int countList = list.size();
		int countSheet = countList%Constant.EXCEL_SHEET > 0 ? (countList/Constant.EXCEL_SHEET + 1) : countList/Constant.EXCEL_SHEET;
		for(int i = 0; i<countSheet; i++){
			int indexList = (i+1)*Constant.EXCEL_SHEET >= countList ? countList:(i+1)*Constant.EXCEL_SHEET;
			List<EtbClientDevice> listItem = new ArrayList<EtbClientDevice>();
			listItem = list.subList(i*Constant.EXCEL_SHEET, indexList);
			listItems.add(listItem);
			sheetNames.add("Sheet" + String.valueOf(i+1));
		}
		InputStream is = new BufferedInputStream(new FileInputStream(templateFile));
		XLSTransformer transformer = new XLSTransformer();
		@SuppressWarnings("rawtypes")
		HSSFWorkbook resultWorkbook =  (HSSFWorkbook) transformer.transformMultipleSheetsList(is,listItems, sheetNames, "deviceList", new HashMap(), 0);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
		resultWorkbook.write(os);
		os.flush();
		os.close();
		is.close();
		return filePath;
	}
}
