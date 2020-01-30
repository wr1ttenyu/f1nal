package wr1ttenyu.f1nal.study.netty.threadpool;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class MyThreadPoolNettyHandler extends ChannelInboundHandlerAdapter {

    static final EventExecutorGroup group = new DefaultEventExecutorGroup(2);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String myMsg = "服务器已收到消息: " + msg;
        /*Future<String> sendMsgFu = group.submit(() -> {
            ctx.writeAndFlush(myMsg);
            return myMsg;
        });*/
        System.out.println(Thread.currentThread().getName());
        ctx.writeAndFlush(myMsg);
    }
}
