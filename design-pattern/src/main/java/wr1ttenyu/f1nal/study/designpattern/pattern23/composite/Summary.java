package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

import java.util.HashMap;
import java.util.Map;

/**
 * 组合模式在 jdk 中的应用：
 *
 */
public class Summary {

    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(0, "东游记");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "西游记");
        map2.put(2, "红楼梦");
    }
}
