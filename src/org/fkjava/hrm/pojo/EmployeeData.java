package org.fkjava.hrm.pojo;

import java.text.SimpleDateFormat;

/**
 * EmployeeData
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-8 上午9:27:57
 * @version 1.0
 */
public class EmployeeData {
	// "编号","姓名","性别","手机号码","邮箱","职位","学历","身份证号码","部门","联系地址","创建日期"
	private int id;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String jobName;
	private String education;
	private String cardId;
	private String deptName;
	private String address;
	private String createDate;
	
	/** setter and getter method */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex == 1 ? "男" : "女";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createDate = sdf.format(createDate);
	}
}