package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import org.junit.jupiter.api.Test;
import wr1ttenyu.f1nal.study.juc.MyLock;

import java.util.Stack;

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

    public static void main(String[] args) {

        Stack<Integer> s = new Stack<>();

        System.out.println(s.empty());
        s.peek();


        s.pop();
    }

    class MinStack {

        private Integer minElement;
        private Stack<Integer> s;

        /** initialize your data structure here. */
        public MinStack() {
            s = new Stack<>();
        }

        public void push(int x) {
            if(minElement == null || x < minElement) {
                minElement = x;
            }
            s.push(x);
        }

        public void pop() {
            s.pop();
            minElement = s.stream().min((x, y) -> {
                if (x > y) return 1;
                else return -1;
            }).get();
        }

        public int top() {
            return s.peek();
        }

        public int getMin() {
            return minElement;
        }
    }
}
