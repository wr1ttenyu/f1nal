package wr1ttenyu.f1nal.study.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// TextWebSocketFrame 表示一个文本Frame
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 当web客户端连接后 就会触发该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //ctx.channel().id() 获取 channel 的唯一ID asLongText 不重复 asShortText 有可能重复
        System.out.println("handlerAdded 被调用 " + ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用 " + ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息:" + msg.text());

        // 回复消息
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println();
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间: " + dateTimeFormatter.format(ldt) + " " + msg.text()));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用 " + ctx.channel().id().asLongText());
        System.out.println("handlerRemoved 被调用 " + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("有异常发生：" + ctx.channel().id().asShortText());
        ctx.channel().close();
    }
}
