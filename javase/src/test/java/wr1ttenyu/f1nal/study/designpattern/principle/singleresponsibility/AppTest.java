package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testStringIntern() {
        int MAX = 1000 * 10000;
        String[] arr = new String[MAX];

        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = String.valueOf(DB_DATA[i % DB_DATA.length]).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }

    @Test
    public void testValuePass() {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();

        change(i, str, num, arr, my);

        System.out.println("i = " + i); // 1
        System.out.println("str = " + str); // hello
        System.out.println("num = " + num); // 200
        System.out.println("arr = " + Arrays.toString(arr)); // 2 2 3 4 5
        System.out.println("my.a = " + my.a); // 11
    }

    public void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }

    class MyData {
        int a = 10;
    }
}

class Exam5 {
    static int s;//成员变量，类变量
    int i;//成员变量，实例变量
    int j;//成员变量，实例变量
    {
        int i = 1;//非静态代码块中的局部变量 i
        i++;
        j++;
        s++;
    }
    public void test(int j){//形参，局部变量,j
        j++;
        i++;
        s++;
    }
    public static void main(String[] args) {//形参，局部变量，args
        Exam5 obj1 = new Exam5();//局部变量，obj1
        Exam5 obj2 = new Exam5();//局部变量，obj1
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s); //2  1  5
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s); // 1  1   5
    }
}


