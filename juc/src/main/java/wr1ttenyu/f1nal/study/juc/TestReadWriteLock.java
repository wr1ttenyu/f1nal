package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    public static void main(String[] args) {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                readWriteLockDemo.write();
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                readWriteLockDemo.read();
            }).start();
        }
    }

}

class ReadWriteLockDemo {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private int number = 0;

    public void read() {
        try {
            readWriteLock.readLock().lock();
            System.out.println("read number : " + number);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write() {
        try {
            readWriteLock.writeLock().lock();
            System.out.println("write number : " + ++number);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
