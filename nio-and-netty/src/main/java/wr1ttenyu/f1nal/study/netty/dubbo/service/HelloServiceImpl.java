package wr1ttenyu.f1nal.study.netty.dubbo.service;

public class HelloServiceImpl implements IHelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name + " ~~~ ";
    }
}
