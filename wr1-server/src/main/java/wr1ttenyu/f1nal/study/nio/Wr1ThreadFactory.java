package wr1ttenyu.f1nal.study.nio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class Wr1ThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "wr1-server worker num:" + threadNum.getAndIncrement());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSum(new int[]{1, 1, -2}));
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums.length == 0) return result;

        Arrays.sort(nums);

        if (nums[nums.length - 1] != 0 && nums[0] / nums[nums.length - 1] > 0)
            return new ArrayList<>();
        for (int i = 0, j = nums.length - 1; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (Math.abs(nums[i]) > nums[j] * 2) {
                continue;
            }
            if (nums[i] > 0) break;

            int left = i + 1;
            int right = j;
            while (left < right) {
                if (left - i > 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                int sum = nums[left] + nums[right];
                if (sum == -nums[i]) {
                    addItem(result, nums[i], nums[left], nums[right]);
                    left++;
                    right--;
                    continue;
                }
                if (sum > -nums[i]) {
                    right--;
                    continue;
                }
                if (sum < -nums[i]) {
                    left++;
                    continue;
                }
            }

        }
        return result;
    }

    public void addItem(List<List<Integer>> result, int num0, int num1, int num2) {
        List<Integer> item = new ArrayList<>(3);
        item.add(num0);
        item.add(num1);
        item.add(num2);
        result.add(item);
        return;
    }
}