package wr1ttenyu.f1nal.study.netty.dubbo.spring;

import wr1ttenyu.f1nal.study.netty.dubbo.netty.DubboClient;
import wr1ttenyu.f1nal.study.netty.dubbo.netty.MyDubboClientHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServiceProxy<T>  implements InvocationHandler {

    private Class<T> interfaceType;

    private MyDubboClientHandler dubboClientHandler;



    public ServiceProxy(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
        this.dubboClientHandler = DubboClient.getDubboClientHandler();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String argsPrefix = interfaceType.getName() + "#" + interfaceType
                .getMethod("hello", String.class).getName() + "#";
        dubboClientHandler.setParam(argsPrefix + args[0]);
        Future<String> future = DubboClient.getExecutor().submit(dubboClientHandler);
        return future.get();
    }
}