package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();

        CountDownLatch countDownLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                /*try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                for (int j = 0; j < 1000; j++) {
                    testClass.setNum(100);
                }
            }).start();
           /* countDownLatch.countDown();*/
            /*System.out.println("countDownLatch: " + countDownLatch.getCount());*/
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(testClass.num);
    }
}

class TestClass {
    int num = 0;

    public void setNum(int num) {
        this.num += num;
    }
}


