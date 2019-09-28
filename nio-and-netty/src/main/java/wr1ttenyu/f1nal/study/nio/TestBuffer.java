package wr1ttenyu.f1nal.study.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class TestBuffer {

    public static void main(String[] args) {

    }

    @Test
    public void testByteBufferPosition() {
        ByteBuffer allocate = ByteBuffer.allocate(9);
        String str = "wr1ttenyu";
        byte[] bytes = new byte[9];
        allocate.put(str.getBytes());
        allocate.flip();
        allocate.get(bytes);
        System.out.println("position:" + allocate.position());
        System.out.println("limit:" + allocate.limit());
        allocate.clear();
        System.out.println("position:" + allocate.position());
        System.out.println("limit:" + allocate.limit());
    }

    public void testAllocate() {
        // transfer data from hard disk to work thread memory:
        // hard disk ---> os memery ---> work thread memory ---> program
        // driect memory avoid copy data from os memory to work thread memory:
        // hard disk ---> driect memory ---> program
        ByteBuffer.allocateDirect(1024);
        ByteBuffer.allocate(1024);
    }

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
