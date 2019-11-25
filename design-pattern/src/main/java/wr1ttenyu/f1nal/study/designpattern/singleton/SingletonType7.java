package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 饿汉式（静态内部类） 总结：
 * 1. 优点：简单 私有静态内部类 不会跟随 外部类的加载而加载 直到getInstance()被调用
 *          线程安全 且 实现了懒加载 且 效率高  利用了jvm虚拟机类加载机制 保证线程安全
 * 2. 在实际开发中 推荐使用
 */
public class SingletonType7 {

    public static void main(String[] args) {
         // 测试
        Singleton7 instance1 = Singleton7.getInstance();
        Singleton7 instance2 = Singleton7.getInstance();
        if(instance1 == instance2) System.out.println("饿汉式 静态内部类");

    }
}

class Singleton7 {

    private static Singleton7 instance;

    private Singleton7() {};

    // 私有静态内部类 不会跟随 外部类的加载而加载 直到getInstance()被调用
    private static class Singleton7Instance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return Singleton7Instance.INSTANCE;
    }
}

