package wr1ttenyu.f1nal.study.nio;

import java.nio.ByteBuffer;

public class TestBuffer {

    public void testBufferKeyFieldAndMethod() {

        /**
         * A container for data of a specific primitive type except boolean
         *
         * primitive type : byte short int long char float double boolean
         *
         * capacity、limit、position are three pivotal property
         */
        System.out.println("-------- buffer init --------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        String data = "wr1ttenyu";
        System.out.println("-------- buffer put --------");
        buffer.put(data.getBytes());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        System.out.println("-------- buffer flip --------");
        buffer.flip();
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        System.out.println("-------- buffer get --------");
        byte[] res = new byte[3];
        buffer.get(res);
        System.out.println("res:" + new String(res));
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        System.out.println("-------- buffer rewind  --------");
        buffer.rewind();
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        System.out.println("-------- buffer mark  --------");
        byte[] res2 = new byte[3];
        buffer.get(res2);
        buffer.mark();
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());

        System.out.println("-------- buffer reset  --------");
        byte[] res3 = new byte[3];
        buffer.get(res3);
        System.out.println("-------- buffer reset 1 --------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());
        buffer.reset();
        System.out.println("-------- buffer reset 2 --------");
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());
    }
}
