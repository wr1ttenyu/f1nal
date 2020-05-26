package wr1ttenyu.f1nal.study.leetcode;

import java.util.Arrays;

public class QuickSort2 {

    public static void main(String[] args) {
        QuickSort2 quickSort = new QuickSort2();
        int[] test = {3,7,1,3,3,34,5,67,23,243};
        quickSort.quickSort(test, 0, test.length - 1);
        System.out.println(Arrays.toString(test));
    }

    private void quickSort(int[] nums, int start, int end) {
        if(end < start) return;

        int left = start;
        int right = end;
        int target = nums[left];

        while(left < right) {
            while(nums[right] >= target && left < right) {
                right--;
            }

            while(nums[left] <= target && left < right) {
                left++;
            }

            swap(nums, left, right);
        }

        swap(nums, start, left);
        quickSort(nums, start, right - 1);
        quickSort(nums, right + 1, end);
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
