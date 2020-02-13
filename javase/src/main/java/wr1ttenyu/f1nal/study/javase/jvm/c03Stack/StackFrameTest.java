package wr1ttenyu.f1nal.study.javase.jvm.c03Stack;

/**
 * 方法的结束方式分为两种：
 *      1. 正常结束，以return的方式结束（返回值为void的方式，也可以写return，只不过这个return可以省略）
 *      2. 方法的执行中出现了未捕获的异常，以抛出异常的方式结束
 */
public class StackFrameTest {

    public static void main(String[] args) {

    }

    private void method1() {
        System.out.println("method1 执行开始");
        method2();
        System.out.println("method1 执行结束");
        int m = 10 / 0;
    }

    private double method2() {
        System.out.println("method2 执行开始");
        int i = method3();
        System.out.println("method2 执行结束");
        double j = 20.8;
        return i + j;
    }

    private int method3() {
        System.out.println("method3 执行开始");
        int i = 10;
        System.out.println("method3 执行结束");
        return i;
    }
}
