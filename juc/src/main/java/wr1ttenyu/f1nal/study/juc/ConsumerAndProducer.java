package wr1ttenyu.f1nal.study.juc;

import java.util.concurrent.TimeUnit;

public class ConsumerAndProducer {
    public static void main(String[] args) {
        CPWaitNotify cpWaitNotify = new CPWaitNotify();
        new Thread(() -> {cpWaitNotify.produce();}).start();
        new Thread(() -> {cpWaitNotify.consume();}).start();

        try {
            TimeUnit.SECONDS.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CPWaitNotify {

    private Integer resource = 0;

    public synchronized void produce() {
        for(;;) {
            while (resource < 10) {
                System.out.println("生产资源，生产者休眠.....");
                resource++;
            }
            try {
                System.out.println("资源数量大于10，生产者休眠.....");
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void consume() {
        for(;;) {
            while (resource > 0) {
                resource--;
            }
            try {
                System.out.println("资源数量为0，消费者休眠.....");
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}