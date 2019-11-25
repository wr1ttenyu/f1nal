package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 饿汉式（静态常量） 总结：
 * 1. 优点：简单 类装载的时候完成实例化  避免线程同步的问题
 * 2. 缺点：在类装载的时候就完成了实例化，没有Lazy-Loading的效果。如果始终没有使用该类实例 则造成内存浪费
 */
public class SingletonType1 {

    public static void main(String[] args) {
         // 测试
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        if(instance1 == instance2) System.out.println("饿汉式 静态变量");

    }
}

class Singleton1 {

    // 构造器私有化
    private Singleton1() {}

    private final static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }
}
