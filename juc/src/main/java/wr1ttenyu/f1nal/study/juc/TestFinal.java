package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.*;

public class TestFinal {

   /* int i; // 普通变量
    final int j = 0 ; // final 变量
    static TestFinal obj;

    public void FinalExample() { // 构造函数
        i = 1; // 写普通域
        j = 2; // 写 final 域
    }

    public static void writer() { // 写线程 A 执行
        obj = new TestFinal();
    }

    public static void reader() { // 读线程 B 执行
        TestFinal object = obj; // 读对象引用
        int a = object.i; // 读普通域         a=1或者a=0或者直接报错i没有初始化
        int b = object.j; // 读 final域      b=2
    }*/

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(123);
        });
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(1234);
    }
}

