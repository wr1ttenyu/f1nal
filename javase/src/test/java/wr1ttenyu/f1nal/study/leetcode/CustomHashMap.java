package wr1ttenyu.f1nal.study.leetcode;

public class CustomHashMap {
    /**
     * 所有的值都在 [1, 1000000]的范围内。
     * 操作的总数目在[1, 10000]范围内。
     * 不要使用内建的哈希库。
     */
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
       /* for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 10000; i++) {
                int i1 = new Random(3).nextInt();
                if (i1 % 3 == 0) {
                    myHashMap.put(new Random(1000000).nextInt(), new Random(1000000).nextInt());
                } else if (i1 % 3 == 1) {
                    myHashMap.get(new Random(1000000).nextInt());
                } else {
                    myHashMap.remove(new Random(1000000).nextInt());
                }
            }
        }*/

        myHashMap.put(1, 123);
        myHashMap.put(1001, 123);
        myHashMap.put(2001, 123);
        myHashMap.put(3001, 123);
        myHashMap.remove(1);
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(1001));

    }

}

class MyHashMap {

    private MyHashMapNode[] arr = new MyHashMapNode[1000];

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {

    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        MyHashMapNode node = arr[hash(key)];
        boolean hasSameKeyNode = false;
        if (node == null) {
            node = new MyHashMapNode(key, value, null);
            arr[hash(key)] = node;
        } else {
            MyHashMapNode nodeNew = new MyHashMapNode(key, value, null);

            MyHashMapNode preNode = null;
            while (node != null) {
                if (node.getKey() == key) {
                    node.setValue(value);
                    hasSameKeyNode = true;
                    break;
                }
                preNode = node;
                node = node.getNext();
            }

            if (!hasSameKeyNode) preNode.setNext(nodeNew);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        MyHashMapNode node = arr[hash(key)];
        if (node == null) {
            return -1;
        } else {
            if (node.getKey() == key) {
                return node.getValue();
            }

            while (node.getNext() != null) {
                node = node.getNext();
                if (node.getKey() == key) {
                    return node.getValue();
                }
            }

            return -1;
        }
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        MyHashMapNode nodePre;
        MyHashMapNode node = arr[hash(key)];
        if (node == null) {
            return;
        } else {
            if (node.getKey() == key) {
                arr[hash(key)] = node.getNext();
            }

            while (node.getNext() != null) {
                nodePre = node;
                node = node.getNext();
                if (node.getKey() == key) {
                    nodePre.setNext(node.getNext());
                }
            }
        }
    }

    private int hash(int key) {
        return key % 1000;
    }
}

class MyHashMapNode {
    private int key;

    private int value;

    private MyHashMapNode next;

    public MyHashMapNode(int key, int value, MyHashMapNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MyHashMapNode getNext() {
        return next;
    }

    public void setNext(MyHashMapNode next) {
        this.next = next;
    }
}
