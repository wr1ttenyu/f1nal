package wr1ttenyu.study.f1nal.springboot.aoptest.configbyinterface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAopApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-config.xml");
        UserService userService = (UserService)applicationContext.getBean("userServiceImpl");
        userService.createUser("wr1", 154);
        userService.getUser("wr1");
    }
}
