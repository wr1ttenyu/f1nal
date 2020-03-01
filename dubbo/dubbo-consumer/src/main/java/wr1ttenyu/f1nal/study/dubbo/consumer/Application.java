package wr1ttenyu.f1nal.study.dubbo.consumer;

import org.apache.zookeeper.client.FourLetterWordMain;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wr1ttenyu.f1nal.study.dubbo.service.facade.IHelloDubboService;

import java.io.IOException;
import java.util.Scanner;

public class Application {

   /* private static Logger LOGGER = LoggerFactory.getLogger(Application.class);*/

    public static void main(String[] args) {
        try {
            FourLetterWordMain.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/dubbo-consumer.xml"});
        context.start();

        /*LOGGER.info("------------------------ dubbo consumer start ------------------------");*/

        IHelloDubboService helloDubboService = context.getBean(IHelloDubboService.class);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.next();
            String hello = helloDubboService.hello(input);
            System.out.println(hello);
        }
    }
}
