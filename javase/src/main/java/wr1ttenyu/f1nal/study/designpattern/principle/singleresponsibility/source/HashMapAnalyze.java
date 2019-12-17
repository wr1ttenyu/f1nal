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
 *          jdk 1.8 数组 + 单向链表 + 红黑树 (当链表长度超过 8 的时候，就将链表变成红黑树)
 *      2.关键属性
 *          threshold：表示容器所能容纳的 key-value 对极限 默认构造器下 size 在第一次被调用put时
 *              被初始化为 {@link HashMap#DEFAULT_INITIAL_CAPACITY} 16 * {@link HashMap#DEFAULT_LOAD_FACTOR} 0.75f = 12
 *          loadFactor：负载因子  默认初始化为 {@link HashMap#DEFAULT_LOAD_FACTOR} 0.75f
 *          modCount：记录修改次数
 *          size：表示实际存在的键值对数量
 *          table：一个哈希桶数组，键值对就存放在里面，键值对的存放形式为：{@link HashMap.Node}
 *          entrySet：存放所有的键值对的Set集合
 */
public class HashMapAnalyze {

    public static void main(String[] args) {
        // TODO HashMap 源码学习 https://blog.csdn.net/USTC_Zn/article/details/78173217
        // TODO 假如我们有1000  10000  100000 的三种数据量  我们应该如何初始化hashMap的大小
        // TODO modCount 作用 以及 多线程时的  resize
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        System.out.println(111);
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
