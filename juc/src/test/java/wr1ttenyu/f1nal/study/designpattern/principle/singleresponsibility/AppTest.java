package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import org.junit.jupiter.api.Test;
import wr1ttenyu.f1nal.study.juc.MyLock;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
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
