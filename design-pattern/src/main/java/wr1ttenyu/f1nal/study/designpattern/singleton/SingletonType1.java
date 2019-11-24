package wr1ttenyu.f1nal.study.designpattern.singleton;

public class SingletonType1 {

    public static void main(String[] args) {
         // 测试
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        if(instance1 == instance2) System.out.println("饿汉式 静态变量");

    }
}

class Singleton {

    private Singleton() {}

    private final static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
