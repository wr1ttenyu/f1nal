package wr1ttenyu.f1nal.study.leetcode;

public class DominantIndex {

    public static void main(String[] args) {

    }

    public int dominantIndex(int[] nums) {
        int preMax = -1;
        int curMax = -1;
        int curMaxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > curMax) {
                preMax = curMax;
                curMax = nums[i];
                curMaxIndex = i;
            } else if(nums[i] > preMax) preMax = nums[i];
        }
        if(preMax * 2 <= curMax) return curMaxIndex;
        return -1;
    }
}
