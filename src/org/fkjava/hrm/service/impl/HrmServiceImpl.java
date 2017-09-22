package org.fkjava.hrm.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.fkjava.hrm.annotation.MapperAuto;
import org.fkjava.hrm.common.WebConstant;
import org.fkjava.hrm.common.io.FileUtil;
import org.fkjava.hrm.common.web.PageModel;
import org.fkjava.hrm.domain.Dept;
import org.fkjava.hrm.domain.Document;
import org.fkjava.hrm.domain.Employee;
import org.fkjava.hrm.domain.Notice;
import org.fkjava.hrm.domain.User;
import org.fkjava.hrm.exception.HrmException;
import org.fkjava.hrm.mapper.DeptMapper;
import org.fkjava.hrm.mapper.DocumentMapper;
import org.fkjava.hrm.mapper.EmployeeMapper;
import org.fkjava.hrm.mapper.JobMapper;
import org.fkjava.hrm.mapper.NoticeMapper;
import org.fkjava.hrm.mapper.UserMapper;
import org.fkjava.hrm.pojo.EmployeeData;
import org.fkjava.hrm.service.HrmService;

import com.opensymphony.xwork2.ActionContext;

/**
 * HrmServiceImpl
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午3:32:44
 * @version 1.0
 */
public class HrmServiceImpl implements HrmService {
	
	/** 注入数据访问层代理对象 */
	@MapperAuto(required=true)
	private DeptMapper deptMapper;
	@MapperAuto(required=true)
	private JobMapper jobMapper;
	@MapperAuto(required=true)
	private UserMapper userMapper;
	@MapperAuto(required=true)
	private DocumentMapper documentMapper;
	@MapperAuto(required=true)
	private NoticeMapper noticeMapper;
	@MapperAuto(required=true)
	private EmployeeMapper employeeMapper;

		
	/**
	 * 用户登录
	 * @param userId 用户名
	 * @param password 密码
	 * @param code 验证码
	 * @return JSON格式字符串
	 */
	public String login(String userId, String password, String code){
		try{
			// 定义JSONObject
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tip", "验证码不正确！");
			jsonObject.put("status", "1");
			// 判断验证码
			String oldCode = (String)ActionContext.getContext().getSession().get(WebConstant.VERIFY_CODE);
			if (true || oldCode.equalsIgnoreCase(code)){
				// 验证码正确,根据用户名与密码查询用户
				User user = userMapper.findUserByUserIdAndPassword(userId, password);
				if (user != null){
					// 登录成功
					ActionContext.getContext().getSession().put(WebConstant.SESSION_USER, user);
					jsonObject.put("tip", "登录成功！");
					jsonObject.put("status", "0");
				}else{
					jsonObject.put("tip", "用户名或者密码不正确！");
					jsonObject.put("status", "2");
				}
			}
			return jsonObject.toString();
		}catch(Exception ex){
			throw new HrmException("用户登录时出现异常！", ex);
		}
	}
	
	/** ###########################TODO 公告管理业务处理   ########################### */
	/**
	 * 分页查询公告
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	public List<Notice> getNoticeByPage(Notice notice, PageModel pageModel){
		try{
			// 定义集合封装查询参数
			Map<String, Object> params = new HashMap<>();
			params.put("notice", notice);
			// 先统计查询
			int recordCount = noticeMapper.count(params);
			System.out.println("===" + recordCount);
			if (recordCount == 0){
				return null;
			}
			
			// 设置总记录条数
			pageModel.setRecordCount(recordCount); // limit ?,?
			params.put("pageModel", pageModel);
			// 分页查询
			return noticeMapper.findByPage(params);
			
		}catch(Exception ex){
			throw new HrmException("分页查询公告时出现异常！", ex);
		}
	}
	/**
	 * 添加公告
	 * @param notice
	 */
	public void saveNotice(Notice notice){
		try{
			notice.setCreateDate(new Date());
			notice.setUser(WebConstant.getSessionUser());
			noticeMapper.save(notice);
		}catch(Exception ex){
			throw new HrmException("添加公告时出现异常！", ex);
		}
	}
	/**
	 * 获取公告
	 * @param id
	 * @return
	 */
	public Notice getNotice(int id){
		try{
			return noticeMapper.get(id);
		}catch(Exception ex){
			throw new HrmException("获取公告时出现异常！", ex);
		}
	}
	/**
	 * 修改公告
	 * @param notice
	 */
	public void updateNotice(Notice notice){
		try{
			noticeMapper.update(notice);
		}catch(Exception ex){
			throw new HrmException("修改公告时出现异常！", ex);
		}
	}
	/**
	 * 删除公告
	 * @param ids
	 */
	public void deleteNotice(String[] ids){
		try{
			noticeMapper.batchDelete(ids);
		}catch(Exception ex){
			throw new HrmException("删除公告时出现异常！", ex);
		}
	}
	
	
	
	/** ###########################TODO 部门管理业务处理   ########################### */
	/**
	 * 分页查询部门
	 * @param pageModel
	 * @return
	 */
	public List<Dept> getDeptByPage(PageModel pageModel){
		try{
			// 统计查询
			int recordCount = deptMapper.count(null);
			if (recordCount == 0){
				return null;
			}
			
			// 分页查询
			pageModel.setRecordCount(recordCount);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pageModel", pageModel);
			return deptMapper.findByPage(params);
			
		}catch(Exception ex){
			throw new HrmException("分页查询部门时出现异常！", ex);
		}
	}
	/**
	 * 根据主键id获取部门
	 * @param id
	 * @return
	 */
	public Dept getDept(int id){
		try{
			return deptMapper.get(id);
		}catch(Exception ex){
			throw new HrmException("根据主键id获取部门时出现异常！", ex);
		}
	}
	/**
	 * 修改部门
	 * @param dept
	 */
	public void updateDept(Dept dept){
		try{
			deptMapper.update(dept);
		}catch(Exception ex){
			throw new HrmException("修改部门时出现异常！", ex);
		}
	}
	/**
	 * 添加部门
	 * @param dept
	 */
	public void addDept(Dept dept){
		try{
			deptMapper.save(dept);
		}catch(Exception ex){
			throw new HrmException("添加部门时出现异常！", ex);
		}
	}
	/**
	 * 删除部门
	 * @param id
	 */
	public void deleteDept(int id){
		try{
			deptMapper.delete(id);
		}catch(Exception ex){
			throw new HrmException("删除部门时出现异常！", ex);
		}
	}
	
	
	
	/** ###########################TODO 文档管理业务处理   ########################### */
	/**
	 * 分页查询文档
	 * @param document
	 * @param pageModel
	 * @return
	 */
	public List<Document> getDocumentByPage(Document document, PageModel pageModel){
		try{
			// 定义集合封装查询参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("document", document);
			// 统计查询
			int recordCount = documentMapper.count(params);
			
			if (recordCount == 0){
				return null;
			}
			// 分页查询
			pageModel.setRecordCount(recordCount);
			params.put("pageModel", pageModel);
			return documentMapper.findByPage(params);
			
		}catch(Exception ex){
			throw new HrmException("分页查询文档时出现异常！", ex);
		}
	}
	/**
	 * 添加文档
	 * @param document
	 */
	public void saveDocument(Document document){
		try{
			document.setUser(WebConstant.getSessionUser());
			documentMapper.save(document);
		}catch(Exception ex){
			throw new HrmException("添加文档时出现异常！", ex);
		}
	}
	/**
	 * 获取文档
	 * @param id
	 * @return
	 */
	public Document getDocument(int id){
		try{
			return documentMapper.get(id);
		}catch(Exception ex){
			throw new HrmException("获取文档时出现异常！", ex);
		}
	}
	/**
	 * 修改文档
	 * @param oldUrl
	 * @param document
	 */
	public void updateDocument(String oldUrl, Document document){
		try{
			// 先修改数据
			documentMapper.update(document);
			// 删除文件 (判断是不是上传了新的文件)
			if (!oldUrl.equals(document.getUrl())){
				FileUtil.deleteFile(oldUrl);
			}
		}catch(Exception ex){
			throw new HrmException("修改文档时出现异常！", ex);
		}
	}
	/**
	 * 删除文档
	 * @param id
	 */
	public void deleteDocument(int id){
		try{
			// 获取文档url
			String oldUrl = this.getDocument(id).getUrl();
			// 先删除数据
			documentMapper.delete(id);
			// 删除文件 
			if (oldUrl != null && !"".equals(oldUrl)){
				FileUtil.deleteFile(oldUrl);
			}
		}catch(Exception ex){
			throw new HrmException("删除文档时出现异常！", ex);
		}
	}
	
	
	
	/** ###########################TODO 员工管理业务处理   ########################### */
	/**
	 * 多条件分页查询员工
	 * @param employee
	 * @param pageModel
	 * @return
	 */
	public List<Employee> getEmployeeByPage(Employee employee, PageModel pageModel){
		try{
			/** 定义集合封装查询参数 */
			Map<String, Object> params = new HashMap<>();
			params.put("employee", employee);
			
			/** 统计查询 */
			int recordCount = employeeMapper.count(params);
			if (recordCount == 0){
				return null;
			}
			
			/** 设置总记录条数 */
			pageModel.setRecordCount(recordCount);
			params.put("pageModel", pageModel);
			/** 分页查询 */
			return employeeMapper.findByPage(params);
		}catch(Exception ex){
			throw new HrmException("多条件分页查询员工时出现异常！", ex);
		}
	}
	
	/**
	 * 查询所有的职位
	 * @return
	 */
	public List<Map<String, String>> getJobs(){
		try{
			return jobMapper.find(null);
		}catch(Exception ex){
			throw new HrmException("查询所有的职位时出现异常！", ex);
		}
	}
	/**
	 * 异步加载部门 
	 * @return
	 */
	public String loadDept(){
		try{
			List<Map<String, String>> lists = deptMapper.find(null);
			// data数据格式：[{id : 1, name : '技术部'}, {id : 1, name : '技术部'}]
			return JSONArray.fromObject(lists).toString();
		}catch(Exception ex){
			throw new HrmException("异步加载部门时出现异常！", ex);
		}
	}
	/**
	 * 查询要导出Excel的数据 
	 * @param employee
	 * @return
	 */
	public List<EmployeeData> getEmployees(Employee employee){
		try{
			/** 定义集合封装查询参数 */
			Map<String, Object> params = new HashMap<>();
			params.put("employee", employee);
			
			return employeeMapper.find(params);
		}catch(Exception ex){
			throw new HrmException("查询要导出Excel的数据时出现异常！", ex);
		}
	}
}