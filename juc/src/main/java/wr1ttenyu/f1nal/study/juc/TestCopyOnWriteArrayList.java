package wr1ttenyu.f1nal.study.juc;

import java.util.*;

public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(helloThread).start();
        }
    }
}

class HelloThread implements Runnable {

    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
