package wr1ttenyu.f1nal.study.netty.threadpool;

import wr1ttenyu.f1nal.study.netty.NettyServer;

/**
 * 给耗时任务增加异步执行线程池的两种方式:
 * 1. {@link io.netty.channel.ChannelPipeline#addLast(io.netty.util.concurrent.EventExecutorGroup, io.netty.channel.ChannelHandler...)}
 *      使用 Pipeline 的 addLast 带 EventExecutorGroup 参数的重载方法，给 Handler 注册线程池
 * 2. 直接在 Handler 中添加静态线程池，然后将耗时任务提交到线程池中执行
 *
 */
public class ThreadPoolServer {

    public static void main(String[] args) {
        NettyServer server = new NettyServer("127.0.0.1", 8888, new ThreadPoolChannelInitializer());
        server.startServer();
    }
}
