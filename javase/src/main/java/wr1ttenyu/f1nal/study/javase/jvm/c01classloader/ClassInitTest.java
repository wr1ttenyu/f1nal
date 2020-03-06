package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

import org.omg.PortableInterceptor.INACTIVE;

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
        System.out.println("--------------------------");
        Son son = new Son();
        System.out.println(son.j);
     }
}

class Father {

    static {
        System.out.println(1);
    }

    private static Integer i = fatherStatic();

    {
        System.out.println(5);
    }

    public Integer j = notStatic();

    Father() {
        System.out.println(7);
    }

    public static int fatherStatic() {
        System.out.println(2);
        return 100;
    }

    public int notStatic() {
        System.out.println(6);
        notStatic2();
        return 100;
    }

    public int notStatic2() {
        System.out.println(11);
        return 100;
    }
}

class Son extends Father {
    private static Integer i = sonStatic();

    static {
        System.out.println(4);
    }

    public Integer j = notStatic();

    {
        System.out.println(9);
    }

    Son() {
        // super(); 写或不写都存在
        System.out.println(10);
    }

    public static int sonStatic() {
        System.out.println(3);
        return 100;
    }

    public int notStatic() {
        super.notStatic();
        System.out.println(8);
        return 100;
    }
}