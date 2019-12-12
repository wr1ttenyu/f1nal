package wr1ttenyu.f1nal.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class BlockServer {

    public static void main(String[] args) {
        try {
            // 打开通道 --> 绑定端口 --> 等待连接
            ServerSocketChannel open = ServerSocketChannel.open();
            ServerSocketChannel ssChannel = open.bind(new InetSocketAddress(8888));
            SocketChannel sChannel = ssChannel.accept();


            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (sChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                byte[] dst = new byte[byteBuffer.limit()];
                byteBuffer.get(dst);
                System.out.println(new String(dst, Charset.forName("UTF-8")));
                byteBuffer.clear();
            }

            String response = "已收到应答";
            byteBuffer.put(response.getBytes());
            byteBuffer.flip();
            sChannel.write(byteBuffer);

            sChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
