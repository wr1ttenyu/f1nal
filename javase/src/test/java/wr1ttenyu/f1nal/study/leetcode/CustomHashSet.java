package wr1ttenyu.f1nal.study.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CustomHashSet {

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */
    public static void main(String[] args) {
        MyHashSet set =new MyHashSet();
        set.add(1);
        set.add(2);
        set.contains(1);
        set.contains(3);
        set.add(2);
        set.contains(2);
        set.remove(2);
        set.contains(2);
    }
}

/**
 * 注意：
 * <p>
 * 所有的值都在 [0, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 */
class MyHashSet {

    private List<Integer>[] container = new ArrayList[10000];

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {

    }

    public void add(int key) {
        int index = hashKey(key);
        List<Integer> values = container[index];
        if (values != null) {
            for (Integer value : values) {
                if (value == key) return;
            }
            values.add(key);
        } else {
            values = new ArrayList();
            values.add(key);
            container[index] = values;
        }

    }

    public void remove(int key) {
        int index = hashKey(key);
        List<Integer> values = container[index];
        if (values == null) return;
        else {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) == key) {
                    values.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = hashKey(key);
        List<Integer> values = container[index];
        if (values == null) return false;
        else {
            for (Integer value : values) {
                if (value == key) return true;
            }
        }
        return false;
    }

    private int hashKey(Integer value) {
        return value % 10000;
    }
}
