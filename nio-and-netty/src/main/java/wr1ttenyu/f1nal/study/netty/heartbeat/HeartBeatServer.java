package wr1ttenyu.f1nal.study.netty.heartbeat;

import com.sun.xml.internal.bind.v2.TODO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AbstractChannel;
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
                     * SOLVE
                     * {@link IdleStateHandler#hasOutputChanged(io.netty.channel.ChannelHandlerContext, boolean)} 这个方法是干什么的
                     * netty 利用 {@link IdleStateHandler#writeListener} 这个监听器来监听写完成事件来更新最后写事件的时间，
                     * 但是可能会出现这样一个问题，当出现写空闲的时候我们就关闭和客户端的连接，当客户端接收数据缓慢的时候，我们在持续的向客户端
                     *  写入数据，在设定的时间内一直没有写出完毕，这就导致出现写空闲，但其实是一直再写，为了避免这种情况，上述方法可以直接检测
                     *  {@link AbstractChannel.AbstractUnsafe#outboundBuffer} 是否有变化，来监测是否有持续写出
                     *
                     * {@link IdleStateHandler} 检测读空闲写空闲事件
                     * {@link ReadTimeoutHandler}  继承自 {@link IdleStateHandler} 如果发生读空闲则关闭连接
                     * {@link WriteTimeoutHandler} 写空闲则是监听一次向外的写事件，如果在规定事件没有完成则触发写超时，关闭连接
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
