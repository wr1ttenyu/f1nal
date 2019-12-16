package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap 基本介绍：
 *      HashMap 是一个使用非常频繁的容器类，它允许键值都放入 null 元素。除该类方法未实现同步外，
 *      其余跟 Hashtable 大致相同，但跟 TreeMap不同，该容器不保证元素顺序，根据需要该容器可能会对元素重新哈希，
 *      元素的顺序也会被重新打散，因此不同时间迭代同一个 HashMap 的顺序可能会不同。
 *
 *      1.数据结构
 *          jdk 1.7 数组 + 单向链表
 *          jdk 1.8 数组 + 单向链表 + 红黑树
 *      2.关键属性
 */
public class HashMapAnalyze {

    public static void main(String[] args) {
        // TODO HashMap 源码学习 https://blog.csdn.net/USTC_Zn/article/details/78173217
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        HashMap<String, String> map1 = new HashMap<>();

        map1.put("2344", "453");
        map1.put("34", "123453");
        map1.put("123", "123");

        Set<Map.Entry<String, String>> entries = map1.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("key=" + entry.getKey() + ";value=" + entry.getValue());
        }


    }
}
