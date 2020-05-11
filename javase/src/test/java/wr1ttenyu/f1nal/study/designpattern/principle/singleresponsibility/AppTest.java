package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        int i = 1/2;
        System.out.println(i);
    }
}

class MyTask implements Delayed {

    private long endTime;

    public MyTask(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.endTime, o.getDelay(TimeUnit.MILLISECONDS));
    }

}
