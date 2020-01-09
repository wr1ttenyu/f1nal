package wr1ttenyu.study.f1nal.springboot.demo.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

/*@Component*/
public class RedisConfig implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof LettuceConnectionFactory)
            ((LettuceConnectionFactory)bean).setShareNativeConnection(false);
        return bean;
    }
}
