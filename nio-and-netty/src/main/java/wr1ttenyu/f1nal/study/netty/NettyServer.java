package wr1ttenyu.f1nal.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    private String ip;

    private Integer port;

    private ChannelInitializer channelInitializer;

    public NettyServer(String ip, Integer port, ChannelInitializer channelInitializer) {
        this.ip = ip;
        this.port = port;
        this.channelInitializer = channelInitializer;
    }

    public void startServer() {
        ServerBootstrap server = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(channelInitializer);

        try {
            ChannelFuture bindCf = server.bind(ip, port).addListeners((future) -> {
                if (future.isSuccess()) {
                    log.info("服务器启动成功，ip:{} port:{}", ip, port);
                } else {
                    log.error("服务器启动失败，ip:{} port:{}", ip, port);
                }
            }).sync();

            ChannelFuture closeCf = bindCf.channel().closeFuture().addListeners((future) -> {
                if (future.isSuccess()) {
                    log.info("服务器关闭成功，ip:{} port:{}", ip, port);
                } else {
                    log.error("服务器关闭失败，ip:{} port:{}", ip, port);
                }
            }).sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
