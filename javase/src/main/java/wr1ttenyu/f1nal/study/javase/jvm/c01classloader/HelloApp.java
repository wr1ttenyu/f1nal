package wr1ttenyu.f1nal.study.javase.jvm.c01classloader;

public class HelloApp {

    private static int a = 1; // prepare : a=0 --> initial : a=1

    private static Integer b;

    public static void main(String[] args) {
        // user 中的 name 属性，是属于实例变量，静态属性才属于类变量
        // 实例变量只有在 类被实例化的时候 才会被初始化
        User user = new User();
        System.out.println(a);
        System.out.println(b);
        System.out.println(user.getName());
    }
}
