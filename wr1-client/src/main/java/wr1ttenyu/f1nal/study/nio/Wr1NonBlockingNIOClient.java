package wr1ttenyu.f1nal.study.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

    private Selector selector;

    private static ExecutorService workers = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try {
            Wr1NonBlockingNIOClient nioClient = new Wr1NonBlockingNIOClient();
            NioClientEventHandler clientEventHandler = new NioClientEventHandler();
            nioClient.initClient();
            nioClient.listen(clientEventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runClient() {
        try {
            Wr1NonBlockingNIOClient nioClient = new Wr1NonBlockingNIOClient();
            NioClientEventHandler clientEventHandler = new NioClientEventHandler();
            nioClient.initClient();
            nioClient.listen(clientEventHandler);
        } catch (Exception e) {
            log.info("client exception msg : {}", e.getMessage());
        }
    }

    private void listen(NioClientEventHandler clientEventHandler) throws IOException {
        // polled get ready events in selectors
        while(selector.select() > 0) {
            // gain ready select key iterator
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                // remove select key has been processed
                iterator.remove();
                if(sk.isConnectable()) {
                    clientEventHandler.handleConnect(sk);

                    workers.execute(() -> {
                        clientEventHandler.handleWrite(sk);
                    });
                } else if (sk.isReadable()) {
                    clientEventHandler.handleRead(sk);
                }
            }
        }
    }

    // nio channel 来了没有注册的事件 会怎么样
    // 对于读事件 即使没有去监听 服务端发送的数据都会积累在tcp的缓冲区 等到监听读事件时  会一次性全部读出来
    private void initClient() throws IOException{
        // 1. gain channel
        SelectorProvider selectorProvider = SelectorProvider.provider();
        SocketChannel client =  selectorProvider.openSocketChannel();
        // 2. switch to non blocking model
        client.configureBlocking(false);
        // 3. gain selector
        this.selector = selectorProvider.openSelector();
        // 4. register channel into selector and specify listen to accept event
        SelectionKey sk = client.register(this.selector, SelectionKey.OP_CONNECT);
        // 122.51.219.124
        if (client.connect(new InetSocketAddress("127.0.0.1", 9898))) {
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 监听读就绪和写就绪（准备写数据）
        } else {
            sk.interestOps(SelectionKey.OP_CONNECT); // 监听连接就绪
        }
    }
}
