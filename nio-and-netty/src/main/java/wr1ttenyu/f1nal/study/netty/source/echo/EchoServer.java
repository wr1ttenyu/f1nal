/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package wr1ttenyu.f1nal.study.netty.source.echo;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.SingleThreadEventExecutor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Echoes back any received data from a client.
 */
public final class EchoServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        final EchoServerHandler serverHandler = new EchoServerHandler();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .option(ChannelOption.SO_BACKLOG, 100)
             // TODO 这里面的日志 Handler 是什么作用
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     if (sslCtx != null) {
                         p.addLast(sslCtx.newHandler(ch.alloc()));
                     }
                     //p.addLast(new LoggingHandler(LogLevel.INFO));
                     p.addLast("handler1", serverHandler).addLast("handler2", serverHandler);
                 }
             });

            // Start the server.
            /**
             * ---------------- Netty 的启动流程跟踪总结： start ----------------
             *
             * channel 注册到 selector
             * {@link io.netty.channel.AbstractChannel.AbstractUnsafe#register0(io.netty.channel.ChannelPromise)}
             * --->
             * 绑定端口
             * {@link AbstractBootstrap#doBind0(io.netty.channel.ChannelFuture, io.netty.channel.Channel, java.net.SocketAddress, io.netty.channel.ChannelPromise)}
             *
             * 同时在这个过程中启动了 {@link NioEventLoop} 实现的 {@link NioEventLoop#run()} 方法
             *
             * TODO 分析一下 {@link NioEventLoop#run()} 方法 在做什么事情
             *
             * ---------------- Netty 的启动流程跟踪总结： end ----------------
             *
             * ---------------- Netty 接收请求过程： start ----------------
             *
             * 服务端启动后，阻塞在了 {@link NioEventLoop#select(long)} 中的 java NIO 的 {@link Selector#select()} 方法上
             *      客户端连接请求到达后 {@link Selector#select()} 方法返回结果
             * --->
             * 到达 {@link NioEventLoop#processSelectedKey(java.nio.channels.SelectionKey, io.netty.channel.nio.AbstractNioChannel)} 方法
             *      开始处理 {@link SelectionKey.OP_ACCEPT} 事件，调用的是 {@link AbstractNioMessageChannel.NioMessageUnsafe#read()} 方法
             * --->
             * 在到 {@link NioServerSocketChannel#doReadMessages(java.util.List)} 方法，调用 {@link ServerSocketChannel#accept()} 方法
             *      这样就完成了客户端请求的 accept 动作, 接收完之后将 {@link java.nio.channels.SocketChannel} 包装成了一个 {@link NioSocketChannel}
             * --->
             * 在 {@link AbstractNioMessageChannel.NioMessageUnsafe#read()} 方法中，
             *      通过 {@link io.netty.channel.ChannelPipeline#fireChannelRead(java.lang.Object)} 方法，
             *      在 ServerSocketChannel 的 Pipeline 中传递，调用各个 Handler 的 ChannelRead 方法
             *      在 ServerSocketChannel 的 Pipeline 中预置了 {@link ServerBootstrap.ServerBootstrapAcceptor}
             *      所以 会调用到 {@link ServerBootstrap.ServerBootstrapAcceptor#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)}
             *      在该方法中，将 accept 获取的 SocketChannel 注册到 ChildGroup 中的某一个 EventLoop 上
             *      注册后会触发 channelActive 事件，调用child eventLoop 的 channelActive方法
             *
             * ---------------- Netty 接收请求过程： end ----------------
             *
             * ---------------- Netty {@link NioSocketChannel} 注册到 workerGroup 过程： start ----------------
             * {@link EventLoopGroup#register(io.netty.channel.Channel)} 开始注册
             * --->
             * 关注三个关键组件 pipeline  handler  handlerContext 创建
             * 1. pipeline 的创建是在 {@link NioSocketChannel} 创建的时候，在父类的构造函数
             *       {@link AbstractChannel#AbstractChannel(io.netty.channel.Channel)} 中创建了一个 {@link DefaultChannelPipeline}
             *       初始化的 pipeline 只有 {@link DefaultChannelPipeline.TailContext} 和 {@link DefaultChannelPipeline.HeadContext}
             *
             * 2. 自定义 Handler 添加到 Pipeline 中
             * {@link AbstractChannel.AbstractUnsafe#register0(io.netty.channel.ChannelPromise)} 是注册的核心方法
             *      {@link DefaultChannelPipeline#invokeHandlerAddedIfNeeded()} 将自定义的 Handler 添加到
             *      {@link NioSocketChannel} 初始化的 pipeline 中
             *      自定义 Handler 添加是通过
             *      {@link ServerBootstrap.ServerBootstrapAcceptor#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)}
             *      方法中，将我们自定义的 {@link ChannelInitializer} 封装成一个 {@link DefaultChannelPipeline.PendingHandlerAddedTask}
             *      在后续 register0 方法中再调用我们自定义的
             *      {@link io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.socket.SocketChannel)}
             *      添加到 handler 到 pipeline 中
             *
             * 3. handlerContext创建
             *  Handler 是包装在 HandlerContext 里面的，pipeline 的添加方法 addLast
             *      {@link ChannelPipeline#addLast(java.lang.String, io.netty.channel.ChannelHandler)}
             *      这个方法里面最终调用到
             *      {@link DefaultChannelPipeline#addLast(io.netty.util.concurrent.EventExecutorGroup, java.lang.String, io.netty.channel.ChannelHandler)}
             *      会创建 handlerContext 调用 newContext 方法
             *      {@link DefaultChannelPipeline#newContext(io.netty.util.concurrent.EventExecutorGroup, java.lang.String, io.netty.channel.ChannelHandler)}
             *      将 handler 包装进 {@link DefaultChannelHandlerContext} 中
             *
             * ---------------- Netty SocketChannel 注册到 workerGroup 过程： end ----------------
             */
            ChannelFuture f = b.bind(PORT).sync();
            /**
             * SOLVE ChannelFuture cancel() 是如何实现的 {@link DefaultPromise#cancel(boolean)}
             * {@link AtomicReferenceFieldUpdater} 使用该工具类 对 promise 的 result 字段进行 CAS 更新
             *      将结果更新为 取消状态 {@link DefaultPromise#CANCELLATION_CAUSE_HOLDER}
             *      如果更新成功 则唤醒等待的线程
             */

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
