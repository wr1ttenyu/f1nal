package wr1ttenyu.study.f1nal.springboot.aoptest.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// 一旦开启了下面的配置，那么所有使用 @Aspect 注解的 bean
// 都会被 Spring 当做用来实现 AOP 的配置类，我们称之为一个 Aspect。
@EnableAspectJAutoProxy
public class AopConfig {
}
