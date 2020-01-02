package wr1ttenyu.f1nal.study.netty.inandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler 发送数据");
        ctx.writeAndFlush(Unpooled.copiedBuffer("abfjjgubjdhsjgjbjt", CharsetUtil.UTF_8));
    }
}
