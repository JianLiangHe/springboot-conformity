package edu.conformity.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceLog {

	/**
	 * 描述业务操作， 例如：xxx管理-执行xxx操作
	 * @return
	 */
	String description() default "";
	
}
