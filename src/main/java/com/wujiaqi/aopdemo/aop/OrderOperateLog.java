package com.wujiaqi.aopdemo.aop;

import com.wujiaqi.aopdemo.convert.ParamConvert;

import java.lang.annotation.*;

/**
 * @author wujiaqi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OrderOperateLog {

    String desc() default "";


    Class<? extends ParamConvert> convert();
}
