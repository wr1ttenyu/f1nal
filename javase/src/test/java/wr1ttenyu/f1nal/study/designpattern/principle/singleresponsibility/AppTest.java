package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        lock.writeLock().lock();
        lock.writeLock().lock();
        System.out.println(123);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.writeLock().unlock();
        lock.writeLock().unlock();
    }

    /*public static void syncMethod1() {
        lock.lock();
        try {
            System.out.println("线程1进入");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void syncMethod2() {
        System.out.println("线程2开始");
        lock.lock();
        try {
            System.out.println("线程2进入");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/
}

class TestClass {
    int num = 0;

    public void setNum(int num) {
        this.num += num;
    }
}


