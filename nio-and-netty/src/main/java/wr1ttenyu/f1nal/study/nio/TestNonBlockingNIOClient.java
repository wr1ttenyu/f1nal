package wr1ttenyu.f1nal.study.nio;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 1. three key step use nio to finish network communication
 * <p>
 * a. channel
 * <p>
 * b. buffer
 * <p>
 * c. selector
 */
public class TestNonBlockingNIOClient {

    public static void main(String[] args) {
        try {
            // 1. gain channel
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            // 2. switch to non blocking model
            socketChannel.configureBlocking(false);

            // 3. generator fixed size byte buffer
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 4. send data to server
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String str = scanner.next();
                buf.put((new Date().toString() + "\n" + str).getBytes());
                buf.flip();
                socketChannel.write(buf);
                buf.clear();
            }

            // 5. close channel
            socketChannel.close();
        } catch (IOException e) {


        }
    }

    public static void mainUDP(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

            ByteBuffer buf = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String str = scanner.next();
                buf.put((new Date().toString() + "\n" + str).getBytes());
                buf.flip();
                datagramChannel.send(buf, new InetSocketAddress("127.0.0.1", 8999));
                buf.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
