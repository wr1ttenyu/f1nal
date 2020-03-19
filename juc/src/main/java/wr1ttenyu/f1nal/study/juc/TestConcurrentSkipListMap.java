package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.ConcurrentSkipListMap;

public class TestConcurrentSkipListMap {

    public static void main(String[] args) {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        concurrentSkipListMap.put(1, 1);
        concurrentSkipListMap.put(2, 2);
        concurrentSkipListMap.put(3, 3);
        concurrentSkipListMap.put(9, 9);
        concurrentSkipListMap.get(9);
        System.out.println(concurrentSkipListMap);
    }
}
