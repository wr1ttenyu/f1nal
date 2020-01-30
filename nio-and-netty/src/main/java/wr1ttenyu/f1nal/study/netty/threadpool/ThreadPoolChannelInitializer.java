package wr1ttenyu.f1nal.study.netty.threadpool;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class ThreadPoolChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        EventExecutorGroup group = new DefaultEventExecutorGroup(2);
        ch.pipeline().addLast(group, new MyThreadPoolNettyHandler());
    }
}