package wr1ttenyu.f1nal.study.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        // 创建BossGroup 和 WorkerGroup
        // 说明
        // 1. 创建两个线程组 BossGroup 和 WorkerGroup
        // 2. BossGroup 只处理连接请求，真正的客户端业务处理，会交给 WorkerGroup 来完成
        // 3. 两个都是无限循环
        // 4. bossGroup 和 workerGroup 含有的子线程（NioEventLoop）的个数 默认实际是 cpu 核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        // 创建服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel 作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数 // TODO SO_BACKLOG 参数作用
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态  // TODO SO_KEEPALIVE 参数作用
                .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道初始化对象（匿名对象）
                    // 给pipeline设置处理器
                    @Override
                    protected void initChannel(SocketChannel sc) {
                        sc.pipeline().addLast(new NettyServerHandler());
                    }
                }); // 给我们的workerGroup的EventLoop对应的管道设置处理器

        System.out.println(".......... server is ready  ..........");

        try {
            // 绑定一个端口并且同步，生成一个 ChannelFuture 对象
            // 启动服务器（并绑定端口）
            ChannelFuture cf = bootstrap.bind(6688).sync();
            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
