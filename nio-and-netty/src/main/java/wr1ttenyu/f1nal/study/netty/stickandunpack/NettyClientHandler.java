package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyClientHandler.class);

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送10条数据到服务端
        MessageProtocol messageProtocol;
        String msg = "下雨喽，不能打球喽，好烦啊！";
        byte[] content = msg.getBytes(Charset.forName("utf-8"));
        for (int i = 0; i < 10; i++) {
            int length = content.length;
            LOGGER.info("第{}次，向客户端发送数据:{} 长度为:{}", i, msg, length);
            messageProtocol = new MessageProtocol(length, content);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        String content = new String(msg.getBody(), Charset.forName("utf-8"));
        LOGGER.info("从服务端:{} 接收到消息:{} 长度为:{}", ctx.channel().remoteAddress(), content, msg.getLength());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("异常错误信息：{}", cause.getMessage());
    }
}
