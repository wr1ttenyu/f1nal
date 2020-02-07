package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest1 {

    public static void main(String[] args) {
        System.out.println("************* 启动类加载器 *************");
        // 获取 BootstrapClassLoader 能够加载的 api 路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        // 从上面路径中选择 jar包中的一个类 看看他的加载器是什么？ BootstrapClassLoader
        ClassLoader bootstrapClassLoader = Provider.class.getClassLoader();
        System.out.println(bootstrapClassLoader); // null --> BootstrapClassLoader

        System.out.println("************* 扩展类加载器 *************");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }

        // 从上面路径中选择 jar包中的一个类 看看他的加载器是什么？ ExtClassLoader
        ClassLoader extClassLoader = CurveDB.class.getClassLoader();
        System.out.println(extClassLoader); // sun.misc.Launcher$ExtClassLoader@7ea987ac
    }
}
