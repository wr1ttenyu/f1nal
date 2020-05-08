package wr1ttenyu.study.f1nal.springboot.springstudy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringConfig implements BeanFactoryAware, DisposableBean, InitializingBean {

    @Value("${person.nickName}")
    private String name;

    private Integer age;

    @Value("123")
    private String fieldStr;

    @Autowired
    private HelloService helloService;

    /**
     * 如果这里去定义TestBean，因为TestBean是一个BeanPostProcessor，所以导致TestBean被提前加载，
     * 而getTestBean依赖SpringConfig，又导致了SpringConfig被提前加载，最终结果就是SpringConfig在TestBean之前被加载
     * 所以SpringConfig不会被TestBean的BeanPostProcessor相关方法拦截，helloService亦是如此
     * @return
     */
    /*@Bean(initMethod = "initMethod")
    public TestBean getTestBean() {
        return new TestBean();
    }*/

    @PostConstruct
    public void postConstruct() {
        System.out.println("SpringConfig postConstruct --> helloService 有没有被注入呢：" + helloService);
        System.out.println("SpringConfig postConstruct --> fieldStr 有没有被注入呢：" + fieldStr);
    }


    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory);
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
