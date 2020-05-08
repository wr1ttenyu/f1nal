package wr1ttenyu.study.f1nal.springboot.springstudy;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloServiceImpl implements HelloService {

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct --> HelloServiceImpl");
    }

    @Override
    public String getMessage() {
        return "hello world";
    }
}
