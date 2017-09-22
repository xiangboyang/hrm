package org.fkjava.hrm.action.base;

import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/**
 * 抽像的基础的Action
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 上午10:53:48
 * @version 1.0
 */
public abstract class AbstractAjaxAction extends AbstractAction {
	
	private static final long serialVersionUID = 753553844533719075L;
	
	@Override
	public String execute() {
		try{
			System.out.println("父类：AbstractAjaxAction");
			String json = this.ajaxTask(); // 调用子类的方法，处理业务
			System.out.println("json: " + json);
			
			System.out.println("压缩之前：" + json.getBytes().length);
			
			/** 为了提高网站响应速度，对响应的内容进行GZIP压缩 */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(json.getBytes("utf-8"));
			gzip.flush();
			gzip.close();
			
			System.out.println("压缩之后：" + bos.toByteArray().length);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			/** 告诉浏览器响应的数据是用gzip压缩的 */
			response.setHeader("Content-Encoding", "GZIP");
			response.setContentType("text/html;charset=UTF-8");
			response.getOutputStream().write(bos.toByteArray());
		}catch(Exception ex){
			log(ex);
		}
		return NONE;
	}
	
	/** 这个抽像方法由所有子类去实现 */
	protected abstract String ajaxTask() throws Exception;
}
