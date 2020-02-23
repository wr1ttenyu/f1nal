package wr1ttenyu.f1nal.study.netty.dubbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import wr1ttenyu.f1nal.study.netty.dubbo.service.HelloServiceImpl;
import wr1ttenyu.f1nal.study.netty.dubbo.service.IHelloService;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Slf4j
public class MyDubboServerHandler extends ChannelInboundHandlerAdapter {

    private IHelloService helloService = new HelloServiceImpl();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // my dubbo protocol => service class name + # + method name + # + msg
        String reqMsg = (String) msg;
        String[] args = reqMsg.split("#");
        if(args.length == 3) {
            ctx.writeAndFlush(helloService.hello(args[2]));
        } else {
            ctx.writeAndFlush("请求内容不符合协议要求");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
        log.info("客户端接入，ip:{} port:{}", socketAddress.getAddress(), socketAddress.getPort());
    }
}
