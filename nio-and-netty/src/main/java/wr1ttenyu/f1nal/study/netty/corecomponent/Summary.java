package wr1ttenyu.f1nal.study.netty.corecomponent;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

/**
 * 1. {@link Bootstrap} 和 {@link ServerBootstrap}
 *       Bootstrap 意思是引导，一个 Netty 应用通常由一个 Bootstrap 开始，主要作用是配置整个 Netty 程序，串联各个组件，
 *    Netty 中 Bootstrap 类是客户端程序的启动引导类，ServerBootstrap 是服务端启动引导类
 *
 * 2. Future、ChannelFuture
 *       Netty 中所有的 IO 操作都是异步的，不能立刻得知消息是否被正确处理。但是可以过一会等他执行完成或者直接注册一个监听，
 *    具体过程就是通过 Future 和 ChannelFutures, 他们可以注册一个监听，当操作执行成功或者失败时监听会自动触发注册的监听事件
 *
 * 3. Channel
 *    1). Netty 网络通信的组件，能够用于执行网络 I/O 操作
 *    2). 通过 Channel 可以获得当前网络连接的通道的状态
 *    3). 通过 Channel 可获得 网络连接的配置参数 （例如接收缓冲区大小）
 *    4). Channel 提供异步的网络 I/O 操作（如建立连接、读写、绑定端口），异步调用意味着任何 I/O 调用都将立即返回，
 *          并且不保证在调用结束时所请求的 I/O 操作已完成
 *    5). 调用立即返回一个 ChannelFuture 实例，通过注册监听器到 ChannelFuture 上，可以在 I/O 操作成功、失败或者
 *          取消时回调通知调用方
 *    6). 支持关联 I/O 操作与对应的处理程序
 *    7). 不同协议、不同的阻塞类型的连接都有不同的 Channel 类型与之对应，常用的 Channel 类型：
 *          NioSocketChannel: 异步的客户端 TCP Socket 连接
 *          NioServerSocketChannel: 异步的服务器端 TCP Socket 连接
 *          NioDatagramChannel: 异步的 UDP 连接
 *          NioSctpChannel: 异步的客户端 Sctp 连接
 *          NioSctpServerChannel: 异步的服务器端 Sctp 连接
 *          这些通道覆盖了UDP 、 TCP 网络 IO 以及文件 IO。
 *
 * 4. Selector
 *    1). Netty 基于 Selector 对象实现 I/O 多路复用， 通过 Selector 一个线程可以监听多个连接的 Channel 事件。
 *    2). 当向一个 Selector 中注册 Channel 后， Selector 内部的机制就可以自动不断地查询(Select) 这些注册的
 *      Channel 是否有已就绪的 I/O 事件（例如可读， 可写， 网络连接完成等） ， 这样程序就可以很简单地使用一个线程高效地管理多个 Channel
 *
 * 5. ChannelHandler 及其子接口
 *    1). ChannelHandler 是一个接口，处理 I/O 事件或拦截 I/O 操作，并将其转发到其 ChannelPipeline（业务处理链）中的下一个处理程序
 *    2). ChannelHandler 本身并没有提供很多方法，因为这个接口有许多的方法需要实现，方便使用期间，可以继承它的子类
 *    3). ChannelOutboundHandler 和 ChannelInboundHandler 是其子接口，分别负责 出站 和 入站 I/O 事件
 *
 * 6. Pipeline 和 ChannelPipeline
 *    ChannelPipeline 是一个重点：
 *      1). ChannelPipeline 是一个 Handler 的集合，它负责处理和拦截 inbound 或者 outbound 的事件和操作，相当于一个
 *              贯穿 Netty 的链。
 *      2). ChannelPipeline 实现了一种高级形式的拦截过滤器模式，使用户可以完成控制事件的处理方式，以及 Channel
 *              中各个的 ChannelHandler 如何交互
 *      3). 在 Netty 中每个 Channel 都有且仅有一个 ChannelPipeline 与之对应，他们的组成关系如下：
 *              a> 一个 Channel 包含了一个 ChannelPipeline，而 ChannelPipeline 中又维护了一个由 ChannelHandlerContext
 *          组成的双向链表，并且每个 ChannelHandlerContext 中又关联着一个 ChannelHandler
 *              b> 入站事件和出站事件在一个双向链表中，入站事件会从链表 head 往后传递到最后一个入站的 handler，
 *          出站事件会从链表 tail 往前传递到最前一个出站的 handler, 两种类型的 handler 互不干扰
 *
 * 7. ChannelHandlerContext
 *      1). 保存 Channel 相关的所有上下文信息，同时关联一个 ChannelHandler 对象
 *      2). 即 ChannelHandlerContext 中包含一个具体的事件处理器 ChannelHandler,同时 ChannelHandlerContext 中也绑定了
 *          的对应的 pipeline 和 Channel 的信息，方便对 ChannelHandler 进行调用
 *
 * 8. ChannelOption
 *      1). Netty 在创建 Channel 实例后，一般都需要设置 ChannelOption 参数
 *      2). ChannelOption 选项详见 {@link ChannelOption}
 *
 * 9. EventLoopGroup 和其实现类 NioEventLoopGroup
 *     1). EventLoopGroup 是一组 EventLoop 的抽象，Netty 为了更好的利用多核 CPU 资源，一般会有多个 EventLoop 同时工作，
 *          每个 EventLoop 维护着一个 Selector 实例。
 *     2). EventLoopGroup 提供 next 接口，可以从组里面按照一定规则获取其中一个 EventLoop 来处理任务。在 Netty 服务器端编程中，
 *          我们一般都需要两个 EventLoopGroup. BossEventLoopGroup 和 WorkerEventLoopGroup。
 *     3). 通常一个服务端口即一个 ServerSocketChannel 对应一个 Selector 和一个 EventLoop 线程。BossEventLoop 负责接收客户端的
 *          连接并将 SocketChannel 交给 WorkerEventLoopGroup 来进行 IO 处理。
 *
 * 10. Unpooled 类
 *     1). Netty 提供一个专门用来操作缓冲区（即 Netty 的数据容器）的工具类
 *     2). 三个重要的属性   readerIndex    writerIndex    capacity
 */
public class Summary {
}
