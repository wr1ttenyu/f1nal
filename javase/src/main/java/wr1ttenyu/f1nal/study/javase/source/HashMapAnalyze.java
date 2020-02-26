package wr1ttenyu.f1nal.study.javase.source;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap 基本介绍：
 *      HashMap 是一个使用非常频繁的容器类，它允许键值都放入 null 元素。除该类方法未实现同步外，
 *      其余跟 Hashtable 大致相同，但跟 TreeMap不同，该容器不保证元素顺序，根据需要该容器可能会对元素重新哈希，
 *      元素的顺序也会被重新打散，因此不同时间迭代同一个 HashMap 的顺序可能会不同。
 *
 *
 *
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
 * 1.数据结构
 *   jdk 1.7 数组 + 单向链表
 *   jdk 1.8 数组 + 单向链表 + 红黑树 (当链表长度超过 8 的时候，就将链表变成红黑树)
 *   jdk 1.8 HashMap 链表 Node 结构 {@link java.util.HashMap.Node}
 *
 *
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

/**
 * 知识点
 * 1. 通过 K 获取数组下标
 *     Node<K,V>[] tab; Node<K,V> p; int n, i;
 *       if ((tab = table) == null || (n = tab.length) == 0)
 *           n = (tab = resize()).length;
 *       // tab[i = (n - 1) & hash] 为计算 node 在数组 中的下标值
 *       // n = tab.length 而 tab 的长度一般不超过 2的16次方 甚至更小 也就是说 hash 的低16位 才能参与到 与 n-1 的 & 运算中
 *       // 如何让 hash 的高16位 也参与运算呢  因为hash int 是32 的
 *       // 通过 (h = key.hashCode()) ^ (h >>> 16) 这个方式 就能让 高16位 参与运算了
 *       // h >>> 16 让高16位 与 低16位 进行^ 为什么不用 & 或者 | 进行运算 从概率上将 & 或 | 的结果都更偏向 0 或者 1
 *       // 从而让 hash 的高16位 也参与到了最后 tab[i = (n - 1) & hash] 数组下标的计算中 会让得到的下标更加散列
 *       if ((p = tab[i = (n - 1) & hash]) == null)
 *           tab[i] = newNode(hash, key, value, null);
 *
 *    {@link java.util.HashMap#hash(java.lang.Object)}
 *    static final int hash(Object key) {
 *       int h;
 *       return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
 *    }
 *
 * 2. put 方法的详细执行
 *  final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
 *                    boolean evict) {
 *  	Node<K,V>[] tab; Node<K,V> p; int n, i;
 *      // tab初始化
 * 		if ((tab = table) == null || (n = tab.length) == 0)
 * 	        // n值 的逻辑是 初始化默认 16 否则就是指定初始容量最近的2的n次方
 * 			n = (tab = resize()).length;
 * 		// 非初始化 计算节点下标 如果节点为空 直接新建Node
 * 		if ((p = tab[i = (n - 1) & hash]) == null)
 * 			tab[i] = newNode(hash, key, value, null);
 * 		else {
 * 			Node<K,V> e; K k;
 * 		    // 第一个就是 相同节点
 * 			if (p.hash == hash &&
 * 				((k = p.key) == key || (key != null && key.equals(k))))
 * 				e = p;
 * 			else if (p instanceof TreeNode)
 * 		        // 红黑树
 * 				e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
 * 			else {
 * 		        // 遍历后续节点
 * 				for (int binCount = 0; ; ++binCount) {
 * 					if ((e = p.next) == null) {
 * 						p.next = newNode(hash, key, value, null);
 * 					    // 转红黑树
 * 						if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
 * 							treeifyBin(tab, hash);
 * 						break;
 *                                        }
 * 					if (e.hash == hash &&
 * 						((k = e.key) == key || (key != null && key.equals(k))))
 * 						break;
 * 					p = e;                *            }
 * 			}
 * 			if (e != null) { // existing mapping for key
 * 				V oldValue = e.value;
 * 				if (!onlyIfAbsent || oldValue == null)
 * 					e.value = value;
 * 				afterNodeAccess(e);
 * 				return oldValue;
 *        }
 * 		}
 * 		++modCount;
 * 	    // 判断size是否超出最大容量
 * 		if (++size > threshold)
 * 			resize();
 * 		afterNodeInsertion(evict);
 * 		return null;
 * 	}
 *
 * 3. resize 扩容过程
 * 	final Node<K,V>[] resize() {
 * 		Node<K,V>[] oldTab = table;
 * 		int oldCap = (oldTab == null) ? 0 : oldTab.length;
 * 		int oldThr = threshold;
 * 		int newCap, newThr = 0;
 * 		if (oldCap > 0) {
 * 			if (oldCap >= MAXIMUM_CAPACITY) {
 * 				threshold = Integer.MAX_VALUE;
 * 				return oldTab;
 *                        }
 * 			else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
 * 					 oldCap >= DEFAULT_INITIAL_CAPACITY)
 * 				newThr = oldThr << 1; // double threshold        * 		}
 * 		else if (oldThr > 0) // initial capacity was placed in threshold
 * 	        // ①第一次初始化 设置 threshold 为 32  则 newCap 为 32
 * 			newCap = oldThr;
 * 		else {               // zero initial threshold signifies using defaults
 * 			newCap = DEFAULT_INITIAL_CAPACITY;
 * 			newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
 * 		}
 * 		if (newThr == 0) {
 * 	        // 按照①的情况 则ft 为24	newThr 为24
 * 			float ft = (float)newCap * loadFactor;
 * 			newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
 * 					  (int)ft : Integer.MAX_VALUE);
 * 		}
 * 		threshold = newThr;
 * 		@SuppressWarnings({"rawtypes","unchecked"})
 * 	    // 按照①的情况 则newTab 长度为32
 * 		Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
 * 		table = newTab;
 * 		if (oldTab != null) {
 * 			for (int j = 0; j < oldCap; ++j) {
 * 				Node<K,V> e;
 * 				if ((e = oldTab[j]) != null) {
 * 					oldTab[j] = null;
 * 					if (e.next == null)
 * 						newTab[e.hash & (newCap - 1)] = e;
 * 					else if (e instanceof TreeNode)
 * 						((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
 * 					else { // preserve order
 * 						Node<K,V> loHead = null, loTail = null;
 * 						Node<K,V> hiHead = null, hiTail = null;
 * 						Node<K,V> next;
 * 						do {
 * 							next = e.next;
 * 							if ((e.hash & oldCap) == 0) {
 * 								if (loTail == null)
 * 									loHead = e;
 * 								else
 * 									loTail.next = e;
 * 								loTail = e;
 * 							}
 * 							else {
 * 								if (hiTail == null)
 * 									hiHead = e;
 * 								else
 * 									hiTail.next = e;
 * 								hiTail = e;
 * 							}
 * 						} while ((e = next) != null);
 * 						if (loTail != null) {
 * 							loTail.next = null;
 * 							newTab[j] = loHead;
 * 						}
 * 						if (hiTail != null) {
 * 							hiTail.next = null;
 * 							newTab[j + oldCap] = hiHead;
 * 						}
 * 					}
 * 				}
 * 			}
 * 		}
 * 		return newTab;
 * 	}
 *
 *
 */
public class HashMapAnalyze {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        // TODO HashMap 源码学习 https://blog.csdn.net/USTC_Zn/article/details/78173217
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        /**
         * SOLVE modCount 源码注释表明 modCount是记录涉及修改hashMap的键值对数量或者改变内部结构(理解为改变内部映射即可)
         *      当有操作涉及遍历 HashMap 的 Node 时，如果 modCount 的值在遍历后，不符合预期值，则抛出 {@link ConcurrentModificationException}
         */

        // TODO CONTINUE 红黑树
        // https://mp.weixin.qq.com/s/SiHedmstpeA8BwCyCW9m7w
        HashMap<String, String> map1 = new HashMap<>(32);
        map1.put("123","123");
        for (String s : map1.keySet()) {
            map1.remove(s);
        }

       /* HashMap<String, String> map1 = new HashMap<>(32);
        for (int i = 0; i < 26; i++) {
            String key = String.valueOf(i);
            map1.put(key, key);
        }*/
    }

    // SOLVE 看看这个算法怎么弄的
    // 对于高位为1的  右移几位  再取| 相当于 高位右移位数*2的位置全变变为1
    // 比如 10010 右移1位 则 变为 11001
    // 从1开始 最终到16 int 32位 全部位置变为 1 或者 最高位为0 其他为全部为 1
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }





}
