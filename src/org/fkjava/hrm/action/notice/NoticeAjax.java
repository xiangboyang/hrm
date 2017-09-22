package org.fkjava.hrm.action.notice;

import org.fkjava.hrm.action.base.AbstractAjaxAction;

/**
 * 异步加载公告内容
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-5 下午2:41:47
 * @version 1.0
 */
public class NoticeAjax extends AbstractAjaxAction {

	private static final long serialVersionUID = -6224689187929277945L;
	/** 公告id */
	private int id;

	@Override
	protected String ajaxTask() throws Exception {
		return hrmService.getNotice(id).getContent();
	}

	/** setter and getter method */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
