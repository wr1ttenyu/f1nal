package wr1ttenyu.f1nal.study.javase.jvm.gc;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class TestWeakReference {

    private static ThreadLocal<String> th = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        MyClass myClass2 = new MyClass();
        WeakReference<MyClass> wr = new WeakReference<>(new MyClass());
        myClass2.setWr(wr);
        System.gc();
        MyClass myClass1 = wr.get();

        Thread thread = new Thread(() -> {
            th.set("123");
            System.out.println("设置完毕");
            try {
                System.out.println("睡眠");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            th.remove();
            System.out.println("获取值：" + th.get());
        });

        thread.start();
        TimeUnit.SECONDS.sleep(5);

        th = null;



        System.gc();

        System.out.println(myClass1);

        TimeUnit.SECONDS.sleep(50);
    }

}

class MyClass {

    private String fidld;

    private WeakReference<MyClass> wr;

    public String getFidld() {
        return fidld;
    }

    public void setFidld(String fidld) {
        this.fidld = fidld;
    }

    public WeakReference<MyClass> getWr() {
        return wr;
    }

    public void setWr(WeakReference<MyClass> wr) {
        this.wr = wr;
    }
}
