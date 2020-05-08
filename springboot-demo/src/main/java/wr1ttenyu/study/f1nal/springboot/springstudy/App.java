package wr1ttenyu.study.f1nal.springboot.springstudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext("wr1ttenyu.study.f1nal.springboot.springstudy");

        System.out.println("context 启动成功");
        // 从 context 中取出我们的 Bean，而不是用 new HelloServiceImpl() 这种方式
        HelloService messageService = context.getBean(HelloService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());

       /* TestBean testBean = (TestBean)context.getBean("getTestBean");
        System.out.println(testBean.getBeanName());*/

        TestBean testBean2 = (TestBean)context.getBean("testBean");
        System.out.println(testBean2.getFieldStr());

        SpringConfig bean = context.getBean(SpringConfig.class);
        System.out.println(bean.getHelloService());

        /*System.out.println(testBean == testBean2);*/
    }
}
