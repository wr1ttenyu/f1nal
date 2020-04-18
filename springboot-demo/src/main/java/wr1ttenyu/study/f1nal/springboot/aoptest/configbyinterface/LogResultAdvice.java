package wr1ttenyu.study.f1nal.springboot.aoptest.configbyinterface;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class LogResultAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnVal, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("方法：" + method.getName() + "执行完毕，返回结果：" + returnVal);
    }
}
