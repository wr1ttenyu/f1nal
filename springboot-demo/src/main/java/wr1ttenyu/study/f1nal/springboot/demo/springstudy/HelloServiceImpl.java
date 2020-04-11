package wr1ttenyu.study.f1nal.springboot.demo.springstudy;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String getMessage() {
        return "hello world";
    }
}
