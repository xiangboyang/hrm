package org.fkjava.hrm.common.io;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 文件操作工具类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-6 上午9:53:29
 * @version 1.0
 */
public final class FileUtil {
	
	/**
	 * 文件上传
	 * @param doc 原文件
	 * @param docFileName 原文件名
	 * @param path 路径
	 * @return path + 新文件名
	 * @throws Exception 
	 */
	public static String uploadFile(File doc, String docFileName, String path) throws Exception {
		/** 获取项目部署路径 */
		String realPath = ServletActionContext.getServletContext().getRealPath(path);
		
		/** 生成新文件名 */
		String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(docFileName);
		/** 文件拷贝 */
		FileUtils.copyFile(doc, new File(realPath + File.separator + newFileName));
		return path + "/" + newFileName;
	}
	
	/**
	 * 删除部署路径的下的文件
	 * @param oldUrl
	 */
	public static void deleteFile(String oldUrl) {
		/** 获取项目部署路径 */
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File dir = new File(realPath + oldUrl);
		// 该文件存在并且是个文件
		if (dir.exists() && dir.isFile()){
			dir.delete(); // 删除文件
		}
	}
	
}
