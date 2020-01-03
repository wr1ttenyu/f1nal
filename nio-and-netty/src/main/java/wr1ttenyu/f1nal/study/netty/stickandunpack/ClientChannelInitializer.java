package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 得到管道 向管道中加入处理器
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("myStickAndUnpackEncoder", new MyStickAndUnpackEncoder());
        pipeline.addLast("myStickAndUnpackDecoder", new MyStickAndUnpackDecoder());
        pipeline.addLast("myClientHandler", new NettyClientHandler());
    }
}
