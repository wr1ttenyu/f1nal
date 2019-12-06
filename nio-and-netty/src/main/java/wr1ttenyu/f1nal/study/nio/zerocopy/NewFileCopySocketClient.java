package wr1ttenyu.f1nal.study.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewFileCopySocketClient {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try(FileInputStream fileInputStream = new FileInputStream("test.zip");
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9988))) {
            FileChannel channel = fileInputStream.getChannel();
            channel.transferTo(0, channel.size(), socketChannel);
            long endTime = System.currentTimeMillis();
            System.out.println("cost time is " + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
