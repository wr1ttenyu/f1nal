package wr1ttenyu.f1nal.study.nio;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestFileMonitor {

    public static void main(String[] args) {
        // 用户目录 和 用户工作目录
        println("用户目录：" + System.getProperty("user.home"));
        println("用户工作目录：" + System.getProperty("user.dir"));

        File userDir = new File("C:\\MyFile\\test"); // 等同于 File file = new File(System.getProperty("user.dir"));
        List<String> subFiles = list(userDir.list());
        // TODO continue
        long lastModifyTime = userDir.lastModified();
        for(;;) {
            if (lastModifyTime == userDir.lastModified()) {
                continue;
            }
            lastModifyTime = userDir.lastModified();
            List<String> newSubFiles = list(userDir.list());
            newSubFiles.removeAll(subFiles);

            Stream.of(newSubFiles).forEach(TestFileMonitor::println);
        }


    }

    private static <T> List<T> list(T... values) {
        return new ArrayList<T>(Arrays.asList(values));
    }

    private static void println(Object object) {
        System.out.println(object);
    }
}
