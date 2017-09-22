package org.fkjava.hrm.action.interceptor;

import org.fkjava.hrm.common.WebConstant;
import org.fkjava.hrm.domain.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 上午11:24:18
 * @version 1.0
 */
public class LoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 4331682845835374942L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 从Session中获取用户
		User user = (User)invocation.getInvocationContext().getSession().get(WebConstant.SESSION_USER);
		
		return user == null ? Action.LOGIN : invocation.invoke();
	}

}
