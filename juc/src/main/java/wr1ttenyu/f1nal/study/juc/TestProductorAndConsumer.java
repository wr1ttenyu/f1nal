package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumer {

    public static void main(String[] args) {

        Clerk clerk = new ClerkWithSynchronize();

        new Thread(new Productor(clerk)).start();
        new Thread(new Consumer(clerk)).start();
        new Thread(new Productor(clerk)).start();
        new Thread(new Consumer(clerk)).start();

    }

}

// TODO 完成 ClerkWithLock
class ClerkWithLock implements Clerk {

    private int commodityNum = 0;

    // TODO ReentrantLock 有什么特性
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    public void get() {
        try {
            lock.lock();
            while (commodityNum < 10) {
                System.out.println("进货，商品当前数量:" + ++commodityNum);
            }
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        while (commodityNum > 0) {
            System.out.println("出售，商品当前数量:" + ++commodityNum);
        }
    }

}

class ClerkWithSynchronize implements Clerk {

    private int commodityNum = 0;

    public void get() {
        synchronized (this) {
            while (true) {
                if (commodityNum < 1) {
                    System.out.println("进货，商品当前数量:" + ++commodityNum);
                    this.notifyAll();
                } else {
                    System.out.println("货满，不能进货，商品当前数量:" + commodityNum);
                    try {
                        this.wait();
                        // TODO InterruptedException 弄清下
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }

    public void sale() {
        synchronized (this) {
            while (true) {
                if (commodityNum > 0) {
                    System.out.println("出售，商品当前数量:" + --commodityNum);
                    this.notifyAll();
                } else {
                    System.out.println("缺货，不能出售，商品当前数量:" + commodityNum);
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Productor implements Runnable {

    Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            clerk.get();
        }
    }
}

class Consumer implements Runnable {

    Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            clerk.sale();
        }
    }
}

interface Clerk {

    void get();

    void sale();
}
