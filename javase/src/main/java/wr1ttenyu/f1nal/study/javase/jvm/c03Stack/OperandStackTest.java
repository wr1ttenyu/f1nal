package wr1ttenyu.f1nal.study.javase.jvm.c03Stack;

public class OperandStackTest {

    public static void main(String[] args) {
        int i = 15;
        int j = 8;
        int k = i + j;

        int[][] intarry= {{1,2,3}, {1,2,3}};
        System.out.println(intarry.length);

    }

    public int getSum() {
        int m = 20;
        int n = 10;
        int k = m + n;
        return k;
    }

    public void testGetSum() {
        int i = getSum();
        int j = 10;
    }
}