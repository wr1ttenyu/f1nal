package wr1ttenyu.f1nal.study.netty.inandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * MessageToByteEncoder<I> 中的泛型是要跟 Handler 发送的数据匹配才会被 encode 编码
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * @param ctx 上下文
     * @param msg 出站 数据
     * @param out 编码后的 ByteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder 被调用");
        System.out.println("msg = " + msg);
        out.writeLong(msg);
    }
}
