package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLockInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("park了");
            LockSupport.park();
            System.out.println("unpark了");
            if (Thread.interrupted()) System.out.println("1111");
        });



        thread.start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("准备中断");
        thread.interrupt();
        System.out.println("中断成功");

        TimeUnit.SECONDS.sleep(200);
    }

    /*public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();

        MyWorkerA myWorker = new MyWorkerA(reentrantLock);

        new Thread(myWorker, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(myWorker, "B").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(myWorker, "C").start();

        TimeUnit.SECONDS.sleep(1);

        Thread main = Thread.currentThread();

        new Thread(new MyWorkerB(main)).start();

        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " : 等待锁");
            reentrantLock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("响应到了中断");
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

        TimeUnit.SECONDS.sleep(15);
    }*/
}

class MyWorkerA implements Runnable {

    ReentrantLock reentrantLock;

    public MyWorkerA(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " : 等待锁");
            reentrantLock.lock();
            System.out.println("线程 " + Thread.currentThread().getName() + " : 获取到了锁");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            System.out.println("线程 " + Thread.currentThread().getName() + " : 释放锁");
        }
    }
}

class MyWorkerB implements Runnable {

    private Thread thread;

    public MyWorkerB(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            System.out.println("准备 interrupt main 线程 " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            thread.interrupt();
            System.out.println("interrupt main 线程 成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}