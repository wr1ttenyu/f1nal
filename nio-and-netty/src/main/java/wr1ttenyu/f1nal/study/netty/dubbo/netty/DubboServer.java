package wr1ttenyu.f1nal.study.netty.dubbo.netty;

import wr1ttenyu.f1nal.study.netty.NettyServer;

public class DubboServer {

    public static void main(String[] args) {
        NettyServer server = new NettyServer("127.0.0.1", 8888, new MyDubboServerChannelInitializer());
        server.startServer();
    }
}
