package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageToByteEncoder<I> 中的泛型是要跟 Handler 发送的数据匹配才会被 encode 编码
 */
public class MyStickAndUnpackEncoder extends MessageToByteEncoder<MessageProtocol> {

    private static Logger LOGGER = LoggerFactory.getLogger(MyStickAndUnpackEncoder.class);

    /**
     * @param ctx 上下文
     * @param msg 出站 数据
     * @param out 编码后的 ByteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        LOGGER.info("粘拆包编码器开始编码，编码数据为 msg:{}", msg);
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getBody());
    }
}
