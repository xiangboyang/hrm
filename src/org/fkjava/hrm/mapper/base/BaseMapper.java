package org.fkjava.hrm.mapper.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据访问基础接口(通用的数据访问)
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-3-2 下午2:58:50
 * @version 1.0
 */
public interface BaseMapper {
	/**
	 * 通用的添加方法
	 * @param entity
	 */
	<T> void save(T entity);
	/**
	 * 通用的修改方法
	 * @param entity
	 */
	<T> void update(T entity);
	/**
	 * 根据主键id获取一个实体
	 * @param id 主键id
	 * @return 实体
	 */
	<T> T get(Serializable id);
	/**
	 * 通用的删除方法
	 * @param id 主键id
	 */
	void delete(Serializable id);
	/**
	 * 通用的批量删除方法
	 * @param id
	 */
	void batchDelete(Serializable[] id);
	/**
	 * 通用的统计查询
	 * @param params 查询参数
	 * @return 总记录条数
	 */
	int count(Map<String, Object> params);
	/**
	 * 通用的分页查询方法
	 * @param params 查询参数
	 * @return
	 */
	<T> List<T> findByPage(Map<String, Object> params);
	/**
	 * 查询所有的记录
	 * @param params 查询参数
	 * @return
	 */
	<T> List<T> find(Map<String, Object> params);
}
