package org.fkjava.hrm.common;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSessionUtils
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午2:55:19
 * @version 1.0
 */
public class SqlSessionUtils {
	/** 定义SqlSessionFactory (它底层封装了数据源) */
	private static SqlSessionFactory sqlSessionFactory;
	/** 定义ThreadLocal来保证SqlSession是线程安全的 */
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
	
	/** 定义静态初始化块 */
	static{
		try{
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/** 获取SqlSession (SqlSession底层封装得是数据连接Connection) */
	public static SqlSession getSession(){
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession == null){
			sqlSession = sqlSessionFactory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	/** 关闭SqlSession */
	public static void close(){
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null){
			sqlSession.close();
		}
		threadLocal.remove();
	}
}
