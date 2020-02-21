package wr1ttenyu.f1nal.study.javase.jvm.c03Stack;

public class DynamicLinkingTest {

    int num =10;

    public void methodA() {
        System.out.println("methodA() .....");
    }

    public void methodB() {
        System.out.println("methodB() .....");

        methodA();

        num++;
    }
}
