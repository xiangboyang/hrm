package org.fkjava.hrm.action.notice;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.fkjava.hrm.action.base.AbstractAction;
import org.fkjava.hrm.common.WebConstant;
import org.fkjava.hrm.domain.Notice;

/**
 * 公告管理控制器
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 下午2:41:06
 * @version 1.0
 */
public class NoticeAction extends AbstractAction {
	
	private static final long serialVersionUID = -4047001258498002589L;
	/** 定义查询条件 */
	private Notice notice;
	/** 公告集合 */
	private List<Notice> notices;
	/** 公告ids */
	private String ids;
	
	/** 分页查询公告 */
	@SkipValidation // 跳出后台校验
	public String selectNotice(){
		try{
			// GET请求中文转码
			if (notice != null){
				notice.setTitle(WebConstant.isoToUtf8(notice.getTitle()));
			}
			
			notices = hrmService.getNoticeByPage(notice, pageModel);
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 添加公告 */
	public String addNotice(){
		try{
			hrmService.saveNotice(notice);
			setTip("添加成功！");
		}catch(Exception ex){
			setTip("添加失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 跳转到修改公告的页面 */
	@SkipValidation // 跳出后台校验
	public String showUpdateNotice(){
		try{
			notice = hrmService.getNotice(notice.getId());
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 修改公告 */
	public String updateNotice(){
		try{
			hrmService.updateNotice(notice);
			setTip("修改成功！");
		}catch(Exception ex){
			setTip("修改失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 删除公告 */
	@SkipValidation // 跳出后台校验
	public String deleteNotice(){
		try{
			hrmService.deleteNotice(ids.split(","));
			setTip("删除成功！");
		}catch(Exception ex){
			setTip("删除失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** setter and getter method */
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public List<Notice> getNotices() {
		return notices;
	}
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}