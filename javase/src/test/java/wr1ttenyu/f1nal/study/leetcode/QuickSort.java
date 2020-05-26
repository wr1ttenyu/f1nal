package wr1ttenyu.f1nal.study.leetcode;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickSort {

    public static void main(String[] args) {
        /*QuickSort quickSort = new QuickSort();
        int[] test = {3,7,1,3,3,34,5,67,23,243};
        int[] ints = quickSort.quickSort(test);
        System.out.println(Arrays.toString(ints));*/

        new Thread(() -> {
            System.out.println("123");
        });

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        executorService.execute(() -> System.out.println(123));

        executorService.submit(() -> {
            System.out.println(123);
            return 1;
        });
    }




    private int[] quickSort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        compareAndSwap(nums, 0, nums.length - 1);
        return nums;
    }

    private void compareAndSwap(int[] nums, int leftIndex, int rightIndex) {
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

        int temp = nums[leftIndex];
        nums[leftIndex] = nums[low];
        nums[low] = temp;

        compareAndSwap(nums, leftIndex, high - 1);
        compareAndSwap(nums, high + 1, rightIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class MyThread extends Thread {


    public void run() {
        super.run();
    }
}
