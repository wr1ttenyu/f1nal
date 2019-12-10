package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class Popcorn {

    // 使用单例模式 饿汉式
    private static Popcorn instance = new Popcorn();

    private Popcorn() {}

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("popcorn on ...");
    }

    public void off() {
        System.out.println("popcorn off ...");
    }

    public void pop() {
        System.out.println("popcorn pop ...");
    }
}
