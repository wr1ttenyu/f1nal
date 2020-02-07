package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

public class ClassInitTest {

    private static int num1 = 1;

    static {
        num1 = 2;
        num2 = 20;
        // System.out.println(num2); // 报错：非法的前向引用
    }

    private static int num2 = 10; // linking之prepare : num2=0 --> initial: 20 --> 10

    public static void main(String[] args) {
        System.out.println(num1);  // 2
        System.out.println(num2);  // 10
     }
}
