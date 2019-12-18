package wr1ttenyu.f1nal.study.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Scanner;

public class ChatRoomClientInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            while (!scanner.hasNext("eof")) {
                String str = scanner.next();
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

    // TODO JAVA 值传递  引用传递
    // https://blog.csdn.net/weixin_34249678/article/details/85995877
    public static void main(String[] args) throws InterruptedException {
        Integer num = 10000;
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");

        new Thread(() -> {
            for (;;) {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread:" + map);
            }

        }).start();

        Thread.currentThread().sleep(5000);

        ChatRoomServerInboundHandler handler = new ChatRoomServerInboundHandler();
        handler.test(map);

        Thread.currentThread().sleep(5000);

        System.out.println("final:" + map);

    }
}
