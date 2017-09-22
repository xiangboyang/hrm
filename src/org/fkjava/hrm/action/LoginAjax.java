package org.fkjava.hrm.action;

import org.fkjava.hrm.action.base.AbstractAjaxAction;

/**
 * 异步请求登录
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 上午10:07:10
 * @version 1.0
 */
public class LoginAjax extends AbstractAjaxAction {
	
	private static final long serialVersionUID = 2081040657628743187L;
	/** 定义属性接收页面的请求参数 */
	private String userId;
	private String password;
	private String code;

	@Override
	protected String ajaxTask() throws Exception {
		System.out.println("子类：LoginAjax");
		return hrmService.login(userId, password, code);
	}

	
	/** setter and getter method */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
