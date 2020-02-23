package wr1ttenyu.f1nal.study.netty.dubbo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wr1ttenyu.f1nal.study.netty.dubbo.service.IHelloService;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class DubboClient {

    private static MyDubboClientHandler dubboClientHandler;

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) {
        DubboClient dubboClient = new DubboClient();
        try {
            dubboClient.startServer();

            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
            applicationContext.scan("wr1ttenyu.f1nal.study.netty.dubbo.spring");
            applicationContext.refresh();

            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            while (!scanner.hasNext("eof")) {
                String name = scanner.next();
                IHelloService helloService = applicationContext.getBean(IHelloService.class);
                String hello = helloService.hello(name);
                System.out.println(hello);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            dubboClient.workerGroup.shutdownGracefully();
        }
    }

    private void startServer() throws InterruptedException {
        Bootstrap client = new Bootstrap();

        dubboClientHandler = new MyDubboClientHandler();

        client.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(dubboClientHandler);
                    }
                });

        ChannelFuture bindCf = client.connect("127.0.0.1", 8888).addListeners((future) -> {
            if (future.isSuccess()) {
                log.info("连接服务器成功，ip:{} port:{}", "127.0.0.1", 8888);
            } else {
                log.error("连接服务器失败，ip:{} port:{}", "127.0.0.1", 8888);
            }
        }).sync();

    }

    public static MyDubboClientHandler getDubboClientHandler() {
        return dubboClientHandler;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}
