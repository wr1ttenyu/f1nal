package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决多线程问题的三种方式：
 * synchronized: 隐式锁
 * 1. 同步代码块
 * 2. 同步方法
 * <p>
 * jdk1.5 之后
 * 3. 同步锁 Lock
 * 注意：是一个显示锁，需要通过 lock() unlock() 方法加解锁
 */
public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();
    }
}

class Ticket implements Runnable {

    // TODO 这里的问题是
    // 在不加锁的情况下 使用 volatile 依然会出现 两个线程出现同一个值
    // 也就是说 如果 volatile 使得多个线程操作的都是操作同一个 内存地址 那么应该能保证多线程下 变量操作的一致性
    // 应该不会出现这个问题
    // 或者是说 volatile 仅仅是针对编译后jvm对指令优化的禁止
    private volatile Integer ticketNum = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            /*lock.lock();*/
            while (ticketNum > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "卖票,剩余:" + --ticketNum);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*lock.unlock();*/
        }
    }
}
