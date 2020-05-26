package wr1ttenyu.f1nal.study.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {
    private static int flag = 0;

    private static Lock lock = new ReentrantLock();

    private static Condition contiditon = lock.newCondition();

    public static void main(String[] args) {
        for(int i = 0; i < 20; i++) {
            try{
                int j = i;
                new Thread(() -> {
                    lock.lock();
                    while(flag != j) {
                        try {
                            contiditon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(j);
                    flag++;
                    contiditon.signalAll();
                }).start();
            } finally {
                lock.unlock();
            }
        }
    }
}
