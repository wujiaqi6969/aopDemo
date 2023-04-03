package com.wujiaqi.aopdemo.aop.timeout;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wujiaqi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableTimeout {

    /**
     * api名称
     *
     * @return
     */
    String apiName();

    /**
     * 超时时间
     *
     * @return
     */
    int value();

    /**
     * 超时时间的单位
     *
     * @return
     */
    TimeUnit timeUnit();

    /**
     * 处理降级方法
     * 必须是这个注解的当前类中的方法
     *
     * @return
     */
    String fallbackMethodName();

}
