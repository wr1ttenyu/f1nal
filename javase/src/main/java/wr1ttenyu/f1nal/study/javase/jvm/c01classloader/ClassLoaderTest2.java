package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

public class ClassLoaderTest2 {

    public static void main(String[] args) {
        try {
            ClassLoader bootstrapClassLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(bootstrapClassLoader);

            ClassLoader appClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(appClassLoader);

            ClassLoader extClassLoader = ClassLoader.getSystemClassLoader().getParent();
            System.out.println(extClassLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
