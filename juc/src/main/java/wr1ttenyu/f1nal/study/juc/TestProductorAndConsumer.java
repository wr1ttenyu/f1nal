package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumer {

    public static void main(String[] args) {

        Clerk clerk = new ClerkWithLock();

        Thread productor = new Thread(new Productor(clerk));
        Thread consumer = new Thread(new Consumer(clerk));

        consumer.start();
        productor.start();

        /*//创建守护线程
        Thread daemon = new Thread(() -> {
            for (; ; ) {
                if (!consumer.isAlive() || !productor.isAlive()) {
                    ((ClerkWithSynchronize) clerk).setOverWork(true);
                    System.out.println("结束工作 ------- cousumer : " + consumer.isAlive()
                            + " productor : " + productor.isAlive());
                    break;
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();*/

        interruptedExceptionTest();
    }

    public static void interruptedExceptionTest() {
        // java.lang.Thread.interrupt 从方法的英文注释来看
        // 我们知道 其实就是对线程在阻塞状态下  我们能够对线程发送一个中断请求
        // 然后线程根据自己的情况来决定是否中断自己的工作  提供了一种中断耗时长线程工作的机制
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // 抛出异常的同时它会将线程B的是否请求中断标志置为false
                // 这里再次调用  Thread.currentThread().interrupt(); 其实是将中断标志置为true 让上层代码知道
                // 其实我们是对InterruptedException 做出反应了
                Thread.currentThread().interrupt();
                System.out.println("thread accept interrupt");
            }

            System.out.println("thread continue to run");
        });

        thread.start();
        thread.interrupt();
        boolean interrupted1 = thread.isInterrupted();
        System.out.println("thread interrupt status is " + interrupted1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean interrupted2 = thread.isInterrupted();

        System.out.println("thread interrupt status is " + interrupted2);

        System.out.println("main thread finish");
    }

}

class ClerkWithLock implements Clerk {

    private boolean overWork = false;

    private int commodityNum = 0;

    // SOLVE ReentrantLock 有什么特性 https://www.cnblogs.com/gxyandwmm/p/9387833.html
    // 可重入锁 即一个线程可以多次获取锁 但是也必须释放同样次数 synchronized 也同样是可重入锁 但是不用手动控制锁的释放
    // 中断响应（lockInterruptibly） lock.lockInterruptibly();
    // 对于 synchronized 来说，如果一个线程在等待锁，那么结果只有两种情况，获得这把锁继续执行，或者线程就保持等待。
    // 而使用重入锁，提供了另一种可能，这就是线程可以被中断。也就是在等待锁的过程中，程序可以根据需要取消对锁的需求。
    // ReentrantLock 可以通过构造函数 来指定是否是 公平锁或非公平锁  默认是非公平锁
    // ReentrantLock 重要的API：
    // lock()：获得锁，如果锁被占用，进入等待。
    // lockInterruptibly()：获得锁，但优先响应中断。
    // tryLock()：尝试获得锁，如果成功，立即放回 true，反之失败返回 false。该方法不会进行等待，立即返回。
    // tryLock(long time, TimeUnit unit)：在给定的时间内尝试获得锁。
    // unLock()：释放锁。
    // TODO 等待队列：所有没有请求到锁的线程，会进入等待队列进行等待。待有线程释放锁后，系统才能够从等待队列中唤醒一个线程，继续工作。队列同步器——AQS
    // TODO 阻塞原语 park() 和 unpark()，用来挂起和恢复线程。没有得到锁的线程将会被挂起。关于阻塞原语，详见：线程阻塞工具类——LockSupport
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get() {
        try {
            lock.lock();
            for(;;) {
                if (commodityNum < 1) {
                    System.out.println("进货，商品当前数量:" + ++commodityNum);
                    condition.signalAll();
                    break;
                } else {
                    System.out.println("货满，不能进货，商品当前数量:" + commodityNum);
                    try {
                        condition.await();
                        // SOLVE InterruptedException 弄清下
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        try {
            lock.lock();
            for(;;) {
                if (overWork) {
                    System.out.println("不买了 回家了 ----");
                    return;
                }

                if (commodityNum > 0) {
                    System.out.println("出售，商品当前数量:" + --commodityNum);
                    condition.signalAll();
                    break;
                } else {
                    System.out.println("缺货，不能出售，等一会儿试试，商品当前数量:" + commodityNum);
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isOverWork() {
        return overWork;
    }

    public void setOverWork(boolean overWork) {
        this.overWork = overWork;
    }
}

class ClerkWithSynchronize implements Clerk {

    private boolean overWork = false;

    private int commodityNum = 0;

    public void get() {
        synchronized (this) {
            // SOLVE 这里去掉 while 跑起来 会导致最后有一个线程没有结束  如何解决
            // 如果是 commodityNum < 1 则导致 如果不是进货 售货交替进行 而是多次进货后 售货一次 必然导致售货最后进入wait 无进货唤醒
            // 依然有线程没有结束 可以使用 标志位 如果有一个线程结束了 就更改标志位  检测标志位true 则直接结束
            // 如果使用 for(;;) 则保证一定可以进货成功  也保证出售成功  也即不会有虚假唤醒  就不会有线程不结束的结果
            for (; ; ) {
                if (overWork) {
                    System.out.println("不进货了 结束工作 ----");
                    return;
                }

                if (commodityNum < 1) {
                    System.out.println("进货，商品当前数量:" + ++commodityNum);
                    this.notifyAll();
                    break;
                } else {
                    System.out.println("货满，不能进货，商品当前数量:" + commodityNum);
                    try {
                        this.wait();
                        // SOVLE InterruptedException 弄清下
                        // 详见 wr1ttenyu.f1nal.study.juc.TestProductorAndConsumer.interruptedExceptionTest
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void sale() {
        synchronized (this) {
            for (; ; ) {
                if (overWork) {
                    System.out.println("不买了 回家了 ----");
                    return;
                }

                if (commodityNum > 0) {
                    System.out.println("出售，商品当前数量:" + --commodityNum);
                    this.notifyAll();
                    break;
                } else {
                    System.out.println("缺货，不能出售，等一会儿试试，商品当前数量:" + commodityNum);
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public boolean isOverWork() {
        return overWork;
    }

    public void setOverWork(boolean overWork) {
        this.overWork = overWork;
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
