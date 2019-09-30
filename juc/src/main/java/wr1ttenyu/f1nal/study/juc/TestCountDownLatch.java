package wr1ttenyu.f1nal.study.java8;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new CountDownLatchDemo(countDownLatch)).start();
        }
        System.out.println("等待子线程完成任务 -----");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("全部子线程完成任务 -----");
    }

}

class CountDownLatchDemo implements Runnable {

    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "已完成任务 -----");
        countDownLatch.countDown();
    }
}