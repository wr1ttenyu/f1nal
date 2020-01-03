package wr1ttenyu.f1nal.study.netty.stickandunpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyStickAndUnpackDecoder extends ByteToMessageDecoder {

    private static Logger LOGGER = LoggerFactory.getLogger(MyStickAndUnpackDecoder.class);

    /**
     * @param ctx 上下文
     * @param in  入站 ByteBuf
     * @param out 解码后的数据集合
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LOGGER.info("粘拆包解码器开始解码");
        int msgLength = in.readInt();
        byte[] body = new byte[msgLength];
        in.readBytes(body);
        MessageProtocol messageProtocol = new MessageProtocol(msgLength, body);
        LOGGER.info("粘拆包解码器开始解码，解码结果为 msg:{}", messageProtocol);
        out.add(messageProtocol);
    }
}
