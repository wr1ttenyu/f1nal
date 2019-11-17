package wr1ttenyu.f1nal.study.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

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
public class Wr1NonBlockingNIOServer {

    // todo continue https://www.oschina.net/question/2618986_2149914 看看这个代码行不行

    public static void main(String[] args) {
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
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    // process accept event
                    if (sk.isAcceptable()) {
                        SocketChannel socketChannel = serverSokcetChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                        InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                        String hostAddress = remoteAddress.getAddress().getHostAddress();
                        int port = remoteAddress.getPort();
                        log.info("accept request from address --> {}:{}", hostAddress, port);
                    } else if (sk.isReadable()) {
                        // process read event
                        // gain channel from SelectionKey
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);

                        InetSocketAddress remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
                        String hostAddress = remoteAddress.getAddress().getHostAddress();
                        int port = remoteAddress.getPort();
                        log.info("Read request from address --> {}:{}", hostAddress, port);

                        int length;
                        while ((length = sChannel.read(buf)) > 0) {
                            buf.flip();
                            System.out.println(new String(buf.array(), 0, length));
                            buf.clear();
                        }

                        String msg = "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦 wr1-server say hello to address --> " + hostAddress + ":" + port + "\n";
                        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                        wrap.flip();
                        sChannel.write(wrap);

                        sk.interestOps(SelectionKey.OP_WRITE);
                    } else if(sk.isWritable()) {
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        InetSocketAddress remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
                        String hostAddress = remoteAddress.getAddress().getHostAddress();
                        int port = remoteAddress.getPort();
                        log.info("wr1-server say hello to address --> {}:{}", hostAddress, port);

                        String msg = "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦 wr1-server say hello to address --> " + hostAddress + ":" + port + "\n";
                        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                        wrap.flip();
                        sChannel.write(wrap);
                        // 取消写就绪，否则会一直触发写就绪（写就绪为代码触发）
                        sk.interestOps(SelectionKey.OP_READ);
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
