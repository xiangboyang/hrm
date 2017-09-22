package org.fkjava.hrm.common;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.fkjava.hrm.domain.User;

/**
 * 常量类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 上午9:34:23
 * @version 1.0
 */
public final class WebConstant {

	/** 定义存放在Session中的验证码 */
	public static final String VERIFY_CODE = "verify_code";
	/** 定义存放在Session中的用户 */
	public static final String SESSION_USER = "session_user";
	
	/** 获取当前用户 */
	public static User getSessionUser() {
		return (User)ServletActionContext.getRequest().getSession().getAttribute(SESSION_USER);
	}
	
	/**
	 * GET请求中文转码
	 * @param str
	 * @return
	 */
	public static String isoToUtf8(String str){
		// 获取请求的方式
		String requestMethod = ServletActionContext.getRequest().getMethod();
		try{
			// GET请求中文转码
			if (requestMethod.equalsIgnoreCase("get") && !StringUtils.isEmpty(str)){
				str = new String(str.getBytes("iso8859-1"), "utf-8");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return str;
	}

}
