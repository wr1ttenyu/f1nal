package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class DvdPlayer {

    // 使用单例模式 饿汉式
    private static DvdPlayer instance = new DvdPlayer();

    private DvdPlayer() {}

    public static DvdPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("dvd on ...");
    }

    public void off() {
        System.out.println("dvd off ...");
    }

    public void play() {
        System.out.println("dvd play ...");
    }

    public void pause() {
        System.out.println("dvd pause ...");
    }

}
