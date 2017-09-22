package org.fkjava.hrm.action;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.fkjava.hrm.action.base.AbstractAjaxAction;

/**
 * 公用的文件异步上传
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 下午4:54:25
 * @version 1.0
 */
public class UploadAjax extends AbstractAjaxAction {

	private static final long serialVersionUID = -5143017658834904194L;
	/** 定义文件上传的三个属性 */
	private File pic;
	private String picFileName;
	private String picContentType;
	
	@Override
	protected String ajaxTask() throws Exception {
		// 获取项目部署路径
		String realPath = ServletActionContext.getServletContext().getRealPath("/images/notice");
		// 生成新的文件名
		String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(picFileName);
		// 文件拷贝
		FileUtils.copyFile(pic, new File(realPath + File.separator + newFileName));
		System.out.println(newFileName);
		return "/images/notice/" + newFileName;
	}
	
	/** setter and getter method */
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
}
