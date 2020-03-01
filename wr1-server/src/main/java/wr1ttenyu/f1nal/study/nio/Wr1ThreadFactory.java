package wr1ttenyu.f1nal.study.nio;

import org.omg.PortableInterceptor.INACTIVE;

import java.net.InetSocketAddress;
import java.util.*;
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
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean hasZero = false;
        Integer zeroIndex = null;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                hasZero = true;
                zeroIndex = i;
            }
        }

        if (nums[nums.length - 1] == 0 || nums[0] == 0 || nums[0] / nums[nums.length - 1] > 0)
            return new ArrayList<>();
        for (int i = 0, j = nums.length - 1; i < nums.length; i++) {
            if (nums[i] >= 0 || nums[j] <= 0) {
                break;
            }
            if (Math.abs(nums[i] * 2) < nums[j]) {
                j--;
                continue;
            } else if (Math.abs(nums[i]) > nums[j] * 2) {
                continue;
            }

            if (Math.abs(nums[i]) > nums[j]) {
                for (int k = j - 1; -nums[i] - nums[j] <= nums[k]; k--) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        addItem(result, nums[i], nums[j], nums[k]);
                        break;
                    }
                }
                continue;
            } else if (Math.abs(nums[i]) < nums[j]) {
                for (int k = i + 1; nums[j] + nums[i] <= -nums[k]; k++) {
                    if (nums[j] + nums[i] + nums[k] == 0) {
                        addItem(result, nums[i], nums[j], nums[k]);
                        break;
                    }
                }
                j--;
                i--;
            } else if (hasZero) {
                List<Integer> item = new ArrayList<>(3);
                item.add(nums[i]);
                item.add(nums[j]);
                item.add(nums[zeroIndex]);
                result.add(item);
                i--;
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