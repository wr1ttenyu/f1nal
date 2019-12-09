package wr1ttenyu.f1nal.study.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 说明：
 * 自定义netty的handler需要继承netty规定好的某个HandlerAdapter（规范）
 * 这时我们自定义的handler，才能成为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据的事件（这里我们可以读取客户端发送的消息）

    /**
     * 1. ChannelHandlerContext ctx：上下文对象，含有管道pipeline, 通道SocketChannel，连接ip等
     * 2. Object msg：就是客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // --------------------  耗时操作  --------------------
        // 1. 用户自定义的任务
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("喵2 " + Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~ 喵2", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("喵3 " + Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~ 喵3", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 2. 用户自定义定时任务 ---> 该任务提交到 scheduleTaskQueue
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("喵4 " + Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~ 喵4", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);

        // 3. 非Reactor线程来调用SocketChannel的各种方法
        // 可以通过在任意一个handler中 使用一个集合将SocketChannel和对应的用户关联关系保存起来
        // 通过这种方式可以 实现 非Reactor线程来调用SocketChannel的各种方法

        System.out.println("go on .....");

        // --------------------  普通操作  --------------------
        /*System.out.println("server read thread : " + Thread.currentThread().getName());
        System.out.println("server ctx = " + ctx);
        System.out.println("看看channel 和 pipeline 的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); // 本质是一个双向链表，出栈入栈

        // 将msg转成netty的ByteBuf，不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client msg is : " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("client ip is : " + channel.remoteAddress());*/
    }

    /**
     * 数据读取完毕之后执行的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // writeAndFlush: write + flush 数据写入缓存 并 发送
        // 一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client~ 喵1", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
