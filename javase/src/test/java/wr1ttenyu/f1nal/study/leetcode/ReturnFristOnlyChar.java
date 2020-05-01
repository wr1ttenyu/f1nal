package wr1ttenyu.f1nal.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReturnFristOnlyChar {

    public static void main(String[] args) {
        ReturnFristOnlyChar test = new ReturnFristOnlyChar();
        System.out.println(test.firstUniqChar("loveleetcode"));
    }

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        char[] arr = s.toCharArray();
        quickSort(arr, 0, arr.length - 1);
        Character cur = null;
        char temp = arr[0];
        List<Character> onlyChar = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] != temp && i == 1)
                    || (arr[i] != temp && temp != cur)) onlyChar.add(temp);
            if(i == arr.length - 1 && arr[i] != temp) onlyChar.add(arr[i]);
            cur = temp;
            temp = arr[i];

        }
        for (char c : s.toCharArray()) {
            if(onlyChar.indexOf(c) >= 0) return c;
        }
        return ' ';
    }

    private void quickSort(char[] arr, int left, int right) {
        if (left >= right) return;
        int target = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (j > i && arr[j] >= target) {
                j--;
            }

            while (i < j && arr[i] <= target) {
                i++;
            }

            if (i < j) swap(arr, i, j);
        }

        char temp = arr[left];
        arr[left] = arr[i];
        arr[i] = temp;


        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
