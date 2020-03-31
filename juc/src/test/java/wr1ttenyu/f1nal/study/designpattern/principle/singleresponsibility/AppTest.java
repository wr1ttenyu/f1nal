package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import org.junit.jupiter.api.Test;
import wr1ttenyu.f1nal.study.juc.MyLock;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    // TODO MyLock 源码调试
    private MyLock myLock = new MyLock();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testMyLock1() {
        myLock = new MyLock();
    }

    @Test
    public void testMyLock2() {
        assertTrue(true);
    }

    @Test
    public void testBlockQueue() {

        Thread thread = new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("收到中断了");
                    System.out.println("在获取一次中断状态：" + Thread.currentThread().isInterrupted());
                    return;
                } else {
                    System.out.println("没有中断");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始中断");
        thread.interrupt();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Stack<Integer> s = new Stack<>();

        System.out.println(s.empty());
        s.peek();


        s.pop();
    }

    class Solution {
        public void reverseString(char[] s) {
            if (s.length == 0 || s.length == 1) {
                return;
            }

            int loopNum;
            if(s.length%2 == 0) {
                loopNum = s.length/2;
            }else {
                loopNum = (s.length-1)/2;
            }

            for (int i = 0; i < loopNum; i++) {
                char temp = s[i];
                s[i] = s[s.length - i];
                s[s.length - i] = temp;
            }
        }
    }
}
