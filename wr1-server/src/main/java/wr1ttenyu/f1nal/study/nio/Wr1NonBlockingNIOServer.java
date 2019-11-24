package wr1ttenyu.f1nal.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.*;

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

    // 在代码实践过程中发现的  一直处于写就绪的情况  跟nio底层的 epoll 有关系
    // epoll 是 实现I/O多路复用的一种方法
    // epoll 分为 水平触发 和 边缘触发
    // 水平触发(level-triggered，也被称为条件触发)LT: 只要满足条件，就触发一个事件(只要有数据没有被获取，内核就不断通知你)
    // 边缘触发(edge-triggered)ET: 每当状态变化时，触发一个事件。
    // 在 java 的实现中  是用的水平触发  这个就导致 且只要内核缓冲区还不满就会一直写就绪 读也是一样 只要缓冲区的数据没有读完 就会一直触发读就绪
    // 所以在使用Java的NIO编程的时候，在没有数据可以往外写的时候要取消写事件，在有数据往外写的时候再注册写事件。
    private Selector selector;

    private final ExecutorService serverWorkerPool = new ThreadPoolExecutor(1, 2, 10,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), new Wr1ThreadFactory(), new Wr1RejectedExecutionHandler());

    public static void main(String[] args) {
        try {
            Wr1NonBlockingNIOServer server = new Wr1NonBlockingNIOServer();
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
                // TODO 这里使用线程池之后 出现了问题 就是同一个事件被多次处理
                serverWorkerPool.execute(new Wr1ServerTask(serverEventHandler, selector, sk));
            }
        }
    }
}