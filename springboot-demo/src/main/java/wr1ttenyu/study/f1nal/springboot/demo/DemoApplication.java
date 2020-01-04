package wr1ttenyu.study.f1nal.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        Sheep sheep1 = (Sheep)applicationContext.getBean("sheep");
        Sheep sheep2 = (Sheep)applicationContext.getBean("sheep");
        System.out.println(sheep1);
        System.out.println(sheep2);
        System.out.println(sheep1 == sheep2);
    }

    @Bean(name = "sheep")
    @Scope("prototype")
    public Sheep getSheep() {
        return new Sheep();
    }
}
