package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class NettyServerHandlerWithoutDecoder extends ChannelInboundHandlerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyServerHandlerWithoutDecoder.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("不解码 从客户端:{} 收到消息 长度为:{}", ctx.channel().remoteAddress(), msg);
        // 讲
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("异常错误信息：{}", cause.getMessage());
    }
}
