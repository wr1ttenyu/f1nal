package wr1ttenyu.f1nal.study.javase.file;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileOutputStream;


public class TestFileCompress {

    public static void main(String[] args) throws Exception {

        byte[] bytes = "test".getBytes();

        ZipCompress zipCompress = new ZipCompress(new ByteInputStream(bytes, bytes.length));

        ByteOutputStream zip = zipCompress.zip();

        byte[] bytes1 = zip.getBytes();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("test.zip"));
        fileOutputStream.write(bytes1);
    }

}
