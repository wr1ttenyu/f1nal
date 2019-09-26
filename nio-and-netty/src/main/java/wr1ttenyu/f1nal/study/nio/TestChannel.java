package wr1ttenyu.f1nal.study.nio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * java.nio.channels.Channel
 *
 * A nexus for I/O operations.
 *
 * <p> A channel represents an open connection to an entity such as a hardware
 * device, a file, a network socket, or a program component that is capable of
 * performing one or more distinct I/O operations, for example reading or
 * writing.
 *
 * Channal major implementation class:
 *      FileChannel
 *      SocketChannel
 *      ServerSocketChannel
 *      DatagramChannel
 *
 * The method of get Channel:
 *  1. JAVA 针对支持通道的类提供了 getChannel() 方法
 *      本地IO:
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile
 *      网络IO:
 *          Socket
 *          ServerSocket
 *          DategramSocket
 *  2. 在 JDK1.7 中的NIO.2 针对各个通道提供了静态方法 open()
 *  3. 在 JDK1.7 中的NIO.2 的 Files 工具类的 newByteChannel()
 */
public class TestChannel {

    @Test
    public void testCopyFile() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
    }
}
