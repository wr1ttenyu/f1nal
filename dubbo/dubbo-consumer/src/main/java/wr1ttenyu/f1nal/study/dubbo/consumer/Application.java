package wr1ttenyu.f1nal.study.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import wr1ttenyu.f1nal.study.dubbo.service.facade.IHelloDubboService;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/dubbo-consumer.xml"});
        context.start();

        IHelloDubboService helloDubboService = context.getBean(IHelloDubboService.class);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.next();
            String hello = helloDubboService.hello(input);
            System.out.println(hello);
        }
    }
}
