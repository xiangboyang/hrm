package org.fkjava.hrm.action.dept;

import java.util.List;

import org.fkjava.hrm.action.base.AbstractAction;
import org.fkjava.hrm.domain.Dept;

/**
 * DeptAction
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-5 上午9:09:02
 * @version 1.0
 */
public class DeptAction extends AbstractAction {
	
	private static final long serialVersionUID = -2659399492233040138L;
	private List<Dept> depts;
	private Dept dept;
	
	/** 分页查询部门 */
	public String selectDept(){
		try{
			depts = hrmService.getDeptByPage(pageModel);
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 跳转到修改部门页面 */
	public String showUpdateDept(){
		try{
			dept = hrmService.getDept(dept.getId());
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 修改部门 */
	public String updateDept(){
		try{
			hrmService.updateDept(dept);
			setTip("修改成功！");
		}catch(Exception ex){
			setTip("修改失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 添加部门 */
	public String addDept(){
		try{
			hrmService.addDept(dept);
			setTip("添加成功！");
		}catch(Exception ex){
			setTip("添加失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 删除部门 */
	public String deleteDept(){
		try{
			hrmService.deleteDept(dept.getId());
			setTip("删除成功！");
		}catch(Exception ex){
			setTip("删除失败！");
			log(ex);
		}
		return SUCCESS;
	}
	
	/** setter and getter method */
	public List<Dept> getDepts() {
		return depts;
	}
	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
