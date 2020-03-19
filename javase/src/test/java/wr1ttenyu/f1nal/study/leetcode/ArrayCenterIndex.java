package wr1ttenyu.f1nal.study.leetcode;

public class ArrayCenterIndex {

    public static void main(String[] args) {
        ArrayCenterIndex arrayCenterIndex = new ArrayCenterIndex();
        int[] test = {-1, -1, -1, -1, -1, -1};

        System.out.println(arrayCenterIndex.pivotIndex(test));
    }

    public int pivotIndex(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;

        int[] numRightSum = new int[nums.length];

        numRightSum[nums.length - 1] = 0;
        numRightSum[nums.length - 2] = nums[nums.length - 1];

        for (int i = nums.length - 3; i >= 0; i--) {
            numRightSum[i] = numRightSum[i + 1] + nums[i + 1];
        }

        int preSum = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0) {
                curSum = preSum + nums[i - 1];
            } else {
                curSum = 0;
            }
            preSum = curSum;
            if(curSum == numRightSum[i]) return i;

        }

        return -1;
    }

}
