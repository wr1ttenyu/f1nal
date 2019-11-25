package wr1ttenyu.f1nal.study.designpattern.singleton;

/**
 * 懒汉式（线程不安全） 总结：
 * 1. 优点：有Lazy-Loading的效果，但是只能在单线程下使用
 * 2. 缺点：线程不安全，启动时多线程进入 if(instance == null) 判断中，导致类实例有多个
 * 3. 生产环境禁用 线程不安全
 */
public class SingletonType3 {

    public static void main(String[] args) {
         // 测试
        Singleton3 instance1 = Singleton3.getInstance();
        Singleton3 instance2 = Singleton3.getInstance();
        if(instance1 == instance2) System.out.println("懒汉式（线程不安全）");

    }
}

class Singleton3 {

    private static Singleton3 instance;

    // 构造器私有化
    private Singleton3() {}

    public static Singleton3 getInstance() {
        if(instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
