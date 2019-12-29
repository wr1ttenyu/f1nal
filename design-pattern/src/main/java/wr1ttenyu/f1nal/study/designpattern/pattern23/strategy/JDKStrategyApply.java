package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

import java.util.Arrays;

public class JDKStrategyApply {

    public static void main(String[] args) {

        Integer[] data = {1, 2, 3, 897, 45};

        // java.util.Arrays.sort(T[], java.util.Comparator<? super T>)
        // Comparator 就相当于策略接口
        Arrays.sort(data, (v1, v2) -> {
            if (v1 > v2) return 1;
            else return -1;
        });

        System.out.println(Arrays.toString(data));
    }
}
