package com.codetend.common.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  //运行时生效
@Target({ElementType.METHOD, ElementType.TYPE}) // 用于描述注解的使用范围
public @interface BaseResponse {
}
