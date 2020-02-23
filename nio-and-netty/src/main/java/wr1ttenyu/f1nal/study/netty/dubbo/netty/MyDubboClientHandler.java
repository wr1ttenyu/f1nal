package wr1ttenyu.f1nal.study.netty.dubbo.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class MyDubboClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {

    private ChannelHandlerContext context;

    private String param;

    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = (String) msg;
        this.notify();
    }

    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(param);
        this.wait();
        return result;
    }

    public void setParam(String param) {
        this.param = param;
    }
}