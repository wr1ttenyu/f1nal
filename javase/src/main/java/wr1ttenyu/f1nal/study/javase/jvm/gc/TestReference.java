package wr1ttenyu.f1nal.study.javase.jvm.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class TestReference {

    public static void main(String[] args) {
        TestReference testReference = new TestReference();
        try {
            testReference.testWeakHashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testSoftReference() {
        SoftReference<Object> softReference = new SoftReference<>(new Object());
    }

    private void testWeakReference() {
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
    }

    private void testWeakHashMap() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap();
        Integer key = new Integer(1);
        Integer key2 = new Integer(1);
        String value = "123";

        weakHashMap.put(key, value);
        System.out.println(weakHashMap);

        key = null; // 当
        System.gc();
        System.out.println(value);
        System.out.println(key2);
        System.out.println(weakHashMap);
        System.out.println(value);
        System.out.println(key2);
    }
}
