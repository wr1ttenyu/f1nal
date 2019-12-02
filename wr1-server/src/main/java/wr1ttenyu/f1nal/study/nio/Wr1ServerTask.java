package wr1ttenyu.f1nal.study.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

class Wr1ServerTask implements Runnable {

    private NioServerEventHandler serverEventHandler;
    private Selector selector;
    private SelectionKey sk;

    public Wr1ServerTask(NioServerEventHandler serverEventHandler, Selector selector, SelectionKey sk) {
        this.serverEventHandler = serverEventHandler;
        this.selector = selector;
        this.sk = sk;
    }

    @Override
    public void run() {
        // process accept event
        if (sk.isAcceptable()) {
            serverEventHandler.handleAccept(selector, sk);
        } else if (sk.isReadable()) {
            serverEventHandler.handleRead(selector, sk);
        } else if (sk.isWritable()) {
            serverEventHandler.handleWrite(selector, sk);
        }
    }

    public NioServerEventHandler getServerEventHandler() {
        return serverEventHandler;
    }

    public Selector getSelector() {
        return selector;
    }

    public SelectionKey getSk() {
        return sk;
    }
}

