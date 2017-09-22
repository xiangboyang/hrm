package org.fkjava.hrm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自动注入Mapper接口代理对象
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午3:54:42
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 保留到运行时，就可以用反映来读取
public @interface MapperAuto {
	boolean required() default true;
}
