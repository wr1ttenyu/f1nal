package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GroupChatRoom {

    private Selector selector;

    private final ExecutorService serverWorkerPool = new ThreadPoolExecutor(1, 2, 10,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), new Wr1ThreadFactory(), new Wr1RejectedExecutionHandler());

    public static void main(String[] args) {
        try {
            GroupChatRoom server = new GroupChatRoom();
            NioServerEventHandler serverEventHandler = new NioServerEventHandler();
            server.initServer();
            server.listen(serverEventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void listen(NioServerEventHandler serverEventHandler) throws IOException {
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
                    serverEventHandler.handleRead(sk);
                }
            }
        }
    }
}