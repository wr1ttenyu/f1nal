package wr1ttenyu.f1nal.study.netty.stickandunpack;

import java.util.Arrays;

/**
 * 协议包
 */
public class MessageProtocol {

    // 数据包大小
    private int length;
    private byte[] body;

    public MessageProtocol(int length, byte[] body) {
        this.length = length;
        this.body = body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
                "length=" + length +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
