package org.fkjava.hrm.action.base;

import org.apache.log4j.Logger;
import org.fkjava.hrm.common.web.PageModel;
import org.fkjava.hrm.service.HrmService;
import org.fkjava.hrm.service.impl.HrmServiceImpl;
import org.fkjava.hrm.service.support.ServiceProxyFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 最基础的Action
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 下午2:45:51
 * @version 1.0
 */
public class AbstractAction extends ActionSupport {

	private static final long serialVersionUID = -9011429005304423978L;
	/** 创建业务层代理对象 */
	protected HrmService hrmService = ServiceProxyFactory.getProxyInstance(new HrmServiceImpl());
	/** 分页实体 */
	protected PageModel pageModel = new PageModel();
	/** 定义提示信息 */
	private String tip;
	/** 定义日志对象 */
	private Logger logger = Logger.getLogger(AbstractAction.class);
	
	/** 记录异常日志信息 */
	protected void log(Throwable ex){
		logger.error("人事管理系统", ex);
	}
	
	/** setter and getter method */
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
