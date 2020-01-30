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
import wr1ttenyu.f1nal.study.netty.dubbo.service.IHelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class DubboClient {

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        Bootstrap client = new Bootstrap();

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        MyDubboClientHandler dubboClientHandler = new MyDubboClientHandler();

        ExecutorService executor = Executors.newFixedThreadPool(2);

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

        try {
            ChannelFuture bindCf = client.connect("127.0.0.1", 8888).addListeners((future) -> {
                if (future.isSuccess()) {
                    log.info("连接服务器成功，ip:{} port:{}", "127.0.0.1", 8888);
                } else {
                    log.error("连接服务器失败，ip:{} port:{}", "127.0.0.1", 8888);
                }
            }).sync();

            Class<IHelloService> iHelloServiceClass = IHelloService.class;
            Class[] interfaces = {iHelloServiceClass};
            IHelloService helloService = (IHelloService) Proxy.newProxyInstance(Thread.currentThread()
                .getContextClassLoader(), interfaces,
                (proxy, method, args) -> {
                    String argsPrefix = iHelloServiceClass.getName() + "#" + iHelloServiceClass
                        .getMethod("hello", String.class).getName() + "#";
                    dubboClientHandler.setParam(argsPrefix + args[0]);
                    Future<String> future = executor.submit(dubboClientHandler);
                    return future.get();
                });

            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            while (!scanner.hasNext("eof")) {
                String str = scanner.next();
                String hello = helloService.hello(str);
                System.out.println(hello);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    class ServiceProxyCreater implements InvocationHandler {

        private Object obj;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(obj, args);
        }

        public <T> T getProxy(Class[] interfaces, Object object) {
            this.obj = object;
            return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), interfaces, this);
        }
    }
}
