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

        // for循环第一遍  感觉应该是都被 await 住了 实际没有  为什么
        // SOLVE： condition.await() 等待被唤醒 并且 释放了锁
        // 每个线程执行的时候 都调用了 condition.signalAll(); 唤醒了其他正在等待的线程
        // 被唤醒之后 等待再次获取锁 也就是说 假设 A 被 await() 住之后
        // B 继续执行的时候  其实 A 被唤醒了 等待获取锁 继续执行
        // 因为三个线程 肯定有一个满足 条件打印 print 且把标志位改为满足剩下的两个线程之一的打印条件
        // 所以 能保证 他唤醒其他两个线程 并且 把这个循环继续进行直到结束

        new Thread(new LoopPrintC(lock, condition, currentFlag)).start();
        new Thread(new LoopPrintA(lock, condition, currentFlag)).start();
        new Thread(new LoopPrintB(lock, condition, currentFlag)).start();

        /*new Thread(() -> {
            try {
                lock.lock();
                System.out.println("A 获取了锁 先睡眠5秒 然后await 看看B 能不能获取锁 如果获取了 证明await 释放了锁");
                System.out.println("A 开始睡 " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("A 释放锁 " + System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                System.out.println("B 开始执行 " + System.currentTimeMillis());
                lock.lock();
                System.out.println("B 获取了锁 " + System.currentTimeMillis() + " 证明 await 释放了锁");
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();*/
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
                /*System.out.println("A第" + i + "次循环");*/
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
            System.out.println("A解锁");
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
                /*System.out.println("B第" + i + "次循环");*/
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
            System.out.println("B解锁");
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
                /*System.out.println("C第" + i + "次循环");*/
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
            System.out.println("C解锁");
            lock.unlock();
        }
    }
}