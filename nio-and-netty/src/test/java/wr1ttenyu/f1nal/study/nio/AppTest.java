package wr1ttenyu.f1nal.study.nio;


import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

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


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("开始读取");
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    System.out.println("阻塞开始");
                    char msg = dataInputStream.readChar();
                    System.out.println("阻塞结束");
                    System.out.println("读取完毕,读取结果为：" + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        while (!scanner.hasNext("eof")) {
            String str = scanner.next();
            System.out.println(str);
        }

    }
}
