package wr1ttenyu.f1nal.study.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                Wr1NonBlockingNIOClient client = new Wr1NonBlockingNIOClient();
                client.runClient();
            });
        }

    }
}
