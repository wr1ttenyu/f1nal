package wr1ttenyu.study.f1nal.springboot.aoptest.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {

    // web 层
    @Pointcut("within(wr1ttenyu.study.f1nal.springboot.aoptest.annotation.web..*)")
    public void inWebLayer() {}

    // service 层
    @Pointcut("within(wr1ttenyu.study.f1nal.springboot.aoptest.annotation.service..*)")
    public void inServiceLayer() {}

    // dao 层
    @Pointcut("within(wr1ttenyu.study.f1nal.springboot.aoptest.annotation.dao..*)")
    public void inDataAccessLayer() {}

    // service 实现，注意这里指的是方法实现，其实通常也可以使用 bean(*ServiceImpl)
    @Pointcut("execution(* wr1ttenyu.study.f1nal.springboot.aoptest.annotation.service.*.*(..))")
    public void businessService() {}

    // dao 实现
    @Pointcut("execution(* wr1ttenyu.study.f1nal.springboot.aoptest.annotation.dao.*.*(..))")
    public void dataAccessOperation() {}
}
