package wr1ttenyu.f1nal.study.javase.jvm.gc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

public class HelloGC {

    private static byte[] test = new byte[1024 * 1024 * 12];

    // jinfo -flags [pid] 查看JVM所有初始配置项
    // jinfo -flag [flagName:PrintGCDetail] [pid]
    // jinfo -flag PrintGCDetails 16388
    // jinfo -flag MetaspaceSize 15756
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*System.out.println("Hello GC");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*try {
            new Thread(()->{testStack(0);}, "th1").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new Thread(()->{testStack(0);}, "th2").start();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

       /* testStack(0);*/

    }

    private static void testStack(Integer deepNum) {
        // 11275
        deepNum++;
        System.out.println(Thread.currentThread().getName() + ": " + deepNum);
        testStack(deepNum);
    }
}
