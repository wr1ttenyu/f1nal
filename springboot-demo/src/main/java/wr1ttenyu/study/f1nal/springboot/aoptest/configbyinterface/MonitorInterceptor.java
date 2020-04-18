package wr1ttenyu.study.f1nal.springboot.aoptest.configbyinterface;

import io.lettuce.core.dynamic.intercept.MethodInterceptor;
import io.lettuce.core.dynamic.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MonitorInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object[] arguments = invocation.getArguments();
        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        long runTime = end - start;
        System.out.println("方法[" + method.getName() + "]执行结束，参数为：" + Arrays.toString(arguments) + "，执行时间为：" + runTime
                + "ms");
        return proceed;
    }
}
