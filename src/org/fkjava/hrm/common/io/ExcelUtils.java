package org.fkjava.hrm.common.io;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 操作Excel工具类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-6 下午4:47:42
 * @version 1.0
 */
public final class ExcelUtils {
	/**
	 * 导出Excel方法
	 * @param fileName 生成Excel的文件名
	 * @param sheetName 工作单的名称
	 * @param titles 第一行标题
	 * @param data 数据
	 * @param response 响应对象
	 */
	public static void exportExcel(String fileName, String sheetName, String[] titles, 
				List<?> data, HttpServletResponse response) throws Exception{
		/** 创建工作簿 */
		HSSFWorkbook workbook = new HSSFWorkbook();
		/** 创建工作单 */
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		/** 创建第一行作为标题行 */
		HSSFRow row = sheet.createRow(0);
		/** 创建第一行中所有单元格 */
		for (int i = 0; i < titles.length; i++){
			HSSFCell cell = row.createCell(i);
			/** 设置单元格的内容 */
			cell.setCellValue(titles[i]);
		}
		
		/** 用集合创建行，集合中对象的属性创建单元格 */
		for (int i = 0; i < data.size(); i++){
			/** 创建行 */
			row = sheet.createRow(i + 1);
			/** 获取对象 */
			Object obj  = data.get(i);
			/** 获取Field */
			Field[] fields = obj.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++){
				/** 创建单元格 */
				HSSFCell cell = row.createCell(j);
				/** 设置访问权限 */
				fields[j].setAccessible(true);
				/** 获取该字段的值 */
				Object result = fields[j].get(obj);
				/** 设置单元格的内容 */
				cell.setCellValue(result == null ? "" : result.toString());
			}
		}
		
		/** 设置响应头 */
		fileName = URLDecoder.decode(fileName, "gbk");
		fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		/** 输出 */
		workbook.write(response.getOutputStream());
	}
}
