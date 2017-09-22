package org.fkjava.hrm.service;

import java.util.List;
import java.util.Map;

import org.fkjava.hrm.common.web.PageModel;
import org.fkjava.hrm.domain.Dept;
import org.fkjava.hrm.domain.Document;
import org.fkjava.hrm.domain.Employee;
import org.fkjava.hrm.domain.Notice;
import org.fkjava.hrm.pojo.EmployeeData;


/**
 * HrmService
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午3:31:55
 * @version 1.0
 */
public interface HrmService {
	
	/**
	 * 用户登录
	 * @param userId 用户名
	 * @param password 密码
	 * @param code 验证码
	 * @return JSON格式字符串
	 */
	String login(String userId, String password, String code);
	/**
	 * 分页查询公告
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	List<Notice> getNoticeByPage(Notice notice, PageModel pageModel);
	/**
	 * 添加公告
	 * @param notice
	 */
	void saveNotice(Notice notice);
	/**
	 * 获取公告
	 * @param id
	 * @return
	 */
	Notice getNotice(int id);
	/**
	 * 修改公告
	 * @param notice
	 */
	void updateNotice(Notice notice);
	/**
	 * 删除公告
	 * @param ids
	 */
	void deleteNotice(String[] ids);
	
	
	
	/**
	 * 分页查询部门
	 * @param pageModel
	 * @return
	 */
	List<Dept> getDeptByPage(PageModel pageModel);
	/**
	 * 根据主键id获取部门
	 * @param id
	 * @return
	 */
	Dept getDept(int id);
	/**
	 * 修改部门
	 * @param dept
	 */
	void updateDept(Dept dept);
	/**
	 * 添加部门
	 * @param dept
	 */
	void addDept(Dept dept);
	/**
	 * 删除部门
	 * @param id
	 */
	void deleteDept(int id);
	
	
	/**
	 * 分页查询文档
	 * @param document
	 * @param pageModel
	 * @return
	 */
	List<Document> getDocumentByPage(Document document, PageModel pageModel);
	/**
	 * 添加文档
	 * @param document
	 */
	void saveDocument(Document document);
	/**
	 * 获取文档
	 * @param id
	 * @return
	 */
	Document getDocument(int id);
	/**
	 * 修改文档
	 * @param oldUrl
	 * @param document
	 */
	void updateDocument(String oldUrl, Document document);
	/**
	 * 删除文档
	 * @param id
	 */
	void deleteDocument(int id);
	/**
	 * 多条件分页查询员工
	 * @param employee
	 * @param pageModel
	 * @return
	 */
	List<Employee> getEmployeeByPage(Employee employee, PageModel pageModel);
	/**
	 * 查询所有的职位
	 * @return
	 */
	List<Map<String, String>> getJobs();
	/**
	 * 异步加载部门 
	 * @return
	 */
	String loadDept();
	/**
	 * 查询要导出Excel的数据 
	 * @param employee
	 * @return
	 */
	List<EmployeeData> getEmployees(Employee employee);
	

}
