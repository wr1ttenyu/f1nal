package wr1ttenyu.study.f1nal.springboot.aoptest.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {
    // dao 实现
    @Pointcut("execution(* wr1ttenyu.study.f1nal.springboot.aoptest.annotation.dao.*.*(..))")
    public void dataAccessOperation() {}
}
