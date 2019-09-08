package wr1ttenyu.f1nal.study.juc;

public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        // 重点概念
        // 1. 共享内存  2.工作内存（线程独享）
        // 3. JIT编译器可能对新线程代码里的while循环进行了优化 并因此导致新线程在线程上下文中无法看到变量
        // volatile : 关键字volatile的作用是告知JIT编译器不要对被标记变量执行任何可能影响其访问顺序的优化
        //     也即 对jvm对编译后指令的优化 导致while循环不去主存中读取变量  volatile 禁止了这种优化
        // 在多个字段被多个线程并发访问的场景下，由于针对每个volatile字段的访问都是各自独立处理的，
        // 并且也无法将这些访问统一协调成一次访问，所以volatile关键字无法保证整体操作的原子性。
        // 该问题所造成的后果是，线程很可能对某些字段只能看到其中间结果，而对另一些变量则看到的是最终的变更结果。
        //    也即 跟 synchronize 相比不具有互斥性  不能保证整体操作的原子性
        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("-------- program finish --------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        flag = true;

        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
