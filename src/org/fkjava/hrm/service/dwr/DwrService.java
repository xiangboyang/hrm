package org.fkjava.hrm.service.dwr;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;
import org.fkjava.hrm.service.HrmService;
import org.fkjava.hrm.service.impl.HrmServiceImpl;
import org.fkjava.hrm.service.support.ServiceProxyFactory;

/**
 * DWR业务服务层
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-8 上午11:23:42
 * @version 1.0
 */
public class DwrService {
	
	/** 定义业务层代理对象 */
	private HrmService hrmService = ServiceProxyFactory.getProxyInstance(new HrmServiceImpl());
	/**
	 * 文件异步上传的方法
	 * @param fileTransfer 文件转换对象
	 * @return 路径 + 文件名
	 * @throws IOException 
	 */
	public String uploadPic(FileTransfer fileTransfer) throws IOException{
		// 获取项目部署路径
		String realPath = WebContextFactory.get().getServletContext().getRealPath("/images/emp");
		// 生成新的文件件
		String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(fileTransfer.getFilename());
		// 文件拷贝
		FileUtils.copyInputStreamToFile(fileTransfer.getInputStream(), new File(realPath + File.separator + newFileName));
		
		return "/images/emp/" + newFileName;
	}
	/**
	 * 获取部门与职位
	 * @return
	 */
	public String loadDeptJobs(){
		//{"depts" : [{},{}], "jobs" :  [{},{}]}
		JSONObject json = new JSONObject();
		json.put("depts", hrmService.loadDept());
		json.put("jobs", hrmService.getJobs());
		return json.toString();
	}
}
