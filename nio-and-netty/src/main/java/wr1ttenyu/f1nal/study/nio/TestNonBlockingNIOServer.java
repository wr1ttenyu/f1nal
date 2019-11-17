package wr1ttenyu.f1nal.study.nio;


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
public class TestNonBlockingNIOServer {

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
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (sk.isReadable()) {
                        // process read event
                        // gain channel from SelectionKey
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        ByteBuffer buf = ByteBuffer.allocate(1024);

                        InetSocketAddress remoteAddress = (InetSocketAddress)sChannel.getRemoteAddress();

                        /*InetAddress address = remoteAddress.getAddress();
                        String canonicalHostName = address.getCanonicalHostName();
                        System.out.println("canonicalHostName:" + canonicalHostName);
                        String hostAddress = address.getHostAddress();
                        System.out.println("hostAddress:" + hostAddress);
                        String hostName1 = address.getHostName();
                        System.out.println("hostName1:" + hostName1);

                        String hostName = remoteAddress.getHostName();
                        System.out.println("hostName:" + hostName);
                        String hostString = remoteAddress.getHostString();
                        System.out.println("hostString:" + hostString);
                        int port = remoteAddress.getPort();
                        System.out.println("port:" + port);*/

                        int length = 0;
                        while ((length = sChannel.read(buf)) > 0) {
                            buf.flip();
                            System.out.println(new String(buf.array(), 0, length));
                            buf.clear();
                        }
                    }
                    // remove select key has been processed
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainUDP(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(8999));

            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);

            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                SelectionKey sk = it.next();
                if (sk.isReadable()) {
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    while (sChannel.read(buf) > 0) {
                        System.out.println(new String(buf.array()));
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
