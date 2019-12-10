package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class Projector {

    // 使用单例模式 饿汉式
    private static Projector instance = new Projector();

    private Projector() {}

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("projector on ...");
    }

    public void off() {
        System.out.println("projector off ...");
    }

    public void project() {
        System.out.println("projector project ...");
    }

    public void focus() {
        System.out.println("projector focus ...");
    }

}
