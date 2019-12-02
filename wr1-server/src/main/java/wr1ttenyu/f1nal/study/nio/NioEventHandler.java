package wr1ttenyu.f1nal.study.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface NioEventHandler {

    default void handleAccept(Selector selector, SelectionKey sk) {
        throw new RuntimeException("操作不支持");
    }

    default void handleConnect(Selector selector, SelectionKey sk) {
        throw new RuntimeException("操作不支持");
    }

    default void handleRead(Selector selector, SelectionKey sk) {
        throw new RuntimeException("操作不支持");
    }

    default void handleWrite(Selector selector, SelectionKey sk) {
        throw new RuntimeException("操作不支持");
    }
}
