package org.fkjava.hrm.action.employee;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.fkjava.hrm.action.base.AbstractAction;
import org.fkjava.hrm.pojo.EmployeeData;
import org.fkjava.hrm.common.WebConstant;
import org.fkjava.hrm.common.io.ExcelUtils;
import org.fkjava.hrm.domain.Employee;

/**
 * 员工管理
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-6 下午2:45:03
 * @version 1.0
 */
public class EmployeeAction extends AbstractAction {
	
	private static final long serialVersionUID = -1758332888939165129L;
	private Employee employee;
	private List<Employee> employees;
	private List<Map<String, String>> jobs;
 	
	/** 多条件分页查询员工 **/
	public String selectEmployee(){
		try{
			// GET请求中文乱码
			if (employee != null){
				employee.setName(WebConstant.isoToUtf8(employee.getName()));
			}
			employees = hrmService.getEmployeeByPage(employee, pageModel);
			/** 查询职位 */
			jobs = hrmService.getJobs();
		}catch(Exception ex){
			log(ex);
		}
		return SUCCESS;
	}
	
	/** 员工资料导出Excel */
	public String downExcel(){
		try{
			/** 查询要导出Excel的数据 */
			List<EmployeeData> lists = hrmService.getEmployees(employee);
			String[] titles = {"编号","姓名","性别","手机号码","邮箱","职位","学历","身份证号码","部门","联系地址","创建日期"};
			ExcelUtils.exportExcel("员工信息表", "员工信息", titles, lists, ServletActionContext.getResponse());
		}catch(Exception ex){
			log(ex);
		}
		return NONE;
	}

	
	/** setter and getter method */
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Map<String, String>> getJobs() {
		return jobs;
	}
	public void setJobs(List<Map<String, String>> jobs) {
		this.jobs = jobs;
	}
}
