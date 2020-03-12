package wr1ttenyu.f1nal.study.leetcode;

public class SearchSpinOrderArray {

    public int search(int[] nums, int target) {

        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (rightIndex >= leftIndex) {
            int half = (rightIndex + leftIndex) / 2;
            if (nums[half] == target) return half;
            if (nums[half] > target) {
                if (nums[leftIndex] > nums[rightIndex]) {
                    if (nums[rightIndex] < target && leftIndex != half) {
                        rightIndex = half - 1;
                    } else {
                        leftIndex = half + 1;
                    }
                } else {
                    rightIndex = half - 1;
                }
            } else {
                if (nums[leftIndex] > nums[rightIndex]) {
                    if (nums[leftIndex] >= nums[half] && leftIndex != half) {
                        rightIndex = half - 1;
                    } else {
                        leftIndex = half + 1;
                    }
                } else {
                    leftIndex = half + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 1};
        SearchSpinOrderArray searchSpinOrderArray = new SearchSpinOrderArray();
        System.out.println(searchSpinOrderArray.search(arr, 3));
    }
}
