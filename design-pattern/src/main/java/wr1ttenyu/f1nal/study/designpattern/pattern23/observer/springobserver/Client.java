package wr1ttenyu.f1nal.study.designpattern.pattern23.observer.springobserver;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Client.class);
        ((AnnotationConfigApplicationContext) context).addApplicationListener(new MySpringApplicationListener());
        MySpringEventPublisher publisher = context.getBean(MySpringEventPublisher.class);
        publisher.publishEvent(new MySpringEvent("wr1ttenyu event"));
    }

    @Bean
    public MySpringEventPublisher mySpringEventPublisher() {
        return new MySpringEventPublisher();
    }
}
