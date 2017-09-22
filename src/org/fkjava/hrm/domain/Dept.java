package org.fkjava.hrm.domain;

import org.apache.ibatis.type.Alias;

/**
 * Dept 数据传输类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-02-27 15:18:21
 * @version 1.0
 */
@Alias("Dept") // 更改类型别名
public class Dept implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String remark;

	/** setter and getter method */
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}

}