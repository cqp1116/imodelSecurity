package com.imodel.User.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    //@Before()切片拦截前调用
    //@After()切片拦截后调用
    @Around("execution(* com.imodel.User.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("切片开始执行 start");

        //通过切片获取请求得内容信息
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start = new Date().getTime();
        Object obj = pjp.proceed();//继续执行方法
        System.out.println("切片执行完成耗时:"+ (new Date().getTime() - start));
        return obj;
    }
}
