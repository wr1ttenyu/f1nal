package wr1ttenyu.f1nal.study.javase.source;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Unit test for simple App.
 */
public class ConcurrentHashMapAnalyze {

    public void testForEach() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            concurrentHashMap.put(i, i);
        }

        concurrentHashMap.forEach((k, v) -> {
            concurrentHashMap.put(k, v + 1);
        });

        System.out.println(concurrentHashMap);
    }

    public void testEntry() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            i += 15;
            concurrentHashMap.put(i, i);
        }

        Set<Map.Entry<Integer, Integer>> entries = concurrentHashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            entry.setValue(1);
        }
        System.out.println(concurrentHashMap);
    }

    public static void main(String[] args) {
        ConcurrentHashMapAnalyze analyze = new ConcurrentHashMapAnalyze();
        analyze.testEntry();
    }
}


