package wr1ttenyu.f1nal.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪的时候就触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client ctx : " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server~", CharsetUtil.UTF_8));
        /*ctx.write(Unpooled.copiedBuffer("hello, server~", CharsetUtil.UTF_8));*/
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 将msg转成netty的ByteBuf，不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("server msg is : " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("server ip is : " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
