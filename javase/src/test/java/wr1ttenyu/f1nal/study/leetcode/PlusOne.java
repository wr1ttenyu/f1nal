package wr1ttenyu.f1nal.study.leetcode;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return digits;

        if (digits[0] == 0) {
            digits[digits.length - 1] = 1;
            return digits;
        }
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            return digits;
        }

        digits[digits.length - 1] = 0;
        for (int i = digits.length - 2; i > 0; i--) {
            if(digits[i] != 9) {
                digits[i] = digits[i] + 1;
                return digits;
            } else {
                if(i == 0) {
                    int[] newDigits = new int[digits.length + 1];
                    newDigits[0] = 1;
                    return newDigits;
                } else {
                    digits[i] = 0;
                    continue;
                }
            }
        }

        return digits;
    }
}
