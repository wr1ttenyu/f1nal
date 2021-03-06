package wr1ttenyu.f1nal.study.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)) // 在bossGroup 增加一个日志处理器
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 因为是基于http协议，使用http的编码和解码器
                        pipeline.addLast(new HttpServerCodec());
                        // 是以块方式写的，添加 ChunkedWriteHandler
                        pipeline.addLast(new ChunkedWriteHandler());
                        /*
                          说明
                          1. http 数据在传输过程中是分段， HttpObjectAggregator，就是可以将多个段聚合
                          2. 因为当浏览器发送大量数据时，就会发出多次http请求
                         */
                        pipeline.addLast(new HttpObjectAggregator(8192));
                        /*
                          说明：
                          1. 对应websocket,它的数据是以 帧（frame）形式传递
                          2. 可以看到WebSocketFrame 下面有六个子类
                          3. 浏览器请求时 ws://localhost:7000/hello 表示请求的uri
                          4. WebSocketServerProtocolHandler 核心功能是将 http 协议升级为 ws 协议，保持长链接
                          5. http 协议升级为 ws 协议 是通过请求状态码 status code 101
                         */
                        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                        // 自定义handler，处理业务逻辑
                        pipeline.addLast(new MyWebSocketHandler());
                    }
                });

        try {
            bootstrap.bind(8989).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
