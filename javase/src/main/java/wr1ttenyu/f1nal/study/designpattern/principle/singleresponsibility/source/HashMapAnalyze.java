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
 *          threshold：表示容器所能容纳的 key-value 对数量极限 默认构造器下 size 在第一次被调用put时
 *              被初始化为 {@link HashMap#DEFAULT_INITIAL_CAPACITY} 16 * {@link HashMap#DEFAULT_LOAD_FACTOR} 0.75f = 12
 *          loadFactor：负载因子  默认初始化为 {@link HashMap#DEFAULT_LOAD_FACTOR} 0.75f
 *          modCount：记录修改次数
 *          size：表示实际存在的键值对数量
 *          table：一个哈希桶数组，键值对就存放在里面，键值对的存放形式为：{@link HashMap.Node}
 *          entrySet：存放所有的键值对的Set集合
 */

/**
 *  SOLVE 假如我们有1000  10000  100000 的三种数据量  我们应该如何初始化hashMap的大小
 *  根据 {@link HashMap#tableSizeFor(int)} 算法，在使用{@link HashMap#HashMap(int)} 指定初始容量时
 *  指定的参数值总是被调整成 大于且最接近该值的 2的n次方，接着在第一次调用 put 时,见下面代码：
 *  final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
 *                 boolean evict) {
 *            Node<K,V>[] tab; Node<K,V> p; int n, i;
 *            if ((tab = table) == null || (n = tab.length) == 0)
 *                // 第一次put  调用resize()
 *                n = (tab = resize()).length;
 *                      ...
 *     }
 *  在 resize 方法中，见下面:
 *  final Node<K,V>[] resize() {
 *         Node<K,V>[] oldTab = table;
 *         int oldCap = (oldTab == null) ? 0 : oldTab.length;
 *         int oldThr = threshold;
 *         int newCap, newThr = 0;
 *         if (oldCap > 0) {
 *             if (oldCap >= MAXIMUM_CAPACITY) {
 *                 threshold = Integer.MAX_VALUE;
 *                 return oldTab;
 *             }
 *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
 *                      oldCap >= DEFAULT_INITIAL_CAPACITY)
 *                 newThr = oldThr << 1; // double threshold
 *         }
 *         else if (oldThr > 0) // initial capacity was placed in threshold
 *             newCap = oldThr;
 *         else {               // zero initial threshold signifies using defaults
 *             newCap = DEFAULT_INITIAL_CAPACITY;
 *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
 *         }
 *         if (newThr == 0) {
 *             float ft = (float)newCap * loadFactor;
 *             // threshold 最终被调整成为 初始化容量 * 0.75
 *             // 所以当预期存储 1000 时，指定1000，最后的容量阈值就是  1024*0.75 < 1000
 *             // 但是当预期10000时，指定10000，最后的容量阈值就是  16384 * 0.75 > 10000
 *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
 *                       (int)ft : Integer.MAX_VALUE);
 *         }
 *         threshold = newThr;
 *         ...
 *   }
 */
public class HashMapAnalyze {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        // TODO HashMap 源码学习 https://blog.csdn.net/USTC_Zn/article/details/78173217
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        // TODO modCount 作用 以及 多线程时的  resize
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        System.out.println(111);
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>(10000);
        HashMap<String, String> map3 = new HashMap<>();

        map1.put("2344", "453");
        map1.put("34", "123453");
        map1.put("123", "123");

        Set<Map.Entry<String, String>> entries = map1.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("key=" + entry.getKey() + ";value=" + entry.getValue());
        }

        int j = 2<<20;
        int k = 2<<18;
        int i = tableSizeFor(j + k);
        System.out.println(i);
    }

    // TODO 看看这个算法怎么弄的
    static final int tableSizeFor(int cap) {
        // 对于
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }





}
