package wr1ttenyu.f1nal.study.nio;

import java.io.File;

public class TestFileMonitor {

    public static void main(String[] args) {
        // 用户目录 和 用户工作目录
        println("用户目录：" + System.getProperty("user.home"));
        println("用户工作目录：" + System.getProperty("user.dir"));

        File userDir = new File(""); // 等同于 File file = new File(System.getProperty("user.dir"));

        // TODO continue
        long lastModifyTime = userDir.lastModified();

        for(;;) {
            if(lastModifyTime == userDir.lastModified()) {
                continue;
            }

        }

    }

    private static void println(Object object) {
        System.out.println(object);
    }
}
