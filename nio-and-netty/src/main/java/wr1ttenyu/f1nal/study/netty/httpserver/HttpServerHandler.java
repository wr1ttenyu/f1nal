package wr1ttenyu.f1nal.study.netty.httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 1. SimpleChannelInboundHandler 继承了 ChannelInboundHandlerAdapter
 * 2. HttpObject 是前一个Handler将请求数据封装的结果
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断 msg 是不是一个 HttpRequest
        if(msg instanceof HttpRequest) {
            System.out.println(msg.getClass());
            System.out.println(ctx.channel().remoteAddress());

            // 过滤掉特定的资源
            HttpRequest request = (HttpRequest)msg;
            URI uri = new URI(request.uri());
            if("/favicon.ico".equals(uri.getPath())) {
                System.out.println("favicon.ico请求, 不做响应");
                return;
            }

            // 回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            // 构造一个 Http 的响应，即 HttpResponse
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的 response 返回
            ctx.channel().writeAndFlush(response);
        }
    }
}
