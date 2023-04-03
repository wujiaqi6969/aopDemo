package com.wujiaqi.aopdemo.aop;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wujiaqi.aopdemo.convert.ParamConvert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wujiaqi
 */
@Aspect
@Component
public class OperateAspect {
    /**
     * 定义线程池
     */
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
            1,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * 1.定义切入点
     * 2.横切逻辑
     * 3.织入
     */

    @Pointcut("@annotation(OrderOperateLog)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            result = Boolean.FALSE.toString();
            e.printStackTrace();
        }


        Object finalResult = result;
        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature method = (MethodSignature) point.getSignature();
                OrderOperateLog orderOperateLog = method.getMethod().getAnnotation(OrderOperateLog.class);

                Class<? extends ParamConvert> convert = orderOperateLog.convert();
                ParamConvert paramConvert = convert.newInstance();
                OrderRecordDo recordDo = paramConvert.convert(point.getArgs()[0]);


                String desc = orderOperateLog.desc();
                recordDo.setDesc(desc);
                recordDo.setResult(finalResult.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                System.out.println("asyn thread record lgo : " + objectMapper.writeValueAsString(recordDo));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        return result;
    }
}
