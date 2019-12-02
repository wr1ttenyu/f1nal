package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

@Slf4j
public class NioClientEventHandler implements NioEventHandler {

    @Override
    public void handleConnect(SelectionKey sk) {
        try {
            SocketChannel sc = (SocketChannel) sk.channel();
            sc.finishConnect(); // 完成连接
            log.info("conntection has been built");
            sk.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleRead(SelectionKey sk) {
        try {
            // process read event
            // gain channel from SelectionKey
            SocketChannel sc = (SocketChannel) sk.channel();
            InetSocketAddress remoteAddress = (InetSocketAddress) sc.getRemoteAddress();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            int port = remoteAddress.getPort();
            log.info("read msg from address --> {}:{}", hostAddress, port);

            ByteBuffer buf = ByteBuffer.allocate(1024);
            int length;
            while ((length = sc.read(buf)) > 0) {
                buf.flip();
                log.info("read from address --> {}:{} msg is {}", hostAddress, port, new String(buf.array(), 0,
                        length));
                buf.clear();
            }

            sk.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleWrite(SelectionKey sk) {
        try {
            // process read event
            // gain channel from SelectionKey
            SocketChannel sc = (SocketChannel) sk.channel();

            // 3. generator fixed size byte buffer
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNext("eof")) {
                String str = scanner.next();
                buf.put((new Date().toString() + "\n" + str).getBytes());
                buf.flip();
                sc.write(buf);
                buf.clear();
            }

            InetSocketAddress remoteAddress = (InetSocketAddress) sc.getRemoteAddress();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            int port = remoteAddress.getPort();
            log.info("send msg to server --> {}:{}", hostAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
