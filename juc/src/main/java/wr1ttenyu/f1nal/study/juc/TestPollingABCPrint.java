package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestPollingABCPrint {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicInteger currentFlag = new AtomicInteger(1);

        // TODO for循环第一遍  感觉应该是都被 wait 住了 实际没有  为什么
        new Thread(new LoopPrintA(lock, condition, currentFlag)).start();
        new Thread(new LoopPrintC(lock, condition, currentFlag)).start();
        new Thread(new LoopPrintB(lock, condition, currentFlag)).start();

    }
}

class LoopPrintA implements Runnable {

    private Lock lock;
    private Condition condition;
    private AtomicInteger currentFlag;

    public LoopPrintA(Lock lock, Condition condition, AtomicInteger currentFlag) {
        this.lock = lock;
        this.condition = condition;
        this.currentFlag = currentFlag;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("A第" + i + "次循环");
                while (!(currentFlag.get() % 3 == 1)) {
                    System.out.println("A第" + i + "次循环 被等待 currentFlag:" + currentFlag);
                    condition.await();
                    System.out.println("A");
                }
                System.out.println(Thread.currentThread().getName() + " print : A");
                currentFlag.getAndIncrement();
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class LoopPrintB implements Runnable {

    private Lock lock;
    private Condition condition;
    private AtomicInteger currentFlag;

    public LoopPrintB(Lock lock, Condition condition, AtomicInteger currentFlag) {
        this.lock = lock;
        this.condition = condition;
        this.currentFlag = currentFlag;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("B第" + i + "次循环");
                while (!(currentFlag.get() % 3 == 2)) {
                    System.out.println("B第" + i + "次循环 被等待 currentFlag:" + currentFlag);
                    condition.await();
                    System.out.println("B");
                }
                System.out.println(Thread.currentThread().getName() + " print : B");
                currentFlag.getAndIncrement();
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class LoopPrintC implements Runnable {

    private Lock lock;
    private Condition condition;
    private AtomicInteger currentFlag;

    public LoopPrintC(Lock lock, Condition condition, AtomicInteger currentFlag) {
        this.lock = lock;
        this.condition = condition;
        this.currentFlag = currentFlag;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("C第" + i + "次循环");
                while(!(currentFlag.get() % 3 == 0)) {
                    System.out.println("C第" + i + "次循环 被等待 currentFlag:" + currentFlag);
                    condition.await();
                    System.out.println("C");
                }
                System.out.println(Thread.currentThread().getName() + " print : C");
                currentFlag.getAndIncrement();
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}