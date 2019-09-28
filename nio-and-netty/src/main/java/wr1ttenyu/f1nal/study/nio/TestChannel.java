package wr1ttenyu.f1nal.study.nio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
 *
 *  The data transfer of different channel:
 *      transferFrom()
 *      transferTo()
 *
 * Scatter and Gather
 *      Scatter Reads : put data in channel to more than one buffer
 *      Gather Writes : get date from more than one buffer to one channel
 */
public class TestChannel {

    @Test
    public void testScatterAndGather() throws Exception {
//        FileChannel fisChannel = FileChannel.open(Paths.get("H:/1.jpg"), StandardOpenOption.READ);
//        FileChannel fosChannel = FileChannel.open(Paths.get("H:/2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        RandomAccessFile raf1 = new RandomAccessFile("H:/1.jpg", "rw");
        RandomAccessFile raf2 = new RandomAccessFile("H:/2.jpg", "rw");
        FileChannel fosChannel = raf2.getChannel();
        FileChannel fisChannel = raf1.getChannel();

        int bufferNum;
        long size = fisChannel.size();
        if(size < Integer.MAX_VALUE) {
            bufferNum = Long.valueOf(size/1024).intValue();
        } else {
            throw new Exception("file size too big to transfer");
        }
        // 经过调试源码发现 sun.nio.ch.IOUtil.IOV_MAX 这个参数限制了 byteBuffers 的长度最大只能为16
        ByteBuffer[] byteBuffers = new ByteBuffer[bufferNum];
        for (int i = 0; i < bufferNum; i++) {
            byteBuffers[i] = ByteBuffer.allocate(1024);
        }
        fisChannel.read(byteBuffers);
        for (int i = 0; i < bufferNum; i++) {
            byteBuffers[i].flip();
        }
        fosChannel.write(byteBuffers);

        if(fisChannel != null)
            fisChannel.close();
        if(fosChannel != null)
            fosChannel.close();
    }

    @Test
    public void testTransferBtwChannel() throws IOException {
        FileChannel fisChannel = FileChannel.open(Paths.get("H:/1.jpg"), StandardOpenOption.READ);
        FileChannel fosChannel = FileChannel.open(Paths.get("H:/2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        /*fisChannel.transferTo(0, fisChannel.size(), fosChannel);*/
        fosChannel.transferFrom(fisChannel, 0, fisChannel.size());

        if(fisChannel != null)
            fisChannel.close();
        if(fosChannel != null)
            fosChannel.close();
    }

    @Test
    public void testCopyFileOfGetChannel() throws IOException {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer bufFile = ByteBuffer.allocate(1024);
        while (fisChannel.read(bufFile) != -1) {
            bufFile.flip();
            fosChannel.write(bufFile);
            bufFile.clear(); // clear can reset position and limit to init state
        }
        if(fisChannel != null)
            fisChannel.close();
        if(fosChannel != null)
            fosChannel.close();
        if(fis != null)
            fis.close();
        if(fos != null)
            fos.close();
    }
}
