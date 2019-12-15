package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        /**
         *  public static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h)
         *  ClassLoader loader : 指定当前目标对象使用的类加载器，获取加载器的方法固定
         *  Class[] interfaces : 目标对象实现的接口类型，使用泛型方法确认类型
         *  InvocationHandler h : 目标对象方法调用时，真实执行的方法
         *  {@link InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}
         *      invoke 方法三个参数：
         *      invoke(Object proxy, Method method, Object[] args)
         *      proxy: 目标对像的代理对象
         *      method: 目标对像被调用的方法
         *      args: 目标对象被调用的方法的参数
         */
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy1, method, args) -> {
            System.out.println("JDK动态代理开始");
            Object invoke = method.invoke(target, args);
            return invoke;
        });

        return proxy;
    }
}
