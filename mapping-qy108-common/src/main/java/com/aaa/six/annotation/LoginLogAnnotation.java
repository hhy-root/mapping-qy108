package com.aaa.six.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: mapping-qy108
 * @author: lwq
 * @create: 2020-05-27 19:22
 * @description:
 *      自定义注解
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnotation {

    /**
     * 需要执行的操作类型
     * @return
     */
    String operationType() default "";

    /**
     * 要执行的具体操作内容
     * @return
     */
    String operationName() default "";
}
