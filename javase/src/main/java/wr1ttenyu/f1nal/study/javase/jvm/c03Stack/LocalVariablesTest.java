package wr1ttenyu.f1nal.study.javase.jvm.c03Stack;

import java.time.LocalDateTime;

public class LocalVariablesTest {

    private int count = 0;

    public LocalVariablesTest() {
        this.count = 1;
    }

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    private void test1() {
        LocalVariablesTest test = new LocalVariablesTest();
        LocalDateTime date = LocalDateTime.now();
        String name1 = "wr1ttenyu";
        test2(date, name1);
        System.out.println(date + name1);
    }

    private static void testStatic() {
        LocalDateTime date = LocalDateTime.now();
        String name1 = "wr1ttenyu";
        int num = 10;
        System.out.println(num);
    }

    private String test2(LocalDateTime dateP, String name2) {
        dateP = null;
        name2 = "handsome";
        double weight = 72.13;
        char name1 = '里';
        return String.valueOf(name1) + weight;
    }

    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c 使用之前已经销毁的变量b占据的slot
        int c = a + 1;
    }

    /**
     * 变量的分类：按照数据类型分类 ①：基本数据类型 ②：引用数据类型
     *           按照在类中声明的位置：①：成员变量：在使用前，都经过默认初始化复制
     *                                      类变量：linking的prepare阶段：给类变量默认赋值 ---> initial阶段：给类变量显示赋值即静态代码块赋值
     *                                      实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
     *                             ②：局部变量: 在使用前，必须要进行显示赋值的，否则，编译不通过
     */
    public void test5Temp() {
        int num;
        // System.out.println(num); // 错误信息: num位初始化
    }
}
