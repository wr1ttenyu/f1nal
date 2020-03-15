package wr1ttenyu.f1nal.study.leetcode;

public class SearchSpinOrderArray {

    public int search(int[] nums, int target) {

        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (rightIndex >= leftIndex) {
            int halfIndex = (rightIndex + leftIndex) / 2;
            int halfValue = nums[halfIndex];
            int leftValue = nums[leftIndex];
            int rightValue = nums[rightIndex];

            if (halfValue == target) return halfIndex;

            if (halfValue > target) {
                // 递增 + 递减序列
                if (leftValue > rightValue) {
                    // 递增序列
                    if (halfValue >= leftValue) {
                        if (leftValue > target) {
                            leftIndex = halfIndex + 1;
                        } else {
                            rightIndex = halfIndex - 1;
                        }
                    } else {
                        // 递增 + 递减序列
                        rightIndex = halfIndex - 1;
                    }
                } else {
                    // 递增序列
                    rightIndex = halfIndex - 1;
                }
            } else {
                // halfValue < target
                // 递增 + 递减序列
                if (leftValue > rightValue) {
                    // 递增序列
                    if (halfValue >= leftValue) {
                        leftIndex = halfIndex + 1;
                    } else {
                        // 递增 + 递减序列
                        if (leftValue > target) {
                            leftIndex = halfIndex + 1;
                        } else {
                            rightIndex = halfIndex - 1;
                        }
                    }
                } else {
                    // 递增序列
                    leftIndex = halfIndex + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 3};
        SearchSpinOrderArray searchSpinOrderArray = new SearchSpinOrderArray();
        System.out.println(searchSpinOrderArray.search(arr, 3));
    }
}
