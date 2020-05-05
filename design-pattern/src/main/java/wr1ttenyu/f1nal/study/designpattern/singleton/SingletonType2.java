package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 饿汉式（静态代码块） 总结：
 * 1. 优点：简单 类装载的时候完成实例化  避免线程同步的问题
 * 2. 缺点：在类装载的时候就完成了实例化，没有Lazy-Loading的效果。如果始终没有使用该类实例 则造成内存浪费
 */
public class SingletonType2 {

    public static void main(String[] args) {
         // 测试
        Singleton2 instance1 = Singleton2.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        if(instance1 == instance2) System.out.println("饿汉式 静态代码块");

    }
}

class Singleton2 {

    // 构造器私有化
    private Singleton2() {}

    private static Singleton2 instance;

    static {
        instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return instance;
    }
}
