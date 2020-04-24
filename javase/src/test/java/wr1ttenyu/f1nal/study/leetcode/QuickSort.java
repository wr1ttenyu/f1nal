package wr1ttenyu.f1nal.study.leetcode;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] test = new int[]{1,1};
        int[] ints = quickSort.quickSort(test);
        System.out.println(Arrays.toString(ints));
    }


    private int[] quickSort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        compareAndSwap(nums, 0, nums.length - 1);
        return nums;
    }

    private void compareAndSwap(int[] nums, int leftIndex, int rightIndex) {
        boolean isLeft = true;
        if (rightIndex < leftIndex) {
            return;
        }
        int low = leftIndex;
        int high = rightIndex;
        int target = nums[leftIndex];
        while (low < high) {
            while(nums[high] >= target && low < high) {
                high--;
            }

            while(nums[low] <= target && low < high) {
                low++;
            }
            swap(nums, low, high);
        }
        compareAndSwap(nums, leftIndex, high - 1);
        compareAndSwap(nums, high + 1, rightIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
