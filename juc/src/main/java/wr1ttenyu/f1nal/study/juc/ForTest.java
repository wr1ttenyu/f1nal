package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(() -> {
            System.out.println("123");
            System.out.println(Thread.currentThread().getName());
        });
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            throw new NullPointerException();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(() -> {
            System.out.println("456");
            System.out.println(Thread.currentThread().getName());
        });

        executorService.shutdown();
    }

}
