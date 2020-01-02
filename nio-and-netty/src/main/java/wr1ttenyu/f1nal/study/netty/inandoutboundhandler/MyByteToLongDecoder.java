package wr1ttenyu.f1nal.study.netty.inandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * @param ctx 上下文
     * @param in 入站 ByteBuf
     * @param out 解码后的数据集合
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        if(in.readableBytes() >= 8) {
            long msg = in.readLong();
            System.out.println("msg = " + msg);
            out.add(msg);
        }
    }
}
