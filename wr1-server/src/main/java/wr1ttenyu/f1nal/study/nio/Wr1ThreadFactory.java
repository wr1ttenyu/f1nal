package wr1ttenyu.f1nal.study.nio;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class Wr1ThreadFactory implements ThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "wr1-server worker num:" + threadNum.getAndIncrement());
    }
}

