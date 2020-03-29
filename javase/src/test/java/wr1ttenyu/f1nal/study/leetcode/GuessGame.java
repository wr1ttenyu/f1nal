package wr1ttenyu.f1nal.study.leetcode;

public class GuessGame {

    public static void main(String[] args) {
        GuessGame guessGame= new GuessGame();
        guessGame.guessNumber(2126753390);
    }

    public int guessNumber(int n) {
        long left = 0;
        long right = n;
        Integer res = null;
        while (left <= right) {
            long half = (left + right) / 2;
            if (guess((int)half) < 0) {
                right = half - 1;
                continue;
            }
            if (guess((int)half) > 0) {
                left = half + 1;
                continue;
            }
            if (guess((int)half) == 0) {
                res = (int)half;
                break;
            }
        }
        return res;
    }

    private int guess(int num) {
        if (num > 1702766719) return -1;
        if (num < 1702766719) return 1;
        return 0;
    }

}
