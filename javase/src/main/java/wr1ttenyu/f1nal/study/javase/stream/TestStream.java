package wr1ttenyu.f1nal.study.javase.stream;

import java.util.ArrayList;
import java.util.List;

public class TestStream {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList(200000);
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }

        long start1 = System.currentTimeMillis();
        for (Integer item : list) {
            String str = String.valueOf(item);
        }
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        list.stream().forEach(x-> { String str = String.valueOf(x);});
        list.stream().parallel().spliterator();
        long end2 = System.currentTimeMillis();

        System.out.println("first: " + (end1 - start1));
        System.out.println("second: " + (end2 - start2));
    }
}
