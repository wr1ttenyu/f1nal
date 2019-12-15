package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.dynamicproxy;

/**
 *  动态代理基本介绍
 *      1. 代理对象不需要实现接口，但是目标对象要实现接口，否则不能用动态代理
 *      2. 代理对象的生成，是利用 JDK 的API，动态的在内存中构建代理对象
 *      3. 动态代理也叫做：JDK 代理、接口代理
 */
public class Client {

    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao)new ProxyFactory(target).getProxyInstance();
        System.out.println("proxyInstance=" + proxyInstance.getClass());
        ITeacherDao teacher = proxyInstance.getTeacher();
        System.out.println(teacher == proxyInstance);
    }
}
