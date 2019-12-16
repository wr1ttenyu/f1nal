package wr1ttenyu.f1nal.study.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class NettyByteBuf01 {

    public static void main(String[] args) {
        // 创建一个 ByteBuf
        // 1. 创建 对象，该对象包含一个数组arr, 是一个byte[10]
        // 2. 在 netty 的 buffer 中，不需要使用 flip 进行反转
        //      底层维护了 readerIndex 和 writerIndex
        // 3. 通过 readerIndex 和 writerIndex 和 capacity，将 buffer 分为三个区域
        // 0 -- readerIndex 已经读取的区域
        // readerIndex --- writerIndex 可读的区域
        // writerIndex --- capacity 可写的区域
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println(byteBuf.capacity());
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.getByte(i));
        }

        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            byteBuffer.put(new Integer(i).byteValue());
        }
        byteBuffer.flip();
        byteBuffer.put(new Integer(5).byteValue());
        System.out.println(byteBuffer);
    }
}
