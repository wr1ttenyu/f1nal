package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.UUID;

public class NettyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        String content = new String(msg.getBody(), Charset.forName("utf-8"));
        LOGGER.info("从客户端:{} 接收到消息:{} 长度为:{}", ctx.channel().remoteAddress(), content, msg.getLength());
        String uuid = UUID.randomUUID().toString();
        LOGGER.info("向客户端发送数据:{}", uuid);
        byte[] bytes = uuid.getBytes();
        MessageProtocol returnMsg = new MessageProtocol(bytes.length, bytes);
        ctx.writeAndFlush(returnMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("异常错误信息：{}", cause.getMessage());
    }
}
