package wr1ttenyu.f1nal.study.leetcode;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

    public static void main(String[] args) {
        VariableClass variableClass = new VariableClass();

        new Thread(() -> {
            try {
                System.out.println("variableClass 值为：" + variableClass.test);
                TimeUnit.SECONDS.sleep(4);
                variableClass.test = 50;
                System.out.println("variableClass 值为：" + variableClass.test);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (variableClass.test == 456) {
           System.out.println(1);
        }

        System.out.println("mission is over");
    }

}

class VariableClass {

    Integer test = new Integer(456);

}