package wr1ttenyu.f1nal.study.leetcode;

import java.util.HashSet;

public class ArrayIntersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) result.add(nums2[i]);
        }
        int[] arr = new int[result.size()];
        int i = 0;
        for (Integer integer : result) {
            arr[i] = integer;
            i++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums1 = {2038};
        int[] nums2 = {1};
        System.out.println(intersection2(nums1, nums2));
    }

    public static int[] intersection2(int[] nums1, int[] nums2) {
        // 作为临时的交集
        int[] container = nums1.length > nums2.length ?
                new int[nums1.length] : new int[nums2.length];

        boolean[] appeared = new boolean[2020];
        for (int i = 0; i < nums1.length; i++) {
            appeared[nums1[i]] = true;      // 标记nums1的各个元素为出现过
        }

        int len = 0;                          // 定位指针
        for (int i = 0; i < nums2.length; i++) {
            if (appeared[nums2[i]] == true) {
                container[len++] = nums2[i];
                appeared[nums2[i]] = false; //标记其状态为使用过
            }
        }

        int[] resArr = new int[len];          //len-0 == 为交集的元素个数
        for (int i = 0; i < len; i++) {
            resArr[i] = container[i];
        }
        return resArr;

    }
}
