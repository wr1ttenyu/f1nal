package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 得到管道 向管道中加入处理器
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("myStickAndUnpackDecoder", new MyStickAndUnpackDecoder());
        pipeline.addLast("myStickAndUnpackEncoder", new MyStickAndUnpackEncoder());
        pipeline.addLast("myServerHandlerWithoutDecoder", new NettyServerHandlerWithoutDecoder());
        pipeline.addLast("myServerHandler", new NettyServerHandler());
    }
}
