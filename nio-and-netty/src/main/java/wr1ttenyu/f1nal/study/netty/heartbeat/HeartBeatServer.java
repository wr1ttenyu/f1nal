package wr1ttenyu.f1nal.study.netty.heartbeat;

import com.sun.xml.internal.bind.v2.TODO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class HeartBeatServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)) // 在bossGroup 增加一个日志处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * TODO
                     * {@link IdleStateHandler#hasOutputChanged(io.netty.channel.ChannelHandlerContext, boolean)} 这个方法是干什么的
                     *
                     * {@link IdleStateHandler}
                     * {@link ReadTimeoutHandler}
                     * {@link WriteTimeoutHandler} 三个Handler的机制
                     */
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 加入一个 netty 提供的 处理空闲 Handler
                        pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS))
                                .addLast(new MyIdleEventHandler());
                    }
                });

        try {
            bootstrap.bind(8989).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
