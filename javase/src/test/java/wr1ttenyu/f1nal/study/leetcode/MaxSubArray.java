package wr1ttenyu.f1nal.study.leetcode;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        if(nums == null) return 0;
        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = curSum + nums[i] > nums[i] ? curSum + nums[i]: nums[i];
            maxSum = maxSum > curSum ? maxSum : curSum;
        }

        return maxSum;
    }
}
