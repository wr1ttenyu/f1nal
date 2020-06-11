package wr1ttenyu.f1nal.study.javase.file;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompress {

    private InputStream source;    // 源文件（带压缩的文件或文件夹）

    public ZipCompress(InputStream source) {
        this.source = source;
    }

    public ByteOutputStream zip() throws Exception {
        System.out.println("压缩中...");

        //创建zip输出流
        ByteOutputStream out1 = new ByteOutputStream();
        ZipOutputStream out = new ZipOutputStream(out1);


        //调用函数
        compress(out, source);


        return out1;
    }

    public void compress(ZipOutputStream out, InputStream source) throws Exception {
        out.putNextEntry(new ZipEntry("src\\main\\java\\wr1ttenyu\\test\\temp1.java"));
        byte[] bytes = "javaTest".getBytes();
        BufferedInputStream bis1 = new BufferedInputStream(new ByteInputStream(bytes, bytes.length));
        int tag1;
        //将源文件写入到zip文件中
        while ((tag1 = bis1.read()) != -1) {
            out.write(tag1);
        }
        bis1.close();
        out.closeEntry();

        out.putNextEntry(new ZipEntry("src\\main\\java\\wr1ttenyu\\test\\temp.xml"));
        BufferedInputStream bis = new BufferedInputStream(source);

        int tag;
        //将源文件写入到zip文件中
        while ((tag = bis.read()) != -1) {
            out.write(tag);
        }
        bis.close();

        out.closeEntry();

        out.flush();
        out.finish();
    }
}