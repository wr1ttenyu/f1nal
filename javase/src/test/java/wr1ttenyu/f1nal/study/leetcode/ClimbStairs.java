package wr1ttenyu.f1nal.study.leetcode;

public class ClimbStairs {

    public static void main(String[] args) {
        int i = climbStairs(5);
        System.out.println(i);
    }

    private static int climbStairs1(int n) {
        if(n <= 2) return n;
        return climbStairs1(n-1) + climbStairs1(n-2);
    }

    public static int climbStairs(int n) {
        if(n <= 2) return n;

        int prevFirst = 2;
        int prevSecond = 1;
        int sum = 0;
        for(int i = 3; i < n + 1; i++) {
            sum = prevFirst + prevSecond;
            prevSecond = prevFirst;
            prevFirst = sum;
        }
        return sum;
    }
}
