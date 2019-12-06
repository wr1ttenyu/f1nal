package wr1ttenyu.f1nal.study.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class OldFileCopySocketClient {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try(FileInputStream fileInputStream = new FileInputStream("test.zip");
            Socket socket = new Socket("127.0.0.1", 9988);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());) {

            byte[] data = new byte[4096];
            int length;
            while ((length = fileInputStream.read(data)) > 0) {
                dataOutputStream.write(data, 0, length);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("cost time is " + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
