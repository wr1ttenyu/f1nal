package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 懒汉式（线程安全，同步代码块） 总结：
 * 1. 本意是对 懒汉式（线程安全，同步方法）的改进，但实际还是线程不安全的
 * 2. 生产环境禁用 线程不安全
 */
public class SingletonType5 {

    public static void main(String[] args) {
         // 测试
        Singleton5 instance1 = Singleton5.getInstance();
        Singleton5 instance2 = Singleton5.getInstance();
        if(instance1 == instance2) System.out.println("懒汉式（线程不安全，同步代码块）");

    }
}

class Singleton5 {

    private static Singleton5 instance;

    // 构造器私有化
    private Singleton5() {}

    public static Singleton5 getInstance() {
        if(instance == null) {
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
