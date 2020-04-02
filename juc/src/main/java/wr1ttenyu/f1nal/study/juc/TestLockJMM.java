package wr1ttenyu.f1nal.study.juc;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class TestLockJMM {

    public static void main(String[] args) {

        Resource target = new Resource();
        Thread thread = new Thread(target);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        target.setI(5);
        try {
            LockSupport.unpark(thread);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Resource implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        AtomicInteger integer = new AtomicInteger();
        while (i == 0) {
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            integer.getAndIncrement();
            /*System.out.println("睡眠结束。。。。。");*/
        }

        System.out.println("循环结束。。。。。integer:" + integer.get());
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

}
