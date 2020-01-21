package wr1ttenyu.f1nal.study.wr1mbgenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlGenerator {

    public static void main(String[] args) {
        InputStream instream = XmlGenerator.class.getClassLoader().getResourceAsStream("generator-template.xml");

        String oldXML = null;
        try {
            oldXML = new String(XmlGenerator.read(instream), "UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String newXML = oldXML.replaceAll("\\$databaseDriver", "C:\\Users\\\\Administrator\\\\.m2\\repository\\\\mysql\\\\mysql-connector-java\\\\8.0.12\\\\mysql-connector-java-8.0.12.jar").replaceAll("\\$driverClass", "com.mysql.jdbc.Driver");

        System.out.println(newXML);
    }

    public static byte[] read(InputStream instream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = instream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        return bos.toByteArray();
    }

}
