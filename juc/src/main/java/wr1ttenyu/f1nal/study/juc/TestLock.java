package wr1ttenyu.f1nal.study.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决多线程问题的三种方式：
 * synchronized: 隐式锁
 * 1. 同步代码块
 * 2. 同步方法
 * <p>
 * jdk1.5 之后
 * 3. 同步锁 Lock
 * 注意：是一个显示锁，需要通过 lock() unlock() 方法加解锁
 */
public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();
    }

    @Test
    public void testVolatile() {
        TestDemo testDemo = new TestDemo();
        new Thread(() -> {
            testDemo.write();
        }).start();

        new Thread(() -> {
            testDemo.multiply();
        }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Ticket implements Runnable {

    // 这里的问题是
    // 在不加锁的情况下 使用 volatile 依然会出现 两个线程出现同一个值
    // 也就是说 如果 volatile 使得多个线程操作的都是操作同一个 内存地址 那么应该能保证多线程下 变量操作的一致性
    // 应该不会出现这个问题
    // 或者是说 volatile 仅仅是针对编译后jvm对指令优化的禁止
    // SOLVE :
    // 当一个变量被volatile修饰时，那么对它的修改会立刻刷新到主存，当其它线程需要读取该变量时，会去内存中读取新值。而普通变量则不能保证这一点。
    // 也就是说 不是操作同一个内存地址
    // 而 --ticketNum 是一个非原子操作 所以当两个线程同时读取一个值 操作完回写 还是相同结果
    // 可见性 顺序性 原子性  volatile 是保证了可见性 和 顺序性
    // 可见性 理解为 写完立即刷新到主存 每次读都去主存中读取 但是 当读取完之后 后续操作就是在读取之后的值之上 进行操作
    //      只有再次有显式读取代码被执行时 才会去主存读取 只有在做读取操作时，发现自己缓存行无效，才会去读主存的值
    // 顺序性 理解为 即禁止指令重排
    private volatile Integer ticketNum = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            /*lock.lock();*/
            while (ticketNum > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "卖票,剩余:" + --ticketNum);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*lock.unlock();*/
        }
    }
}

class TestDemo {
    volatile int a = 0;

    public void write() {
        int b = a;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a++;
        System.out.println(a);
    }

    public void multiply() {
        a++;
    }
}

// SOLVE 懒汉模式 volatile 作用  最强自解析 完全能说明问题 给我点个tm的大赞
class Singleton {
    // 静态字段会进行默认初始化 null
    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getsingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    // 线程一
                    // singleton = new Singleton(); 到底发生了什么
                    // 1. 加载Singleton.class文件到方法区，同时加载Person类中的static属性
                    // 2. 在java堆中开辟空间存放Singleton类对象，但是不进行初始化操作。
                    // 3. singleton 默认初始化数据
                    // 4. 将引用singleton指向java中新开辟的Singleton类对象 此时 singleton 已经不为null了
                    // 3 4 两个步骤不能保证一定按顺序执行 存在指令重排序
                    // 此时 如果线程二 进入 getsingleton 方法 判断 singleton 已经不为空了
                    // 那么就把 singleton 返回了  我们考虑极端情况 此时 线程二继续执行
                    // 而线程一依然没有机会执行 singleton 默认初始化数据  也就是步骤3
                    // 那么 线程二 拿到的singleton 实例 就是一个没有初始化完成的对象  后续执行可能就会出问题
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}