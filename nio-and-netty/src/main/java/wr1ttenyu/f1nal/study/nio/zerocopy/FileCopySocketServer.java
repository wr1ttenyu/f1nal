package wr1ttenyu.f1nal.study.nio.zerocopy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileCopySocketServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket();
             FileOutputStream fileOutputStream = new FileOutputStream("test2.zip")) {
            serverSocket.bind(new InetSocketAddress(9988));
            for (;;) {
                Socket socket = serverSocket.accept();
                byte[] data = new byte[1024];
                int sum = 0;
                int length;
                while ((length = socket.getInputStream().read(data)) > 0) {
                    fileOutputStream.write(data, 0, length);
                    sum += length;
                }
                System.out.println(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
