package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 懒汉式（线程安全，同步方法） 总结：
 * 1. 优点：有Lazy-Loading的效果，而且是线程安全的
 * 2. 缺点：效率太低 getInstance()是同步方法 每次调用都是同步的
 * 3. 生产环境禁用 效率太低
 */
public class SingletonType4 {

    public static void main(String[] args) {
         // 测试
        Singleton4 instance1 = Singleton4.getInstance();
        Singleton4 instance2 = Singleton4.getInstance();
        if(instance1 == instance2) System.out.println("懒汉式（线程安全，同步方法）");

    }
}

class Singleton4 {

    private static Singleton4 instance;

    // 构造器私有化
    private Singleton4() {}

    public static synchronized Singleton4 getInstance() {
        if(instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
