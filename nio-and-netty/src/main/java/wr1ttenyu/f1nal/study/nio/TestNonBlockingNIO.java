package wr1ttenyu.f1nal.study.nio;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
public class TestNonBlockingNIO {

    @Test
    public void client() throws IOException {
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
    }

    @Test
    public void server() {
        try {
            // 1. gain channel
            ServerSocketChannel serverSokcetChannel = ServerSocketChannel.open();

            // 2. switch to non blocking model
            serverSokcetChannel.configureBlocking(false);

            // 3. bind port
            serverSokcetChannel.bind(new InetSocketAddress(9898));

            // 4. gain selector
            Selector selector = Selector.open();

            // 5. register channel into selector and specify listen to accept event
            serverSokcetChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 6. polled get ready events in selectors
            while (selector.select() > 0) {
                // 7. gain ready select key iterator
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    // TODO continue
                    // accept event p
                    if(sk.isAcceptable()) {
                        SocketChannel socketChannel = serverSokcetChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
