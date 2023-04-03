package com.wujiaqi.aopdemo.aop.timeout;

import com.wujiaqi.aopdemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wujiaqi
 */
@Component
@Aspect
@Slf4j
public class TimeoutAspect {


    @Autowired
    private Executor threadPool;

    @Pointcut("@annotation(EnableTimeout)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        AtomicReference<Object> result = new AtomicReference<>();


        //方法的签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();


        EnableTimeout enableTimeout = methodSignature.getMethod().getAnnotation(EnableTimeout.class);
        String apiName = enableTimeout.apiName();

        try {
            CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
                try {
                    result.set(pjp.proceed());
                } catch (Throwable e) {
                    result.set("系统内部错误~");
                    log.error("超时aop,异步任务报错:apiName={}报错!", apiName, e);
                }
                return result.get();
            }, threadPool);

            future.get(enableTimeout.value(), enableTimeout.timeUnit());
        } catch (TimeoutException e) {
            //目标参数
            Object[] args = pjp.getArgs();

            //目标类
            Object target = pjp.getTarget();

            //处理特殊方法
            try {
                Method fallbackMethod = target.getClass().getMethod(enableTimeout.fallbackMethodName(), methodSignature.getParameterTypes());
                result.set(fallbackMethod.invoke(target, args));
            } catch (Exception ex) {
                result.set("系统内部错误~");
                log.error("处理特殊方法,处理特殊方法报错!apiName={}", apiName, ex);
            }

        } catch (Exception e) {
            result.set("系统内部错误~");
            log.error("超时aop,等待异步任务反馈报错:apiName={}报错!", apiName, e);
        }


        return result.get();
    }


    public static void main(String[] args) {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        try {
            runAsync.get(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException is running");
        }

        System.out.println("main is over!");

    }

}


