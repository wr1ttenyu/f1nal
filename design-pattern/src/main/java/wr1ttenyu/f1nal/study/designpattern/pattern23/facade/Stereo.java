package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class Stereo {

    // 使用单例模式 饿汉式
    private static Stereo instance = new Stereo();

    private Stereo() {}

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("stereo on ...");
    }

    public void off() {
        System.out.println("stereo off ...");
    }

    public void up() {
        System.out.println("stereo up ...");
    }

    public void down() {
        System.out.println("stereo down ...");
    }

}
