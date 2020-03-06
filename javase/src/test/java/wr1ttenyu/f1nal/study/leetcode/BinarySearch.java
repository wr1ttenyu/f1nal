package wr1ttenyu.f1nal.study.leetcode;

public class BinarySearch {

    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int targetindex = -1;
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int halfIndex = (right - left) / 2 + left;
            if (nums[halfIndex] > target) {
                right = halfIndex;
                continue;
            }
            if (nums[halfIndex] < target) {
                left = halfIndex;
                continue;
            }

            targetindex = halfIndex;
            break;
        }

        return targetindex;
    }
}
