package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

public class DeadThreadTest {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 类的加载是同步的 只能由一个线程去完成
            DeadThread deadThread = new DeadThread();
        };

        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable, "线程2");

        thread1.start();
        thread2.start();

    }
}

class DeadThread {
    static {
        if(true) {
            System.out.println(Thread.currentThread().getName() + " 初始化当前类");
            while (true) {

            }
        }
    }
}
