package org.fkjava.hrm.action.base;

import java.io.InputStream;
import java.net.URLDecoder;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 专门下载的Action
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-6 下午2:05:51
 * @version 1.0
 */
public class DownLoadAction extends ActionSupport {

	private static final long serialVersionUID = -3807708998063556454L;
	/** 定义下载的文件名 */
	private String downFileName;
	/** 定义文件的URL */
	private String fileUrl;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/** <param name="inputName">fileStream</param> */
	public InputStream getFileStream(){
		// 响应的数据编码： utf-8 --> iso8859-1 --> utf-8 (firefox、chrome、opera)
		// 响应的数据编码： utf-8 --> gbk --> iso8859-1 --> gbk (msie、firefox、chrome、opera)
		System.out.println("====getFileStream===");
		InputStream inputStream = ServletActionContext.getServletContext().getResourceAsStream(fileUrl);
		try{
			// 对中文文件名做转码
			String title = new String(downFileName.getBytes("iso8859-1"), "utf-8");
			// 把utf-8的中文转化成gbk (decode转码)
			title = URLDecoder.decode(title, "gbk");
			title = new String(title.getBytes("gbk"), "iso8859-1");
			this.downFileName = title +  "." + FilenameUtils.getExtension(this.fileUrl);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return inputStream;
	}
	/** 获取下载的文件名 */
	public String getDownFileName(){
		return this.downFileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
}
