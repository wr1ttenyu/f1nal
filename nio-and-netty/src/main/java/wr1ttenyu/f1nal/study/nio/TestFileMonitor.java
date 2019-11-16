package wr1ttenyu.f1nal.study.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestFileMonitor {

    public static void main(String[] args) {
        // nio 的监听方式
        monitorFileWithNio();
        // 普通监听方式
        // monitorFileSimple();
    }

    /**
     * 文件监控 普通方式 非NIO
     */
    private static void monitorFileWithNio() {
        // 用户目录 和 用户工作目录
        println("用户目录：" + System.getProperty("user.home"));
        println("用户工作目录：" + System.getProperty("user.dir"));

        Path userDirPath = Paths.get("C:\\MyFile\\test");
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            userDirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds
                    .ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            for (;;) {
                try {
                    WatchKey key = watchService.take();
                    List<WatchEvent<?>> watchEvents = key.pollEvents();
                    for (WatchEvent<?> watchEvent : watchEvents) {
                        Path fileName = (Path)watchEvent.context();
                        if(watchEvent.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                            System.out.println("创建了文件:" +  fileName.getFileName());
                        }
                        if(watchEvent.kind().equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                            System.out.println("删除了文件:" +  fileName.getFileName());
                        }
                        if(watchEvent.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                            System.out.println("修改了文件:" +  fileName.getFileName());
                        }
                        key.reset();
                    }
                } catch (InterruptedException e) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件监控 普通方式 非NIO
     */
    private static void monitorFileSimple() {
        // 用户目录 和 用户工作目录
        println("用户目录：" + System.getProperty("user.home"));
        println("用户工作目录：" + System.getProperty("user.dir"));

        File userDir = new File("C:\\MyFile\\test"); // 等同于 File file = new File(System.getProperty("user.dir"));
        List<String> subFiles = list(userDir.list());
        long lastModifyTime = userDir.lastModified();
        for (; ; ) {
            if (lastModifyTime == userDir.lastModified()) {
                continue;
            }
            lastModifyTime = userDir.lastModified();
            List<String> newSubFiles = list(userDir.list());
            newSubFiles.removeAll(subFiles);
            Stream.of(newSubFiles).forEach(TestFileMonitor::println);
            subFiles = list(userDir.list());
        }
    }

    private static <T> List<T> list(T... values) {
        return new ArrayList<T>(Arrays.asList(values));
    }

    private static void println(Object object) {
        System.out.println(object);
    }
}
