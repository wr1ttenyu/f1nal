package wr1ttenyu.f1nal.study.netty.httpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道中加入处理器

        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();
        // 加入一个netty提供的 httpServerCodec codec-> coder & decoder
        pipeline.addLast("wr1HttpServerCodec", new HttpServerCodec())
                // 再加入一个我们自己自定义的 handler
                .addLast("wr1HttpServerHandler", new HttpServerHandler());

    }
}
