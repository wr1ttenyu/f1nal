package wr1ttenyu.f1nal.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe {

    public static void main(String[] args) {
        try {
            // 1. gain pipe
            Pipe pipe = Pipe.open();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 2. send data through pipe
            Pipe.SinkChannel sink = pipe.sink();
            buf.put("你好啊".getBytes());
            buf.flip();
            sink.write(buf);
            buf.clear();

            /*ByteBuffer buf2 = ByteBuffer.allocate(1024);*/
            // 3. receive data through pipe
            Pipe.SourceChannel source = pipe.source();
            int length = source.read(buf);
            System.out.println(new String(buf.array(), 0, length));

            sink.close();
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
