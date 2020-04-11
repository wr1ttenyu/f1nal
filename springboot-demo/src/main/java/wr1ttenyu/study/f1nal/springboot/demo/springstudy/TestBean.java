package wr1ttenyu.study.f1nal.springboot.demo.springstudy;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestBean implements /*BeanFactoryPostProcessor,*//*bean定义获取完毕，可以修改bean定义*/
        /*BeanDefinitionRegistryPostProcessor,*//*注册新的bean定义，如dubbo那种*//*,*/
        InstantiationAwareBeanPostProcessor/*在bean实例化的前后进行切入，这个过程中bean的属性还没有被注入*//*,*/,
        BeanNameAware/**/,
        BeanFactoryAware/**/,
        ApplicationContextAware/**/,
        BeanPostProcessor/**/ {

    @Value("123")
    private String fieldStr;

    private int fieldInt;

    private String beanName;

    private ApplicationContext applicationContext;

    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private HelloService helloService;

    public void initMethod() {
        System.out.println("initMethod --> 如果fieldInt有值了，说明bean已经被创建了，fieldInt：" + fieldInt);
        System.out.println("initMethod --> helloService 有没有被注入呢：" + helloService);
        System.out.println("postConstruct --> fieldStr 有没有被注入呢：" + fieldStr);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct --> 如果fieldInt有值了，说明bean已经被创建了，fieldInt：" + fieldInt);
        System.out.println("postConstruct --> helloService 有没有被注入呢：" + helloService);
        System.out.println("postConstruct --> fieldStr 有没有被注入呢：" + fieldStr);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(fieldStr);
        System.out.println("BeanNameAware回调");
        beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getBeanName() {
        return beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws
            BeansException {
        return pvs;
    }

    public String getFieldStr() {
        return fieldStr;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization --> bean: " + bean + " beanName: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization --> bean: " + bean + " beanName: " + beanName);
        return bean;
    }
}
