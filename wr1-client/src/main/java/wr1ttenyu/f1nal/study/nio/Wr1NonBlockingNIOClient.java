package wr1ttenyu.f1nal.study.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
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
@Slf4j
public class Wr1NonBlockingNIOClient {

    public static void main(String[] args) {
        try {
            // 1. gain channel
            SelectorProvider provider = SelectorProvider.provider();
            SocketChannel client =  provider.openSocketChannel();

            // 2. switch to non blocking model
            client.configureBlocking(false);

            // 3. gain selector
            Selector selector = provider.openSelector();

            // 4. register channel into selector and specify listen to accept event
            SelectionKey key = client.register(selector, SelectionKey.OP_CONNECT);

            if (client.connect(new InetSocketAddress("127.0.0.1", 9898))) {
                System.out.println("connected...");
                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 监听读就绪和写就绪（准备写数据）
            } else {
                key.interestOps(SelectionKey.OP_CONNECT); // 监听连接就绪
            }

            // 6. polled get ready events in selectors
            for(;;) {
                selector.select();
                // 7. gain ready select key iterator
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    if (sk.isReadable()) {
                        // process read event
                        // gain channel from SelectionKey
                        SocketChannel sc = (SocketChannel) sk.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);

                        InetSocketAddress remoteAddress = (InetSocketAddress) sc.getRemoteAddress();
                        String hostAddress = remoteAddress.getAddress().getHostAddress();
                        int port = remoteAddress.getPort();
                        log.info("Read msg from address --> {}:{}", hostAddress, port);

                        int length;
                        while ((length = sc.read(buf)) > 0) {
                            buf.flip();
                            System.out.println(new String(buf.array(), 0, length));
                            buf.clear();
                        }
                        sk.interestOps(key.interestOps() | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    } else if (sk.isWritable()) {
                        // process read event
                        // gain channel from SelectionKey
                        SocketChannel sc = (SocketChannel) sk.channel();

                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        Scanner scanner = new Scanner(System.in);

                        /*while (!scanner.hasNext("eof")) {*/
                            String str = scanner.next();
                            buf.put((new Date().toString() + "\n" + str).getBytes());
                            buf.flip();
                            sc.write(buf);
                            buf.clear();
                       /* }*/
                        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    } else if(sk.isConnectable()) {
                        System.out.println("conntection has been built");
                        client.finishConnect(); // 完成连接
                        System.out.println("connected...");
                        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                    // remove select key has been processed
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
