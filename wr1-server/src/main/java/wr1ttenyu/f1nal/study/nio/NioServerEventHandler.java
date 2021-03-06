package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Slf4j
public class NioServerEventHandler implements NioEventHandler {

    @Override
    public void handleAccept(Selector selector, SelectionKey sk) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);

            InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            int port = remoteAddress.getPort();
            log.info("accept request from {}:{} has been handled", hostAddress, port);

            String msg = "hi,欢迎登陆聊天室";
            ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
            socketChannel.write(wrap);

            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            log.error("have a exception msg:{} stack-trace:{}", e.getMessage(), e.getStackTrace());
        }
    }

    @Override
    public void handleRead(Selector selector, SelectionKey sk) {
        log.info("{} handle read event", Thread.currentThread().getName());
        SocketChannel sChannel = (SocketChannel) sk.channel();
        String hostAddress = "@hostAddress@";
        Integer port = 0;
        try {
            // process read event
            // gain channel from SelectionKey
            InetSocketAddress remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
            hostAddress = remoteAddress.getAddress().getHostAddress();
            port = remoteAddress.getPort();
            log.info("from {}:{} read event", hostAddress, port);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int length;
            while ((length = sChannel.read(buf)) > 0) {
                buf.flip();
                log.info("msg from {}:{} --> {}", hostAddress, port, new String(buf.array(), 0, length));
                buf.clear();
            }

            // 断开连接后,会产生OP_READ事件,length 小于0则关闭channel
            if (length < 0) {
                sChannel.close();
                sk.cancel();
                log.info("connect close from {}:{}", hostAddress, port);
            } else {
                // 读完之后 给客户端回话
                String msg = "hi,i am wr1ttenyu, my ip : 122.51.219.124";
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                sChannel.write(wrap);
                sk.interestOps(SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            try {
                sChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            sk.cancel();
            log.info("occur exception msg : {} \n connect close from {}:{}", e.getMessage(), hostAddress, port);
        }
    }

    @Override
    public void handleWrite(Selector selector, SelectionKey sk) {
        log.info("{} handle write event", Thread.currentThread().getName());
        try {
            // 取消写就绪，否则会一直触发写就绪（写就绪为代码触发）
            sk.interestOps(SelectionKey.OP_READ);
            SocketChannel sChannel = (SocketChannel) sk.channel();
            InetSocketAddress remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            int port = remoteAddress.getPort();
            log.info("say hello to {}:{}", hostAddress, port);

            String msg = "hi,i am wr1ttenyu, my ip : 122.51.219.124";
            ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
            sChannel.write(wrap);
        } catch (IOException e) {
            log.error("have a exception msg:{} stack-trace:{}", e.getMessage(), e.getStackTrace());
        }
    }
}
