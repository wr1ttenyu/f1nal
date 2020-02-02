package wr1ttenyu.f1nal.study.dubbo.provider.service.impl;

import wr1ttenyu.f1nal.study.dubbo.service.facade.IHelloDubboService;

public class HelloDubboServiceImpl implements IHelloDubboService {

    @Override
    public String hello(String name) {
        return "hello " + name + " ~~~";
    }
}
