package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 懒汉式（双重检查） 总结：
 * 1. 线程安全 且 实现了懒加载 且 效率高
 * 2. 在实际开发中 推荐使用
 */
public class SingletonType6 {

    public static void main(String[] args) {
         // 测试
        Singleton6 instance1 = Singleton6.getInstance();
        Singleton6 instance2 = Singleton6.getInstance();
        if(instance1 == instance2) System.out.println("懒汉式（双重检查）");

    }
}

class Singleton6 {

    private static volatile Singleton6 instance;

    // 构造器私有化
    private Singleton6() {}

    public static Singleton6 getInstance() {
        if(instance == null) {
            synchronized (Singleton6.class) {
                if(instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
