package wr1ttenyu.f1nal.study.leetcode;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(isHappy(28));
    }

    private static Set<Integer> set = new HashSet();

    public static boolean isHappy(int n) {
        int temp = n;

        int result = 0;
        while (n >= 10) {
            int cur = n % 10;
            result += cur * cur;
            n = (n - cur) / 10;
        }
        result = result + n * n;
        if (result == 1) return true;
        if(set.contains(result)) return false;
        set.add(result);
        return isHappy(result);
    }
}
