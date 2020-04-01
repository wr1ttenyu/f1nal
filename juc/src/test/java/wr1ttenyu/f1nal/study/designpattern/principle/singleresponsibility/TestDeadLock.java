package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;

import java.util.concurrent.TimeUnit;

public class TestDeadLock {

    public static void main(String[] args) {
        String lockA = "锁A";
        String lockB = "锁B";
        new Thread(new DeadLockResource(lockA, lockB)).start();
        new Thread(new DeadLockResource(lockB, lockA)).start();
    }

}

class DeadLockResource implements Runnable {

    private String lockA;

    private String lockB;

    public DeadLockResource(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 持有锁 "+lockA+" 等待获取锁 " + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("全部锁获取了。。。。");
            }
        }
    }
}