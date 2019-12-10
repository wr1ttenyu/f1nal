package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class Screen {

    // 使用单例模式 饿汉式
    private static Screen instance = new Screen();

    private Screen() {}

    public static Screen getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("screen on ...");
    }

    public void off() {
        System.out.println("screen off ...");
    }

    public void up() {
        System.out.println("screen up ...");
    }

    public void down() {
        System.out.println("screen down ...");
    }

}
