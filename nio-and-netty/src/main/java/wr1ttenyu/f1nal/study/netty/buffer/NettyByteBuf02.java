package wr1ttenyu.f1nal.study.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NettyByteBuf02 {

    public static void main(String[] args) {
        // 创建 ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello ByteBuf", CharsetUtil.UTF_8);
        System.out.println(byteBuf);

        // 使用相关的方法
        if(byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            System.out.println(new String(content, Charset.forName("utf-8")));

            System.out.println("byteBuf=" + byteBuf);

            System.out.println(byteBuf.readByte());

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            System.out.println(byteBuf.getByte(1));
            System.out.println(byteBuf.readableBytes());

            System.out.println(byteBuf.readByte());
            System.out.println(byteBuf.readableBytes());

            System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4, 6, Charset.forName("utf-8")));
        }

    }
}
