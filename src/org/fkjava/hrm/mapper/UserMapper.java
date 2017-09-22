package org.fkjava.hrm.mapper;

import org.apache.ibatis.annotations.Param;
import org.fkjava.hrm.domain.User;
import org.fkjava.hrm.mapper.base.BaseMapper;

/**
 * UserMapper 数据访问类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-02-27 15:18:21
 * @version 1.0
 */
public interface UserMapper  extends BaseMapper{
	/**
	 * 根据用户名与密码查询用户
	 * @param userId
	 * @param password
	 * @return
	 */
	User findUserByUserIdAndPassword(@Param("userId")String userId, @Param("pwd")String password);



}