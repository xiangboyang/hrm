package org.fkjava.hrm.action.dept;

import org.fkjava.hrm.action.base.AbstractAjaxAction;

/**
 * 异步加载部门
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-6 下午4:07:05
 * @version 1.0
 */
public class DeptAjax extends AbstractAjaxAction {

	private static final long serialVersionUID = -7903052428681665866L;

	@Override
	protected String ajaxTask() throws Exception {
		return hrmService.loadDept();
	}

}
