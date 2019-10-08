package wr1ttenyu.f1nal.study.java8;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class TestLambda1 {

    @Test
    public void testLambdaGrammar() {
        // 方法体 只有一句 可以不用写大括号
        new Thread(() -> System.out.println("111")).start();

        // 方法体 不止一句 必须写大括号
        new Thread(() -> {
            System.out.println("111");
            System.out.println("222");
        }).start();

        // 有一个参数 参数的括号 可以省略
        Consumer<String> consumer = (x) -> System.out.println(x);
        Consumer<String> consumer2 = x -> System.out.println(x);

        // 若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
        // Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
        Function<String, String> function = (x) ->  x;


    }

}
