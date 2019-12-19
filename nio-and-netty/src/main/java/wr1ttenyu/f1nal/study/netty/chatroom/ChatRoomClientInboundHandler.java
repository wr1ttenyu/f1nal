package wr1ttenyu.f1nal.study.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public class ChatRoomClientInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            while (!scanner.hasNext("eof")) {
                String str = scanner.next();
                System.out.println("[我] 说: " + str);
                ctx.writeAndFlush(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
            }
        }).start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
