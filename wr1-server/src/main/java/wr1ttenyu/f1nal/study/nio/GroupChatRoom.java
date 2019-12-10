package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Signal;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GroupChatRoom {

    private Selector selector;

    private final ExecutorService serverWorkerPool = new ThreadPoolExecutor(1, 2, 10,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), new Wr1ThreadFactory(), new Wr1RejectedExecutionHandler());

    public static HashMap<String, SocketChannel> CHANNEL_CONTIANER = new HashMap<>();

    public static void main(String[] args) {

        // SIGTERM 为linux下的信号量
        Signal sg = new Signal("TERM"); // kill -15 pid
        // 监听信号量
        Signal.handle(sg, (signal) -> {
            log.info("signal handle: " + signal.getName() + " 开始关闭聊天室");
            sendChatRoomCloseMsg("管理员执行了关闭聊天室命令 开始关闭....");
            System.exit(0);
        });

        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 在关闭钩子中执行收尾工作
            // 注意事项：
            // 1.在这里执行的动作不能耗时太久
            // 2.不能在这里再执行注册，移除关闭钩子的操作
            // 3 不能在这里调用System.exit()
            log.error("聊天室出现异常 开始关闭....");
            sendChatRoomCloseMsg("聊天室出现异常 开始关闭....");
            log.error("do shutdown hook");
        }));

        try {
            GroupChatRoom server = new GroupChatRoom();
            GroupChatRoomServerEventHandler serverEventHandler = new GroupChatRoomServerEventHandler();
            server.initServer();
            server.listen(serverEventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendChatRoomCloseMsg(String msg) {
        if(msg == null) {
            return;
        }
        for (Map.Entry<String,SocketChannel> entry : CHANNEL_CONTIANER.entrySet()) {
            try {
                entry.getValue().write(ByteBuffer.wrap(msg.getBytes()));
            } catch (IOException e) {
                log.error("聊天室异常关闭信息发送失败,channelKey:{},errorMsg:{}", entry.getKey(), e.getMessage());
            }
        }
        log.info("已经通过全部客户端聊天室关闭信息");
    }

    private void initServer() throws IOException {
        // 1. gain channel
        ServerSocketChannel serverSokcetChannel = ServerSocketChannel.open();

        // 2. switch to non blocking model
        serverSokcetChannel.configureBlocking(false);

        // 3. bind port
        serverSokcetChannel.bind(new InetSocketAddress(9898));

        // 4. gain selector
        selector = Selector.open();

        // 5. register channel into selector and specify listen to accept event
        serverSokcetChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void listen(NioEventHandler serverEventHandler) throws IOException {
        // polled get ready events in selectors
        while (selector.select() > 0) {
            // gain ready select key iterator
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                // remove select key has been processed
                iterator.remove();
                // process accept event
                if (sk.isAcceptable()) {
                    serverEventHandler.handleAccept(selector, sk);
                } else if (sk.isReadable()) {
                    serverEventHandler.handleRead(selector, sk);
                }
            }
        }
    }
}