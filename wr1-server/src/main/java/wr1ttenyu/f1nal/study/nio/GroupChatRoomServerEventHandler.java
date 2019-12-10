package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

import static wr1ttenyu.f1nal.study.nio.GroupChatRoom.CHANNEL_CONTIANER;

@Slf4j
public class GroupChatRoomServerEventHandler implements NioEventHandler {

    @Override
    public void handleAccept(Selector selector, SelectionKey sk) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);

            InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
            addChannel(remoteAddress, socketChannel);
            int port = remoteAddress.getPort();
            log.info("用户：{} 登录了!", port);

            String welcomeMsg = "hi " + port + ",欢迎登陆聊天室";
            ByteBuffer welcomeMsgBuffer = ByteBuffer.wrap(welcomeMsg.getBytes());
            socketChannel.write(welcomeMsgBuffer);

            String loginMsg = "用户：" + port + " 已经登录了!";
            broadcastMsg(selector, sk, loginMsg);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            log.error("have a exception msg:{} stack-trace:{}", e.getMessage(), e.getStackTrace());
        }
    }

    @Override
    public void handleRead(Selector selector, SelectionKey sk) {
        Integer port = 0;
        SocketChannel sChannel = null;
        InetSocketAddress remoteAddress = null;
        try {
            sChannel = (SocketChannel) sk.channel();
            remoteAddress = (InetSocketAddress) sChannel.getRemoteAddress();
            // process read event
            // gain channel from SelectionKey
            port = remoteAddress.getPort();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int length;
            String msg = "";
            while ((length = sChannel.read(buf)) > 0) {
                buf.flip();
                msg = port + " 说: " + new String(buf.array(), 0, length);
                log.info(msg);
                buf.clear();
            }

            // 断开连接后,会产生OP_READ事件,length 小于0则关闭channel
            if (length < 0) {
                String logoutMsg = "用户：" + port + " 正常下线了!";
                log.info(logoutMsg);
                removeChannel(remoteAddress);
                sChannel.close();
                sk.cancel();
            } else {
                // 将客户端消息广播给其他客户端
                broadcastMsg(selector, sk, msg);
                sk.interestOps(SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            sk.cancel();
            try {
                if (sChannel != null) {
                    sChannel.close();
                }
                String logoutMsg = "用户：" + port + " 强制下线了!";
                removeChannel(remoteAddress);
                log.info(logoutMsg);
                broadcastMsg(selector, sk, logoutMsg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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

    private void broadcastMsg(Selector selector, SelectionKey sk, String loginMsg) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        if (!keys.isEmpty()) {
            SocketChannel channel;
            for (SelectionKey key : keys) {
                if (key != sk && key.channel() instanceof SocketChannel) {
                    ByteBuffer loginMsgBuffer = ByteBuffer.wrap(loginMsg.getBytes());
                    channel = (SocketChannel) key.channel();
                    channel.write(loginMsgBuffer);
                }
            }
        }
    }

    private String createChannelKey(InetSocketAddress remoteAddress) {
        return remoteAddress.getHostName() + ":" + remoteAddress.getPort();
    }

    private void removeChannel(InetSocketAddress remoteAddress) {
        if(remoteAddress != null) {
            CHANNEL_CONTIANER.remove(remoteAddress.getHostName() + ":" + remoteAddress.getPort());
        }
    }

    private void addChannel(InetSocketAddress remoteAddress, SocketChannel socketChannel) {
        if(remoteAddress != null) {
            String channelKey = createChannelKey(remoteAddress);
            CHANNEL_CONTIANER.put(channelKey, socketChannel);
        }
    }
}
