package wr1ttenyu.f1nal.study.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * three core steps of use NIO to solve network communication:
 * <p>
 * 1. Channel: A nexus for I/O operations. in charge of connection
 * <p>
 * 2. Buffer: A container for data of a specific primitive type. in charge of data transfer
 * <p>
 * 3. Selector： A multiplexor of {@link SelectableChannel} objects. in charge of monitor the state of channel data
 */
public class TestBlockingNIO {

    @Test
    public void server() {
        try {
            // 打开通道 --> 绑定端口 --> 等待连接
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8888));
            SocketChannel sChannel = serverSocketChannel.accept();

            FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\wr1ttenyu\\学习\\2.gif"),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (sChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            fileChannel.close();
            sChannel.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client() {
        try {
            SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

            FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\wr1ttenyu\\学习\\1.gif"),
                    StandardOpenOption.READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                sChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            sChannel.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serverWithResponse() {
        try {
            // 打开通道 --> 绑定端口 --> 等待连接
            ServerSocketChannel open = ServerSocketChannel.open();
            ServerSocketChannel ssChannel = open.bind(new InetSocketAddress(8888));
            SocketChannel sChannel = ssChannel.accept();

            FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\wr1ttenyu\\学习\\2.gif"),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (sChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            String response = "已收到应答";
            byteBuffer.put(response.getBytes());
            byteBuffer.flip();
            sChannel.write(byteBuffer);

            sChannel.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clientWithResponse() {
        try {
            SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));

            FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\wr1ttenyu\\学习\\1.gif"),
                    StandardOpenOption.READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                sChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            // 如果不关闭输出 那么接收端是不知道已经传输完毕的
            // TODO 这个可能就是对应 tcp ip 协议中的一个动作 需要搞清楚 到底对应什么 顺便也就把 tcp ip 协议再总结一下
            sChannel.shutdownOutput();

            while (sChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                byte[] resByte = new byte[byteBuffer.limit()];
                byteBuffer.get(resByte);
                String response = new String(resByte);
                System.out.println(response);
                byteBuffer.clear();
            }

            sChannel.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
