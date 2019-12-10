package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class TheaterLight {

    // 使用单例模式 饿汉式
    private static TheaterLight instance = new TheaterLight();

    private TheaterLight() {}

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("theater light on ...");
    }

    public void off() {
        System.out.println("theater light off ...");
    }

    public void dim() {
        System.out.println("theater light dim ...");
    }

    public void bright() {
        System.out.println("theater light bright ...");
    }

}
