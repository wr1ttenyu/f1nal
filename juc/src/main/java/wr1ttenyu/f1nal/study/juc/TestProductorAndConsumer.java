package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumer {

    public static void main(String[] args) {

        Clerk clerk = new ClerkWithSynchronize();

        Thread productor = new Thread(new Productor(clerk));
        Thread consumer = new Thread(new Consumer(clerk));

        //创建守护线程
        Thread daemon = new Thread(() -> {
            for (;;) {
                if (!consumer.isAlive() || !productor.isAlive()) {
                    ((ClerkWithSynchronize) clerk).setOverWork(true);
                    System.out.println("结束工作 ------- cousumer : " + consumer.isAlive()
                            + " productor : " + productor.isAlive());
                    break;
                }
            }
        });

        consumer.start();
        productor.start();

        daemon.setDaemon(true);
        daemon.start();


    }

}

// TODO 完成 ClerkWithLock
class ClerkWithLock implements Clerk {

    private boolean overWork = false;

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
        lock.lock();
        while (commodityNum > 0) {
            System.out.println("出售，商品当前数量:" + --commodityNum);
        }
        lock.unlock();
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
            // 这里去掉 while 跑起来 会导致最后有一个线程没有结束  如何解决
            // 如果是 commodityNum < 1 则导致 如果不是进货 售货交替进行 而是多次进货后 售货一次 必然导致售货最后进入wait 无进货唤醒
            // 依然有线程没有结束 可以使用 标志位 如果有一个线程结束了 就更改标志位  检测标志位true 则直接结束
            /*for (;;) {*/
            if (overWork) {
                System.out.println("不进货了 结束工作 ----");
                return;
            }

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
            /*}*/

        }
    }

    public void sale() {
        synchronized (this) {
            /* for (;;) {*/
            if (overWork) {
                System.out.println("不买了 回家了 ----");
                return;
            }

            if (commodityNum > 0) {
                System.out.println("出售，商品当前数量:" + --commodityNum);
                this.notifyAll();
            } else {
                System.out.println("缺货，不能出售，等一会儿试试，商品当前数量:" + commodityNum);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*  }*/
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
