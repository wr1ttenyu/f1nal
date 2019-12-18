package wr1ttenyu.f1nal.study.netty.chatroom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoomServerInboundHandler extends ChannelInboundHandlerAdapter {

    private static Map<String, Channel> allUser = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String userCode = getUserCode(channel);
        allUser.put(userCode, channel);
        String msg = "[" + userCode + "] 欢迎登陆聊天室";
        System.out.println(msg);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String userCode = getUserCode(channel);
        ByteBuf buf = (ByteBuf) msg;
        String casterMsg = "["  + userCode + "] 说: " + buf.toString(CharsetUtil.UTF_8);
        msgBroadcaster(casterMsg, channel);
        System.out.println(casterMsg);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String userCode = getUserCode(channel);
        String msg = "[ " + userCode + "] 下线了！";
        msgBroadcaster(msg, channel);
        System.out.println(msg);
    }

    private void msgBroadcaster(String msg, Channel curChannel) {
        for (Map.Entry<String, Channel> entry : allUser.entrySet()) {
            Channel channel = entry.getValue();
            if(channel != curChannel)
                channel.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        }
    }

    private String getUserCode(Channel channel) {
        InetSocketAddress socketAddress = (InetSocketAddress) channel.remoteAddress();
        return socketAddress.getHostString() + ":" + socketAddress.getPort();
    }

    public void test(HashMap<String, String> map) {
        map.put("2", "2");
        System.out.println("test:" + map);
        map = new HashMap<>();
        map.put("3", "3");
    }
}
