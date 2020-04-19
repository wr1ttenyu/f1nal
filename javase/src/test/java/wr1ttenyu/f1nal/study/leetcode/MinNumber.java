package wr1ttenyu.f1nal.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class MinNumber {

    public static void main(String[] args) {
        MinNumber minNumber = new MinNumber();
        int[] intArr = new int[10];
        intArr[0] = 3;
        intArr[1] = 43;
        intArr[2] = 48;
        intArr[3] = 94;
        intArr[4] = 85;
        intArr[5] = 33;
        intArr[6] = 64;
        intArr[7] = 32;
        intArr[8] = 63;
        intArr[9] = 66;
        System.out.println(minNumber.minNumber(intArr));
    }

    public String minNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sort(nums);
        int cur;
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private void sort(int[] nums) {
        int temp;
        for (int i = 1; i < nums.length; i++) {
            int cur = i;
            for (int j = i - 1; j >= 0; j--) {
                if (compareWhoMin(nums[cur], nums[j])) {
                    temp = nums[j];
                    nums[j] = nums[cur];
                    nums[cur] = temp;
                    cur = j;
                } else {
                    break;
                }
            }
        }
    }

    // 3 30 34  5 9
    private boolean compareWhoMin(int m, int n) {
        if (m < 10 && n < 10) return m < n;
        if (m == n) return false;

        int tempm = m;
        int tempn = n;
        ArrayList<Integer> mpre = new ArrayList();
        ArrayList<Integer> npre = new ArrayList();

        while (tempm >= 10) {
            mpre.add(tempm % 10);
            tempm = (tempm - tempm % 10) / 10;
        }
        mpre.add(tempm);

        while (tempn >= 10) {
            npre.add(tempn % 10);
            tempn = (tempn - tempn % 10) / 10;
        }
        npre.add(tempn);

        boolean mLoopDone = false;
        boolean nLoopDone = false;
        for (int i = mpre.size() - 1, j = npre.size() - 1; ; i--, j--) {
            if (mLoopDone && nLoopDone) return false;
            if (i < 0) {
                i = mpre.size() - 1;
                mLoopDone = true;
            }
            if (j < 0) {
                j = npre.size() - 1;
                nLoopDone = true;
            }
            if (mpre.get(i) > npre.get(j)) return false;
            if (mpre.get(i) < npre.get(j)) return true;
        }
    }
}
