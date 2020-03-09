package wr1ttenyu.f1nal.study.leetcode;

public class XSquare {

    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        long halfValue;
        while (right > left) {
            halfValue = (right + left) / 2;
            if (halfValue * halfValue < 0 || halfValue * halfValue > x) {
                right = halfValue - 1;
                continue;
            }
            if (halfValue * halfValue < x) {
                left = halfValue + 1;
                continue;
            }
            return (int)halfValue;
        }
        return (int)(right * right > x ? right - 1 : right);
    }

    public static void main(String[] args) {
       /* XSquare xSquare = new XSquare();
        xSquare.mySqrt(2147395599);*/
        long res = 268424449 * 268424449;
        System.out.println(res);
    }
}
