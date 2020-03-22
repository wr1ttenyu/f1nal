package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.add(new MyTask(System.currentTimeMillis() + 2000));
        delayQueue.add(new MyTask(System.currentTimeMillis() + 5000));

        System.out.println("--------------0--------------");
        try {
            System.out.println("--------------1--------------");
            MyTask take = (MyTask)delayQueue.take();
            System.out.println("--------------2--------------");
            MyTask take1 = (MyTask)delayQueue.take();
            System.out.println("--------------3--------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
