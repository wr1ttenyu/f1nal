package wr1ttenyu.f1nal.study.designpattern.pattern23.proxy.staticproxy;

/**
 * 静态代理：需要被代理对象和代理对象实现同一接口或者是继承相同的父类，
 *      目的是使调用者使用统一的 API 进行访问
 *
 * 优点：无需修改被代理对象的代码前提下，能通过代理对象对目标功能实现增强
 * 缺点：因为代理对象需要与被代理对象实现一样的接口，所以会有很多代理类
 *       一旦接口增加方法，目标对象与代理对象都要维护
 */
public class Client {

    public static void main(String[] args) {
        TeacherDao teacherDao = new TeacherDao();
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        teacherDaoProxy.teach();
    }
}
