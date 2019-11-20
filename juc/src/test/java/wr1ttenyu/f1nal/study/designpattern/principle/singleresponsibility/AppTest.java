package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import org.junit.jupiter.api.Test;
import wr1ttenyu.f1nal.study.juc.MyLock;

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
}
