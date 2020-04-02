package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerAndProducer {
    public static void main(String[] args) {
        /*CPWaitNotify cpWaitNotify = new CPWaitNotify();
        new Thread(() -> {cpWaitNotify.produce();}).start();
        new Thread(() -> {cpWaitNotify.consume();}).start();*/

        /*CPLock cpLock = new CPLock();
        new Thread(() -> { cpLock.produce(); }).start();
        new Thread(() -> { cpLock.consume(); }).start();*/

        CPBlockQueue cpBlockQueue = new CPBlockQueue();
        new Thread(() -> { cpBlockQueue.produce(); }).start();
        new Thread(() -> { cpBlockQueue.consume(); }).start();

        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CPWaitNotify {

    private volatile Integer resource = 0;

    public synchronized void produce() {
        for (; ; ) {
            while (resource < 10) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource++;
                System.out.println("生产资源，resource数量：" + resource);
            }
            try {
                System.out.println("资源数量大于10，生产者休眠.....");
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void consume() {
        for (; ; ) {
            while (resource > 0) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource--;
                System.out.println("消费资源，resource数量：" + resource);
            }
            try {
                System.out.println("资源数量为0，消费者休眠.....");
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class CPLock {

    private volatile Integer resource = 0;

    private Lock lock = new ReentrantLock();

    private Condition consumerCondition = lock.newCondition();

    private Condition producerCondition = lock.newCondition();

    public void produce() {
        try {
            lock.lock();
            for (; ; ) {
                while (resource < 10) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resource++;
                    System.out.println("生产资源，resource数量：" + resource);
                }
                try {
                    System.out.println("资源数量大于10，生产者休眠.....");
                    consumerCondition.signal();
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

    }

    public void consume() {
        try {
            lock.lock();
            for (; ; ) {
                while (resource > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resource--;
                    System.out.println("消费资源，resource数量：" + resource);
                }
                try {
                    System.out.println("资源数量为0，消费者休眠.....");
                    producerCondition.signal();
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

}

class CPBlockQueue {

    private volatile Integer resource = 0;

    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public void produce() {
        for (; ; ) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产资源前，resource数量：" + queue.size());
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产资源后，resource数量：" + queue.size());
        }

    }

    public void consume() {
        for (; ; ) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费资源前，resource数量：" + queue.size());
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费资源后，resource数量：" + queue.size());
        }
    }

}