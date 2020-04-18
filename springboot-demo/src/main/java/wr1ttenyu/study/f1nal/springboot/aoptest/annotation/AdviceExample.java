package wr1ttenyu.study.f1nal.springboot.aoptest.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AdviceExample {

    // 这里会用到我们前面说的 SystemArchitecture
    // 下面方法就是写拦截 "dao层实现"
    @Before("wr1ttenyu.study.f1nal.springboot.aoptest.annotation.SystemArchitecture.dataAccessOperation()")
    public void doAccessCheck(JoinPoint joinPoint) {
        // ... 实现代码
    }

  /*  // 当然，我们也可以直接"内联"Pointcut，直接在这里定义 Pointcut
    // 把 Advice 和 Pointcut 合在一起了，但是这两个概念我们还是要区分清楚的
    @Before("execution(* wr1ttenyu.study.f1nal.springboot.demo.aoptest.annotation.dao.*.*(..))")
    public void doAccessCheck(JoinPoint joinPoint) {
        // ... 实现代码
    }

    @AfterReturning("com.javadoop.aop.SystemArchitecture.dataAccessOperation()")
    public void doAccessCheck() {
        // ...
    }

    @AfterReturning(
            pointcut="com.javadoop.aop.SystemArchitecture.dataAccessOperation()",
            returning="retVal")
    public void doAccessCheck(Object retVal) {
        // 这样，进来这个方法的处理时候，retVal 就是相应方法的返回值，是不是非常方便
        //  ... 实现代码
    }

    // 异常返回
    @AfterThrowing("com.javadoop.aop.SystemArchitecture.dataAccessOperation()")
    public void doRecoveryActions() {
        // ... 实现代码
    }

    @AfterThrowing(
            pointcut="com.javadoop.aop.SystemArchitecture.dataAccessOperation()",
            throwing="ex")
    public void doRecoveryActions(DataAccessException ex) {
        // ... 实现代码
    }

    // 注意理解它和 @AfterReturning 之间的区别，这里会拦截正常返回和异常的情况
    @After("com.javadoop.aop.SystemArchitecture.dataAccessOperation()")
    public void doReleaseLock() {
        // 通常就像 finally 块一样使用，用来释放资源。
        // 无论正常返回还是异常退出，都会被拦截到
    }

    // 感觉这个很有用吧，既能做 @Before 的事情，也可以做 @AfterReturning 的事情
    @Around("com.javadoop.aop.SystemArchitecture.businessService()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }*/

}
