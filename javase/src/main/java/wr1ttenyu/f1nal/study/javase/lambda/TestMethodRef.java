package wr1ttenyu.f1nal.study.javase.lambda;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 1. method reference: if exist a method has achieve lambda method body, we can use "method reference".
 * the method reference is a special manifestation of lambda.
 * <p>
 * object reference :: instance method
 * <p>
 * class name :: static method
 * <p>
 * class name :: instance method
 *
 * 注意：
 *  ①.方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中的抽象方法的参数列表的返回值类型保持一致
 *  ②.若 Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数（或无参）是实例方法的参数时，格式 --> ClassName::MethodName
 *
 * 2. constructor reference: the function interface arguments and return type must the same as the constructor method
 *
 * 3. array constructor reference
 */
public class TestMethodRef {

    /**
     * object reference :: instance method
     */
    @Test
    public void testMethodRef1() {
        PrintStream out = System.out;
        Consumer<String> stringConsumer1 = (str) -> out.println(str);
        stringConsumer1.accept("Hello World！");

        System.out.println("--------------------------------");

        Consumer<String> stringConsumer2 = out::println;
        stringConsumer2.accept("Hello World！");

        BiFunction<String, String, String> function1 = (str1, str2) -> connectString(str1, str2);

        TestMethodRef testMethodRef = new TestMethodRef();
        BiFunction<String, String, String> function2 = testMethodRef::connectString;
    }

    /**
     * class name :: static method
     */
    @Test
    public void testMethodRef2() {
        Comparator<Integer> comparable1 = (int1, int2) -> Integer.compare(int1, int2);

        System.out.println("--------------------------------");

        Comparator<Integer> comparable2 = Integer::compare;
    }

    /**
     * class name :: instance method
     */
    @Test
    public void testMethodRef3() {
        BiPredicate<String, String> stringPredicate1 = (str1, str2) -> str1.equals(str2);

        System.out.println("--------------------------------");

        BiPredicate<String, String> stringPredicate2 = String::equals;
    }

    /**
     * class name :: new
     */
    @Test
    public void testConstructorRef() {
        Supplier<String> stringSupplier1 = () -> new String();

        System.out.println("--------------------------------");

        Supplier<String> stringSupplier2 = String::new;

        Function<String, String> stringSupplier3 = String::new;

        String apply = stringSupplier3.apply("123");
        System.out.println(apply);
    }

    /**
     * array :: new
     */
    @Test
    public void testArrayConstructorRef() {
        Supplier<String[]> stringSupplier1 = () -> new String[10];

        System.out.println("--------------------------------");

        Supplier<String> stringSupplier2 = String::new;

    }

    public String connectString(String str1, String str2) {
        return str1 + str2;
    }

}
