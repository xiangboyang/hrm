package org.fkjava.hrm.service.support;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.fkjava.hrm.annotation.MapperAuto;
import org.fkjava.hrm.common.SqlSessionUtils;

/**
 * 业务层代理工厂
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午3:57:28
 * @version 1.0
 */
public final class ServiceProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getProxyInstance(final Object target) {
		
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), new InvocationHandler() {
					
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				// 获取SqlSession
				SqlSession session = SqlSessionUtils.getSession();
				try{
					// 获取目标对象中所有的属性
					Field[] fields = target.getClass().getDeclaredFields();
					// 迭代Field
					for (Field field : fields){
						// 判断field上面是否加了@MapperAuto这个注解
						if (field.isAnnotationPresent(MapperAuto.class)){
							// 判断它的required属性值是不是true
							if (field.getAnnotation(MapperAuto.class).required()){
								// 设置该属性可访问
								field.setAccessible(true);
								// 注入该属性值
								field.set(target, session.getMapper(field.getType()));
							}
						}
					}
					System.out.println("调用业务层方法之前--------1----------");
					
					/** 执行目标方法 */
					Object result = method.invoke(target, args);
					
					System.out.println("调用业务层方法之后--------2----------");
					session.commit(); // 提交事务
					return result;
				}catch (Throwable ex) {
					session.rollback(); // 回滚事务
					throw new Throwable(ex);
				}finally{
					SqlSessionUtils.close(); // 关闭SqlSession
				}
			}
		});
	}

}
