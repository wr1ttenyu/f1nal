package wr1ttenyu.f1nal.study.netty.inandoutboundhandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

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
                // SO_BACKLOG tcp 三次握手 第二次握手 和 第三次握手的次数 分别会保存在两个队列中
                // 第二次握手之后 等待客户端应答  第三次握手之后  等待服务端accept
                // SO_BACKLOG 就是设置这两个队列的内连接数量的最大值
                .option(ChannelOption.SO_BACKLOG, 128)
                // SO_KEEPALIVE 参数作用 相当于开启 tcp 自带的心跳功能
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                .childHandler(new ServerChannelInitializer()); // 给我们的workerGroup的EventLoop对应的管道设置处理器

        System.out.println(".......... server is ready  ..........");

        try {
            // 绑定一个端口并且同步，生成一个 ChannelFuture 对象
            // 启动服务器（并绑定端口）
            ChannelFuture cf = bootstrap.bind(8888).sync();
            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
