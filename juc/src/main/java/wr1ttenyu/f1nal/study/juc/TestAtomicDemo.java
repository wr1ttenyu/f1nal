package wr1ttenyu.f1nal.study.java8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * jdk1.5 以后 java.util.concurrent.automic 包下提供了常用的原子变量
 *  1. volatile 保证内存可见性
 *  2. CAS (compare and swap)
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {

    /*private int serialNumber = 0;*/

    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "getSerialNumber():" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
