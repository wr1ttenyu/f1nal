package wr1ttenyu.f1nal.study.netty.dubbo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyDubboClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new MyDubboClientHandler());
    }
}