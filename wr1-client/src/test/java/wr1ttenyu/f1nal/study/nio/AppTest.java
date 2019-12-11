package wr1ttenyu.f1nal.study.nio;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testALotOfClient() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    Wr1NonBlockingNIOClient nioClient = new Wr1NonBlockingNIOClient();
                    NioClientEventHandler clientEventHandler = new NioClientEventHandler();
                    nioClient.initClient();
                    nioClient.listen(clientEventHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
